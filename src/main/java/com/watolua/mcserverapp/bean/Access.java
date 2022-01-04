package com.watolua.mcserverapp.bean;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Access {
	BANNED	((short) 0),
	INITIAL	((short) 1),
	ASKED	((short) 2),
	ALLOWED	((short) 3),
	ADMIN	((short) 4);
	
	private short accessLevel;
	
	Access(short i) {
		this.accessLevel = i;
	}
	
	public String toString() {
		return this.name();
	}
	
	public short getAccessLevel() {
		return this.accessLevel;
	}
	
	public boolean equals(Access access) {
		return this.accessLevel == access.getAccessLevel();
	}
	
	public static Access getAccess(short accessLevel) {
		Access[] accesses = Access.values();
		List<Access> result = Arrays.stream(accesses)
				.filter(element -> element.getAccessLevel() == accessLevel)
				.collect(Collectors.toList());
		if(result.size() == 1)
			return result.get(0);
		else return null;
	}
}
