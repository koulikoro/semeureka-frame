package com.semeureka.frame.service;

import com.semeureka.frame.entity.Menu;

public interface MenuService {
	Menu findById(Integer id);

	void update(Menu menu);

	void save(Menu menu);

	void deleteById(Integer id);

	void init();

	Menu findByUrl(String requestURI);
}
