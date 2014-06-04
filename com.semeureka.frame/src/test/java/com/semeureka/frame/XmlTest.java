package com.semeureka.frame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.semeureka.frame.entity.Menu;

public class XmlTest {
	public static void main(String[] args) throws Exception {
//		XmlMapper xmlMapper = new XmlMapper();
//		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		Menu menu = new Menu();
		menu.setName("NAME");
		menu.setUrl("URL");
		System.out.println(mapper.writeValueAsString(menu));
//		System.out.println(xmlMapper.writeValueAsString(menu));
	}
}
