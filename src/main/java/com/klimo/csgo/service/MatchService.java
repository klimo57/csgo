package com.klimo.csgo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.klimo.csgo.domain.Game;
import com.klimo.csgo.domain.Match;
import com.klimo.csgo.domain.Team;
import com.klimo.csgo.domain.enums.Map;
import com.klimo.csgo.domain.enums.MatchFormat;

@Service
public class MatchService {

	public Match match(Team team1, Team team2, MatchFormat format, LocalDateTime date, Map... maps) {
		if(format == null)
			throw new IllegalArgumentException("format cannot be null!");
		Match match = new Match();
		match.setFormat(format);
		addGames(match, team1, team2, maps);
		match.setDate(date);
		return match;
	}

	public void addGames(Match match, Team team1, Team team2, Map... maps) {
		for(int i = 0; i < match.getFormat().getGamesCount(); i++) {
			Game game = new Game();
			game.setTeam1(team1);
			game.setTeam2(team2);
			if(maps != null && i < maps.length) {
				game.setMap(maps[i]);
			}
			match.addGame(game);
		}
	}

}
