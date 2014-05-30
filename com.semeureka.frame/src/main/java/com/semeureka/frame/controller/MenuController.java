package com.semeureka.frame.controller;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.semeureka.frame.entity.Menu;
import com.semeureka.frame.service.MenuService;

@Controller
public class MenuController {
	public static Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuService;

	@RequestMapping(value = { "" })
	public String home() {
		return "redirect:" + menuService.findById(0).getDefaultUrl();
	}

	@RequestMapping(value = "/menu/new", method = RequestMethod.POST)
	public String save(Menu menu) {
		menuService.save(menu);
		return "redirect:/menu";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String find(Model model) {
		model.addAttribute("menus", menuService.findById(0).getChildren(new LinkedList<Menu>()));
		return "menu";
	}

	@RequestMapping(value = "/menu/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id, Menu menu) {
		if (id.equals(menu.getId())) {
			menuService.update(menu);
		} else {
			throw new UnsupportedOperationException(); // TODO 交换菜单
		}
		return "redirect:/menu";
	}

	@RequestMapping(value = "/menu/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		menuService.deleteById(id);
		return "redirect:/menu";
	}
}
