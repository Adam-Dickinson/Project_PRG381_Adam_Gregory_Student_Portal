package net.portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AppController {
	
	@Autowired
	private StudentRepository studRepo;
	
	@Autowired
	private AdminRepository adRepo;
	
	@Autowired
	private StudentService service;
	
	@GetMapping("")
	public String viewHomePage() {
		return"index";
	}
	
	@GetMapping("/register_student")
	public String showStudentRegForm(Model model) {
		model.addAttribute("student", new Student());
		return "student_register_form";
	}
	
	@PostMapping("/process_student_register")
	public String processStudentRegistration(Student student) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(student.getPassword());
		student.setPassword(encodedPassword);
		studRepo.save(student);
		return "student_register_success";
	}
	
	@GetMapping("/register_admin")
	public String showAdminRegForm(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin_register_form";
	}
	
	@PostMapping("/process_admin_register")
	public String processAdminRegistration(Admin admin) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(admin.getPassword());
		admin.setPassword(encodedPassword);

		adRepo.save(admin);
		return "admin_register_success";
	}
	
	@GetMapping("/list_student")
	public String viewStudentsList(Model model) {
		List<Student> listStudents = studRepo.findAll();
		model.addAttribute("listStudnets", listStudents);
		
		return"students";
	}
	
	@GetMapping("/student_portal_main_page")
	public String studentPortal(Model model) {
		return "student_portal";
	}
	
//	@RequestMapping("/edit/{id}")
//	public ModelAndView showEditStudentForm(@PathVariable(name = "id") Long id) {
//		ModelAndView mav = new ModelAndView("student_update_form");
//		Student student = new service.get(id);
//		mav.addObject("Student", student);
//		
//		return mav;
//	}
//	
//	@RequestMapping("/delete/{id}")
//	public String deleteStudent(@PathVariable(name = "id") Long id) {
//		service.delete(id);
//		
//		return "/redirect:/";
//	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody String deleteStudent(@PathVariable Long id) {
		studRepo.deleteById(id);
		return "students";
	}
	

}
