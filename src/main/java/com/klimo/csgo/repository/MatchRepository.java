package com.klimo.csgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klimo.csgo.domain.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
