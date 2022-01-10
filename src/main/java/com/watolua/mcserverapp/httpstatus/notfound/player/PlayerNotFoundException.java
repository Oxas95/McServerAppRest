package com.watolua.mcserverapp.httpstatus.notfound.player;

@SuppressWarnings("serial")
public class PlayerNotFoundException extends RuntimeException {

	public PlayerNotFoundException(String name) {
		super("Could not find player " + name);
	}
}
