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
		team.setLeagueName(rs.getString("leagueName"));
		team.setTeamName(rs.getString("teamName"));
		team.setHeadquarters(rs.getString("headquarters"));
		team.setInauguration(rs.getString("inauguration"));
		team.setHistory(rs.getString("history"));
		return team;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	public Team load(String leagueName) {
		String sql = "select * from teams where league_name=:leagueName";
		SqlParameterSource param = new MapSqlParameterSource().addValue("leagueName", leagueName);
		List<Team> teamList = template.query(sql, param, TEAM_ROW_MAPPER);
		if(teamList.size()==0) {
			return null;
		}
		return teamList.get(0);
	}
	
}
