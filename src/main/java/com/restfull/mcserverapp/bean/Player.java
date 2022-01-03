package com.restfull.mcserverapp.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {
	
	public static final  String DISCONNECTED_TOKEN = "0000";
	
	public static final int BANNED = -1;
	public static final int INITIAL = 0;
	public static final int ASKED = 1;
	public static final int ALLOWED = 2;
	public static final int ADMIN = 3;
	
	private @Id String username;
	private String token;
	private int autorisation;
	
	public Player() {}
	
	public Player(String username, String token, int autorisation) {
		setUsername(username);
		setToken(token);
		setAutorisation(autorisation);
	}
	
	public Player(String username) {
		this(username,DISCONNECTED_TOKEN, 0);
	}
	
	public Player(String username, int autorisation) {
		this(username, DISCONNECTED_TOKEN, autorisation);
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
	public int getAutorisation() {
		return autorisation;
	}
	public void setAutorisation(int autorisation) {
		this.autorisation = autorisation;
	}
	public void setDisconnected() {
		setToken(DISCONNECTED_TOKEN);
	}
	
	@Override
	public String toString() {
		return "Player [username=" + username + ", token=" + token + ", autorisation=" + autorisation + "]";
	}
	
}
