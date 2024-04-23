package com.authentication.authentication.with.roles.repository;

import com.authentication.authentication.with.roles.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser, String> {
}
