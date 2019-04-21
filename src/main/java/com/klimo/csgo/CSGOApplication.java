package com.klimo.csgo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.klimo.csgo.domain.Match;
import com.klimo.csgo.repository.MatchRepository;
import com.klimo.csgo.service.HLTVService;

@SpringBootApplication
public class CSGOApplication implements InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(CSGOApplication.class);

	@Autowired
	private MatchRepository matchRepo;

	@Autowired
	private HLTVService hltvService;

	public static void main(String[] args) {
		SpringApplication.run(CSGOApplication.class, args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// weg damit
		List<Match> upcoming = hltvService.loadUpcomingMatches();

		/*
		 * passendes match holen
		 * -> history der teams holen
		 * -> bis sich der team core ändert (= mind. 3 oder 4 spieler gleich)
		 * -> map vetos holen
		 * -> in ml algorithmus
		 * -> ergebnis evtl per mail schicken
		 */

		log.info("found matches: " + upcoming.size());

		matchRepo.saveAll(upcoming);
		matchRepo.flush();
	}



}
