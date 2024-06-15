package ch.fhnw.carrental.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.carrental.data.domain.Admin;

@Repository
//JpaRepository should be typed to the domain class and an ID type
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findAdminByAdminEmail(String adminEmail);
    List<Admin> findAllByAdminEmailContainsIgnoreCase(String adminEmail);
}
