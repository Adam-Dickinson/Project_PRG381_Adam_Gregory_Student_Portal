package net.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomStudentDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Student student = repo.findByEmail(email);
		if(student == null) {
			throw new UsernameNotFoundException("Student Not Found");
		}
		return new CustomStudentDetails(student);
	}

}
