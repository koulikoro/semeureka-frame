package com.semeureka.frame.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.semeureka.frame.entity.Alert;

public interface AlertService {
	void update(Alert alert);

	void save(Alert alert);

	Page<Alert> findLastOne();

	Page<Alert> findAll(Pageable pageable);

	Alert findOne(Integer id);
}
