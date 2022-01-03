package com.restfull.mcserverapp.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {
	
	public static final  String DISCONNECTED_TOKEN = "0000";
	
	private @Id String username;
	private String token;
	private Autorisation autorisation;
	
	public Player() {}
	
	public Player(String username, String token, Autorisation autorisation) {
		setUsername(username);
		setToken(token);
		setAutorisation(autorisation);
	}
	
	public Player(String username) {
		this(username,DISCONNECTED_TOKEN, Autorisation.INITIAL);
	}
	
	public Player(String username, Autorisation autorisation) {
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
	public Autorisation getAutorisation() {
		return autorisation;
	}
	public void setAutorisation(Autorisation autorisation) {
		this.autorisation = autorisation;
	}
	public void setDisconnected() {
		setToken(DISCONNECTED_TOKEN);
	}
	
	@Override
	public String toString() {
		return "Player [username=" + username + ", token=" + token + ", autorisation=" + autorisation + "]";
	}
	
	public boolean isAdmin() {
		return this.autorisation.equals(Autorisation.ADMIN);
	}
}
