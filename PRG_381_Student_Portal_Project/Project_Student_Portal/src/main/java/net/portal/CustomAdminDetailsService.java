package net.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomAdminDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Admin admin = repo.findByName(name);
		if(admin == null) {
			throw new UsernameNotFoundException("Admin Not Found");
		}
		return new CustomAdminDetails(admin);
	}

}
