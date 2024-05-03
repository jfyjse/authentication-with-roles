package com.authentication.authentication.with.roles.repository;

import com.authentication.authentication.with.roles.dto.UserListDTO;
import com.authentication.authentication.with.roles.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {


    Optional<ApplicationUser> findByUsername(String username);

    @Query(value = "select * from users", nativeQuery = true)
    List<UserListDTO> findAllUsers();
}