package com.semeureka.frame.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.semeureka.frame.entity.Alert;

public class AlertSpecifications {
	public static Specification<Alert> byUpdateTimeIsNull() {
		return new Specification<Alert>() {
			@Override
			public Predicate toPredicate(Root<Alert> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.isNull(root.get("updateTime")));
				return cb.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
