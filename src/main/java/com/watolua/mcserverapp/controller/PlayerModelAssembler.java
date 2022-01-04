package com.watolua.mcserverapp.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.watolua.mcserverapp.bean.Player;

@Component
public class PlayerModelAssembler implements RepresentationModelAssembler<Player, EntityModel<Player>> {

	@Override
	public EntityModel<Player> toModel(Player player) {
		return EntityModel.of(player,
				linkTo(methodOn(PlayerController.class).one(player.getUsername())).withSelfRel(),
				linkTo(methodOn(PlayerController.class).all()).withRel("players"));
	}

}
