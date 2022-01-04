package com.watolua.mcserverapp.notfound.exception;

@SuppressWarnings("serial")
public class PlayerNotFoundException extends RuntimeException {

	public PlayerNotFoundException(String name) {
		super("Could not find player " + name);
	}
}