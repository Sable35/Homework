package com.example.webapplicationexample.repositories;

import com.example.webapplicationexample.entities.ERole;
import com.example.webapplicationexample.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
