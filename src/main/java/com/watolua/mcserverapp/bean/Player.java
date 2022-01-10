package com.watolua.mcserverapp.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {
	
	public static final  String DISCONNECTED_TOKEN = "0000";
	
	private @Id String username;
	private String token;
	private Access access;
	
	public Player() {}
	
	public Player(String username, String token, Access access) {
		setUsername(username);
		setToken(token);
		setAccess(access);
	}
	
	public Player(String username) {
		this(username,DISCONNECTED_TOKEN, Access.INITIAL);
	}
	
	public Player(String username, Access access) {
		this(username, DISCONNECTED_TOKEN, access);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Access getAccess() {
		return access;
	}
	public void setAccess(Access access) {
		this.access = access;
	}
	public void setDisconnected() {
		setToken(DISCONNECTED_TOKEN);
	}
	
	@Override
	public String toString() {
		return "Player [username=" + username + ", token=" + token + ", access=" + access + "]";
	}
	
	public boolean isAdmin() {
		return this.access.equals(Access.ADMIN);
	}
	
	public boolean isAllowed() {
		return this.access.getAccessLevel() >= Access.ALLOWED.getAccessLevel();
	}
}
