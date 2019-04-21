package com.klimo.csgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klimo.csgo.domain.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
