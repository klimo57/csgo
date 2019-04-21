package com.klimo.csgo.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.klimo.csgo.domain.enums.Map;

@Entity
public class Game {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_id")
	private Match match;

	@Enumerated(EnumType.STRING)
	private Map map;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team1_id")
	private Team team1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team2_id")
	private Team team2;

	private Integer scoreTeam1;

	private Integer scoreTeam2;

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Game other = (Game)obj;
		if(id == null) {
			if(other.id != null)
				return false;
		} else if(!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public Integer getScoreTeam1() {
		return scoreTeam1;
	}

	public void setScoreTeam1(Integer scoreTeam1) {
		this.scoreTeam1 = scoreTeam1;
	}

	public Integer getScoreTeam2() {
		return scoreTeam2;
	}

	public void setScoreTeam2(Integer scoreTeam2) {
		this.scoreTeam2 = scoreTeam2;
	}

}
