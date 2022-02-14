package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Team;
import com.example.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {
	@Autowired
	private TeamService teamService;
	
	/**
	 * @param model
	 * @return
	 * 一覧ページを呼ぶ機能
	 */
	@RequestMapping("")
	public String showList(Model model) {
		List<Team> teamList=teamService.showList();
		model.addAttribute("teamList",teamList);
		return "teamList";
	}
	/**
	 * 詳細ページの情報収集
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Team team=teamService.showDetail(Integer.parseInt(id));
		model.addAttribute("team", team);
		return "teamDetail";
	}
}
