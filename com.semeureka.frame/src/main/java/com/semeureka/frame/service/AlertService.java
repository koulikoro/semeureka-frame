package com.semeureka.frame.service;

import java.util.List;

import com.semeureka.frame.entity.Alert;

public interface AlertService {
	List<Alert> findAll();

	Alert findOne(Integer id);

	void update(Alert alert);
}
