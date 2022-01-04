package com.watolua.mcserverapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watolua.mcserverapp.bean.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {

}
