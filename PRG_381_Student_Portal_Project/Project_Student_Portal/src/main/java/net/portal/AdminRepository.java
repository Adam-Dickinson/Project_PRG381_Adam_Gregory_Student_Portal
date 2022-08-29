package net.portal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	@Query("SELECT a FROM Admin a WHERE a.name = ?1")
	Admin findByName(String name);
}
