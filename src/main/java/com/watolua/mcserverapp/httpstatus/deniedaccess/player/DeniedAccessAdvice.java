package com.watolua.mcserverapp.httpstatus.deniedaccess.player;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DeniedAccessAdvice {

	@ResponseBody
	@ExceptionHandler(DeniedAccessException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	String DeniedAccessHandler(DeniedAccessException ex) {
		return ex.getMessage();
	}
}
