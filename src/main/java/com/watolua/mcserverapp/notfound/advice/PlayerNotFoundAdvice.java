package com.watolua.mcserverapp.notfound.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.watolua.mcserverapp.notfound.exception.PlayerNotFoundException;

public class PlayerNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(PlayerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(PlayerNotFoundException ex) {
		return ex.getMessage();
	}
}
