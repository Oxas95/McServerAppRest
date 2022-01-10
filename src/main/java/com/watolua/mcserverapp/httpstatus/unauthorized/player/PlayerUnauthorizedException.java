package com.watolua.mcserverapp.httpstatus.unauthorized.player;

public class PlayerUnauthorizedException extends RuntimeException {

	public PlayerUnauthorizedException() {
		super("You must be logged before doing this");
	}
}
