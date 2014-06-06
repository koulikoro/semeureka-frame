package com.semeureka.frame.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semeureka.frame.entity.Alert;
import com.semeureka.frame.service.AlertService;

@Controller
@RequestMapping(value = "/alert")
public class AlertController {
	@Autowired
	private AlertService alertService;

	@RequestMapping(value = "")
	public String alert(@SortDefault(direction = Direction.DESC, sort = "id") Pageable pageable,
			Model model) {
		model.addAttribute("alerts", alertService.findAll(pageable));
		return "/alert/alert";
	}

	@RequestMapping(value = "/last")
	public String last(Model model) {
		model.addAttribute("lasts", alertService.findLastOne());
		return "/alert/last";
	}

	@RequestMapping(value = "/{id}")
	public String deal(@PathVariable Integer id, Model model) {
		Alert alert = alertService.findOne(id);
		alert.setUpdateTime(new Date());
		alertService.update(alert);
		return "redirect:" + (alert.getUrl() != null ? alert.getUrl() : "/");
	}
}
