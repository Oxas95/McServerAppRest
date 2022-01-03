package com.restfull.mcserverapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.restfull.mcserverapp.dao.PlayerRepository;

@RestController
public class PlayerController {

	private final PlayerRepository repository;
	
	public PlayerController(PlayerRepository repository) {
		this.repository = repository;
	}
}
