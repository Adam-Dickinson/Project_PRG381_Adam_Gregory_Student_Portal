package net.portal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AdminTestRepository {
	@Autowired
	private AdminRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateAdmin() {
		Admin admin = new Admin();
		admin.setName("John Peter");
		admin.setPassword("john1234");
		admin.setContact("0215498762");
		
		Admin savedStudent = repo.save(admin);
		
		Admin existStudent = entityManager.find(Admin.class, savedStudent.getId());
		
		assertThat(existStudent.getName()).isEqualTo(admin.getName());
	}
}
