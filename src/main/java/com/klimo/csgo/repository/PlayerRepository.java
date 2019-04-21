package com.klimo.csgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klimo.csgo.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
