package net.portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepo;
	
	public void save(Student student) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(student.getPassword());
		student.setPassword(encodedPassword);

		studentRepo.save(student);
	}
	
	public List<Student> listAll(){
		return studentRepo.findAll();
	}
	
	public Student get(Long student_id) {
		return studentRepo.findById(student_id).get();
	}
	
	public Student edit(Long student_id) {
		return studentRepo.findById(student_id).get();
	}
	
	public Student delete(Long student_id) {
		return studentRepo.findById(student_id).get();
	}
	
}
