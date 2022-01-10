package com.watolua.mcserverapp.httpstatus.deniedaccess.player;

public class DeniedAccessException extends RuntimeException {

	public DeniedAccessException() {
		super("You are not able to access to this resource");
	}
}
