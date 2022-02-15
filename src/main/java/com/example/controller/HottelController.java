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
	public String hotelSerch(String price,Model model) {
		List<Hotel> hotelList = null;
		// if(Objects.isNull(price))でよく使われる
		if (price.isEmpty()) {
			hotelList = service.findAll();
		}else {try {
			hotelList=service.findSerch(Integer.parseInt(price));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			model.addAttribute("numberFormatError", "※整数で指定してください");
			e.printStackTrace();
			return "hotelList";
		}			
		}
		model.addAttribute("hotelList", hotelList);
		return "hotelList";
	}
}
