package com.semeureka.frame.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionOperations;

import com.semeureka.frame.entity.Menu;
import com.semeureka.frame.repository.MenuRepository;
import com.semeureka.frame.service.MenuService;

@Transactional
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	private static final int MAX_SUBMENU_COUNT = 10;
	public static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private TransactionOperations txOperations;
	private List<Menu> menus;

	@Override
	@PostConstruct
	public void init() {
		txOperations.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				refreshCache();
			}
		});
	}

	private void refreshCache() {
		menus = menuRepository.findAll();
	}

	@Override
	public Menu findById(Integer id) {
		for (Menu menu : menus) {
			if (menu.getId().equals(id)) {
				return menu;
			}
		}
		return null;
	}

	@Override
	public Menu findByUrl(String url) {
		for (Menu menu : menus) {
			if (menu.getLevel().equals(3) && StringUtils.contains(url, menu.getUrl())) {
				return menu;
			}
		}
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		menuRepository.delete(id);
		refreshCache();
	}

	@Override
	public void update(Menu menu) {
		Menu menu2 = menuRepository.findOne(menu.getId());
		menu2.setName(menu.getName());
		menu2.setUrl(menu.getUrl());
		menuRepository.save(menu2);
		refreshCache();
	}

	@Override
	public void save(Menu menu) {
		resetParent(menu);
		menuRepository.save(menu);
		refreshCache();
	}

	private void resetParent(Menu menu) {
		if (!menu.getId().equals(0)) {
			menu.setParent(findById(menu.getId() / MAX_SUBMENU_COUNT));
		} else {
			menu.setParent(null);
		}
	}
}
