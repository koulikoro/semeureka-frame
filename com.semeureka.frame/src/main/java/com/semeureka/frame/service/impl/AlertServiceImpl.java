package com.semeureka.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semeureka.frame.entity.Alert;
import com.semeureka.frame.repository.AlertRepository;
import com.semeureka.frame.service.AlertService;

@Service("alertService")
@Transactional
public class AlertServiceImpl implements AlertService {
	@Autowired
	private AlertRepository alertRepository;

	@Override
	public List<Alert> findAll() {
		return alertRepository.findByUpdateTimeIsNull();
	}

	@Override
	public Alert findOne(Integer id) {
		return alertRepository.findOne(id);
	}

	@Override
	public void update(Alert alert) {
		alertRepository.save(alert);
	}
}
