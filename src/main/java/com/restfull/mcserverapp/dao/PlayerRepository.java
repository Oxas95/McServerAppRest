package com.restfull.mcserverapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restfull.mcserverapp.bean.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {

}
