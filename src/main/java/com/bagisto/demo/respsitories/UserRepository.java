package com.bagisto.demo.respsitories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bagisto.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
