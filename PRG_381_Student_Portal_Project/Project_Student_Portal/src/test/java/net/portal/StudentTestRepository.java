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
public class StudentTestRepository {
	@Autowired
	private StudentRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateStudent() {
		Student student = new Student();
		student.setAddress("22 Tinderwood Screscent");
		student.setCourse_code("PRG381");
		student.setEmail("adamgamesyt@gmail.com");
		student.setName("Adam Dickinson");
		student.setPassword("adam1234");
		
		
		Student savedStudent = repo.save(student);
		
		Student existStudent = entityManager.find(Student.class, savedStudent.getId());
		
		assertThat(existStudent.getEmail()).isEqualTo(student.getEmail());
	}
}
