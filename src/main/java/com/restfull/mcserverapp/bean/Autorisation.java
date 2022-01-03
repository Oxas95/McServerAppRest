package com.restfull.mcserverapp.bean;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Autorisation {
	BANNED((short) -1,"banned"),
	INITIAL((short) 0, "initial"),
	ASKED((short) 1, "asked"),
	ALLOWED((short) 2, "allowed"),
	ADMIN((short) 3, "admin");
	
	private short autorisationLevel;
	private String string;
	
	Autorisation(short i, String string) {
		this.autorisationLevel = i;
		this.string = string;
	}
	
	public String toString() {
		return this.string;
	}
	
	public short getAutorisationLevel() {
		return this.autorisationLevel;
	}
	
	public boolean equals(Autorisation autorisation) {
		return this.autorisationLevel == autorisation.getAutorisationLevel();
	}
	
	public static Autorisation getAutorisation(short autorisationLevel) {
		Autorisation[] autorisations = Autorisation.values();
		List<Autorisation> result = Arrays.stream(autorisations)
				.filter(element -> element.getAutorisationLevel() == autorisationLevel)
				.collect(Collectors.toList());
		if(result.size() == 1)
			return result.get(0);
		else return null;
	}
}
