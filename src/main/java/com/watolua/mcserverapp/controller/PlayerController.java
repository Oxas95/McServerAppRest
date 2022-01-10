package com.watolua.mcserverapp.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.http.client.ClientProtocolException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watolua.mcserverapp.bean.Access;
import com.watolua.mcserverapp.bean.Player;
import com.watolua.mcserverapp.dao.PlayerRepository;
import com.watolua.mcserverapp.httpstatus.deniedaccess.player.DeniedAccessException;
import com.watolua.mcserverapp.httpstatus.internalerror.InternalServerErrorException;
import com.watolua.mcserverapp.httpstatus.notfound.player.PlayerNotFoundException;
import com.watolua.mcserverapp.httpstatus.serviceunavailable.ServiceUnavailableException;
import com.watolua.mcserverapp.httpstatus.unauthorized.player.PlayerUnauthorizedException;

@RestController
public class PlayerController implements MinecraftAuth{

	private final PlayerRepository repository;
	private final PlayerModelAssembler assembler;
	
	public PlayerController(PlayerRepository repository, PlayerModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
	
	private Player getBDDPlayer(Player player) {
		return repository.findById(player.getUsername())
		.orElseThrow(() -> new PlayerNotFoundException(player.getUsername()));
	}
	
	private boolean checkToken(Player playerFound, Player playerGiven) {
		return playerFound.getToken().equals(playerGiven.getToken())
		&& !playerFound.getToken().equals(Player.DISCONNECTED_TOKEN);
	}
	
	private boolean isAdmin(Player player) {
		Player playerFound = getBDDPlayer(player);
		return checkToken(playerFound, player) && playerFound.isAdmin();
	}
	
	private boolean isAllowed(Player player) {
		Player playerFound = getBDDPlayer(player);
		return checkToken(playerFound, player) && playerFound.isAllowed();
	}
	
	private boolean isHim(Player player) {
		return checkToken(getBDDPlayer(player), player);
	}
	
	@GetMapping("/players")
	CollectionModel<EntityModel<Player>> all(@RequestBody Player player) {
		if(isAllowed(player)) {
			List<EntityModel<Player>> players = repository.findAll().stream()
					.map(assembler::toModel)
					.collect(Collectors.toList());
			players.forEach(element -> element.getContent().setDisconnected());
			return CollectionModel.of(players, linkTo(methodOn(PlayerController.class).all(player)).withSelfRel());
		} else {
			throw new DeniedAccessException();
		}
	}
	
	@PostMapping("/player")
	ResponseEntity<?> newPlayer(@RequestBody Player player) {
		player.setAccess(Access.INITIAL);
		player.setDisconnected();
		EntityModel<Player> entityModel = assembler.toModel(repository.save(player));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	@PostMapping("/player/auth")
	ResponseEntity<?> one(@RequestBody Player player) {
		String newToken;
		try {
			newToken = this.requestMinecraftId(player.getUsername(), player.getToken());
		} catch (ClientProtocolException e) {
			throw new InternalServerErrorException();
		} catch (IOException e) {
			throw new ServiceUnavailableException();
		}
		player.setToken(newToken);
		
		EntityModel<Player> entityModel = assembler.toModel(player);

		return ResponseEntity //
		      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		      .body(entityModel);
	}
	
	@PutMapping("/player/{username}")
	ResponseEntity<?> replacePlayer(@RequestBody Player player, @RequestParam String username, @RequestParam short access) {
		if(isAdmin(player)) {
			Player updatedPlayer = repository.findById(username)
					.map( element -> {
						element.setAccess(player.getAccess());
						return repository.save(element);
					}).orElseGet( () -> {
						player.setAccess(Access.INITIAL);
						player.setDisconnected();
						return repository.save(player);
					});
			
			EntityModel<Player> entityModel = assembler.toModel(updatedPlayer);
	
			return ResponseEntity //
			      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
			      .body(entityModel);
		} else {
			throw new DeniedAccessException();
		}
	}
	
	@DeleteMapping("/player/auth/{username}")
	ResponseEntity<?> deletePlayer(@RequestBody Player player) {
		if(isHim(player)) {
			player.setDisconnected();
			repository.save(player);
			return ResponseEntity.noContent().build();
		} else {
			throw new PlayerUnauthorizedException();
		}
	}
}
