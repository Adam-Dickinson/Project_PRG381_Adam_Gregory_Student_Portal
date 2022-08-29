package net.portal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Student findByEmail(String email);
}

