package com.restfull.mcserverapp.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restfull.mcserverapp.bean.Autorisation;
import com.restfull.mcserverapp.bean.Player;
import com.restfull.mcserverapp.dao.PlayerRepository;
import com.restfull.mcserverapp.notfound.Exception.PlayerNotFoundException;

@RestController
public class PlayerController {

	private final PlayerRepository repository;
	private final PlayerModelAssembler assembler;
	
	public PlayerController(PlayerRepository repository, PlayerModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@GetMapping("/players")
	CollectionModel<EntityModel<Player>> all() {
		List<EntityModel<Player>> players = repository.findAll().stream()
				.map(assembler::toModel).collect(Collectors.toList());
		return CollectionModel.of(players, linkTo(methodOn(PlayerController.class).all()).withSelfRel());
	}
	
	@PostMapping("/player")
	ResponseEntity<?> newPlayer(@RequestBody Player player) {
		player.setAutorisation(Autorisation.INITIAL);
		EntityModel<Player> entityModel = assembler.toModel(repository.save(player));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	@GetMapping("/player/{username}")
	EntityModel<Player> one(@PathVariable String username) {
		Player player = repository.findById(username)
				.orElseThrow(() -> new PlayerNotFoundException(username));
		return assembler.toModel(player);
	}
	
	@PutMapping("/player/{username}")
	ResponseEntity<?> replacePlayer(@RequestBody Player player, @RequestParam(value = "username") String username) {
		Player updatedPlayer = repository.findById(username)
				.map( element -> {
					element.setAutorisation(player.getAutorisation());
					return repository.save(element);
				}).orElseGet( () -> {
					return repository.save(player);
				});
		
		EntityModel<Player> entityModel = assembler.toModel(updatedPlayer);

		  return ResponseEntity //
		      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		      .body(entityModel);
	}
	
	@DeleteMapping("/player/{username}")
	ResponseEntity<?> deletePlayer(@PathVariable String username) {
		repository.deleteById(username);
		return ResponseEntity.noContent().build();
	}
}
