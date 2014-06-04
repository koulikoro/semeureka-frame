package com.semeureka.frame.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import com.semeureka.frame.entity.Alert;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	public List<Alert> findByUpdateTimeIsNull();
}
