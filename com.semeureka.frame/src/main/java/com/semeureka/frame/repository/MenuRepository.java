package com.semeureka.frame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.semeureka.frame.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
	@Override
	@Query("select distinct m from Menu m left join fetch m.children")
	public List<Menu> findAll();
}
