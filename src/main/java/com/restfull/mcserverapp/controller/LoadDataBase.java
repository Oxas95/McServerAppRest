package com.restfull.mcserverapp.controller;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restfull.mcserverapp.bean.Autorisation;
import com.restfull.mcserverapp.bean.Player;
import com.restfull.mcserverapp.dao.PlayerRepository;

@Configuration
public class LoadDataBase {

	private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);
	
	private static Stream<String> admins = Stream.of("Oxas", "MonsieurM19", "CitronSplit", "Sw4rKz", "TheFrenchLama").parallel();
	
	@Bean
	CommandLineRunner initDatabase(PlayerRepository repository) {
		return args -> {
			admins.forEach(element -> {
				log.info("Preloading " + repository.save(new Player(element, Autorisation.ADMIN)));
			});
		};
	}
}
