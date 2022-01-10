package com.watolua.mcserverapp.httpstatus.internalerror;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InternalServerErrorAdvice {

	@ResponseBody
	@ExceptionHandler(InternalServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String DeniedAccessHandler(InternalServerErrorException ex) {
		return ex.getMessage();
	}
}
