package com.klimo.csgo.domain.enums;

import java.util.Arrays;

public enum MatchFormat {

	BO1(1),
	BO3(3),
	BO5(5);

	private int gamesCount;

	private MatchFormat(int gamesCount) {
		this.gamesCount = gamesCount;
	}

	public int getGamesCount() {
		return gamesCount;
	}

	public static MatchFormat parse(String text) {
		return Arrays.stream(values())
				.parallel()
				.filter(value -> value.name().equalsIgnoreCase(text))
				.findAny()
				.get();
	}

}
