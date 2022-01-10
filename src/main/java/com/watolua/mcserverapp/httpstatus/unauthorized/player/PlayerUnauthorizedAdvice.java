package com.watolua.mcserverapp.httpstatus.unauthorized.player;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PlayerUnauthorizedAdvice {

	@ResponseBody
	@ExceptionHandler(PlayerUnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	String playerUnauthorizedHandler(PlayerUnauthorizedException ex) {
		return ex.getMessage();
	}
}
