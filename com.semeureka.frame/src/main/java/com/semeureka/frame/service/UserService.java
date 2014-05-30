package com.semeureka.frame.service;

import java.util.List;

import com.semeureka.frame.entity.User;

public interface UserService {
	List<User> find();

	void save(User user);

	User findByUsername(String username);
}
