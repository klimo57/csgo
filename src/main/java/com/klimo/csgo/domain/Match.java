package com.klimo.csgo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.klimo.csgo.domain.enums.MatchFormat;

@Entity
public class Match {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private MatchFormat format;

	@OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Game> games = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team1_id")
	private Team team1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team2_id")
	private Team team2;

	private LocalDateTime date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MatchFormat getFormat() {
		return format;
	}

	public void setFormat(MatchFormat format) {
		this.format = format;
	}

	public List<Game> getGames() {
		return games;
	}

	public void addGame(Game game) {
		getGames().add(game);
		game.setMatch(this);
	}

	public void removeGame(Game game) {
		getGames().remove(game);
		game.setMatch(this);
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
