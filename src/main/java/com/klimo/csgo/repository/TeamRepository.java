package com.klimo.csgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klimo.csgo.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

	public Team findByName(String name);

}
