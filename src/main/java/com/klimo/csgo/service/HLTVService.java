package com.klimo.csgo.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klimo.csgo.domain.Match;
import com.klimo.csgo.domain.Team;
import com.klimo.csgo.domain.enums.MatchFormat;
import com.klimo.csgo.exception.ConnectionException;
import com.klimo.csgo.exception.HLTVParsingException;
import com.klimo.csgo.hltv.html.HTMLClass;
import com.klimo.csgo.hltv.url.EventType;
import com.klimo.csgo.hltv.url.HLTVUrlBuilder;
import com.klimo.csgo.hltv.url.Site;
import com.klimo.csgo.hltv.url.Status;
import com.klimo.csgo.hltv.url.UrlBuilder;

@Service
public class HLTVService {

	private static final Logger log = LoggerFactory.getLogger(HLTVService.class);

	@Autowired
	private TeamService teamService;

	public List<Match> loadUpcomingMatches() throws ConnectionException {
		Document doc = getSite(new HLTVUrlBuilder()
								.setSite(Site.MATCHES)
								.addStatus(Status.UPCOMING)
								.addEventType(EventType.MAJOR)
								.addEventType(EventType.INTERNATIONAL_LAN)
								.addEventType(EventType.REGIONAL_LAN));

		log.info("got site: " + doc.toString());

		return doc.getElementsByClass(HTMLClass.MATCHDAY.getName())
				.parallelStream()
				.flatMap(
					matchDayElement -> {
						String day = matchDayElement.getElementsByClass(HTMLClass.DATEFIELD.getName()).get(0).text();
						return matchDayElement.getElementsByClass(HTMLClass.UPCOMING_MATCH.getName())
								.stream()
								.map(matchElement -> loadUpcomingMatch(matchDayElement, day));
					})
				.collect(Collectors.toList());
	}

	private Match loadUpcomingMatch(Element matchElement, String day) {
		Optional<Element> timeElement = matchElement.getElementsByClass(HTMLClass.TIMEFIELD.getName())
										.parallelStream()
										.filter(elem -> elem.text().matches("[0-2][0-9]:[0-9]{2}"))
										.findAny();
		String formatString = matchElement.getElementsByClass(HTMLClass.FORMATFIELD.getName()).get(0).text();

		List<String> teamNames = matchElement.getElementsByClass(HTMLClass.TEAMFIELD.getName())
									.parallelStream()
									.map(teamElement -> teamElement.text())
									.collect(Collectors.toList());

		if(timeElement.isEmpty())
			log.warn("no time found");

		if(teamNames.size() < 2)
			throw new HLTVParsingException("not enough teams found");

		log.info("team 1: " + teamNames.get(0) + " (" + teamNames.size() + ")");
		Team team1 = teamService.loadOrCreateTeam(teamNames.get(0));
		log.info("team 2: " + teamNames.get(1));
		Team team2 = teamService.loadOrCreateTeam(teamNames.get(1));

		Match match = new Match();
		match.setTeam1(team1);
		match.setTeam2(team2);
		match.setDate(LocalDateTime.parse(day + "T" + timeElement.get().text()));
		match.setFormat(MatchFormat.parse(formatString));
		return match;
	}

	private Document getSite(UrlBuilder urlBuilder) throws ConnectionException {
		return getSite(urlBuilder, false);
	}

	private Document getSite(UrlBuilder urlBuilder, boolean mask) throws ConnectionException {
		urlBuilder.setMethod("get");
		String url = urlBuilder.build();
		log.info("connecting to " + url);
		Connection connection = Jsoup.connect(url);
		if(mask)
			connection.headers(urlBuilder.headers());
		try {
			return connection.get();
		} catch(IOException e) {
			if(e instanceof HttpStatusException && !mask) {
				log.info("connection rejected, will try masking...");
				return getSite(urlBuilder, true);
			}
			throw new ConnectionException("could not connect: " + url, e);
		}
	}

}
