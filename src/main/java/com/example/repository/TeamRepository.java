package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Team;



@Repository
public class TeamRepository {
	/**
	 * 
		Teamオブジェクトを生成するローマッパー.
	 */
	public static final RowMapper<Team> TEAM_ROW_MAPPER = (rs, i) -> {
		Team team = new Team();
		team.setId(rs.getInt("id"));
		team.setLeagueName(rs.getString("league_name"));
		team.setTeamName(rs.getString("team_name"));
		team.setHeadquarters(rs.getString("headquarters"));
		team.setInauguration(rs.getString("inauguration"));
		team.setHistory(rs.getString("history"));
		return team;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public Team load(Integer id) {
		String sql = "select * from teams where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Team team = template.queryForObject(sql, param, TEAM_ROW_MAPPER);
		return team;
	}
	
	public List<Team> findAll() {
		String sql = "SELECT id,league_name,team_name, headquarters, inauguration, history FROM teams ORDER BY inauguration ASC";

		List<Team> teamList = template.query(sql, TEAM_ROW_MAPPER);

		return teamList;
	}
	

	/**
	 * @param teamName
	 * @return
	 * 予備メゾット（名前検索）
	 */
	public Team findTeam(String teamName) {
		String sql = "select * from teams where team_name=:teamName";
		SqlParameterSource param = new MapSqlParameterSource().addValue("teamName", teamName);
		List<Team> teamList = template.query(sql, param, TEAM_ROW_MAPPER);
		if(teamList.size()==0) {
			return null;
		}
		return teamList.get(0);
	}
	
}
