package com.semeureka.frame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semeureka.frame.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
