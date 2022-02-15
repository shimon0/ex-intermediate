package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Clothe;
import com.example.service.ClotheService;

@Controller
@RequestMapping("/clothe")
public class ClotheController {
	@Autowired
	private ClotheService service;
	
	@RequestMapping("")
	public String index() {
		return "clotheList";
	}
	@RequestMapping("/search")
	public String serchSelect(Integer gender,String color,Model model) {
		List<Clothe> clotheList=service.searchSelect(gender, color);
		model.addAttribute("clotheList",clotheList);
		return "clotheList";
	}
}
