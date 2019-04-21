package com.klimo.csgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klimo.csgo.domain.Team;
import com.klimo.csgo.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepo;

	public Team loadOrCreateTeam(String name) {
		Team team = teamRepo.findByName(name);
		if(team != null)
			return team;

		team = new Team();
		team.setName(name);
		return teamRepo.saveAndFlush(team);
	}

}
