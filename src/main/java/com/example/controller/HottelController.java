package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Hotel;
import com.example.service.HotelService;

@Controller
@RequestMapping("/hotel")
public class HottelController {
	@Autowired
	private HotelService service;
	
	@RequestMapping("")
	public String index() {
		return "hotelList";
	}
	@RequestMapping("/search")
	public String hotelSerch(Integer price,Model model) {
		List<Hotel> hotelList=service.findSerch(price);
		model.addAttribute("hotelList", hotelList);
		return "hotelList";

	}
}
