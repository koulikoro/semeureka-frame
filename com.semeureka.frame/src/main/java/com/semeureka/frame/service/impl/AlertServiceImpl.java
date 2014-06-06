package com.semeureka.frame.service.impl;

import static com.semeureka.frame.repository.spec.AlertSpecifications.byUpdateTimeIsNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.semeureka.frame.entity.Alert;
import com.semeureka.frame.repository.AlertRepository;
import com.semeureka.frame.service.AlertService;

@Service("alertService")
@Transactional
public class AlertServiceImpl implements AlertService {
	private Pageable lastOnePageable = new PageRequest(0, 1, Direction.DESC, "id");
	@Autowired
	private AlertRepository alertRepository;

	@Override
	public Page<Alert> findAll(Pageable pageable) {
		return alertRepository.findAll(pageable);
	}

	@Override
	public void update(Alert alert) {
		alertRepository.save(alert);
	}

	@Override
	public void save(Alert alert) {
		alertRepository.save(alert);
	}

	@Override
	public Page<Alert> findLastOne() {
		return alertRepository.findAll(byUpdateTimeIsNull(), lastOnePageable);
	}

	@Override
	public Alert findOne(Integer id) {
		return alertRepository.findOne(id);
	}
}
