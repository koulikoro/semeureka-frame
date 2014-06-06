package com.semeureka.frame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.semeureka.frame.entity.Alert;

public interface AlertRepository extends JpaRepository<Alert, Integer>,
		JpaSpecificationExecutor<Alert> {
}
