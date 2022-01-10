package com.watolua.mcserverapp.httpstatus.serviceunavailable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ServiceUnavailableAdvice {

	@ResponseBody
	@ExceptionHandler(ServiceUnavailableException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	String DeniedAccessHandler(ServiceUnavailableException ex) {
		return ex.getMessage();
	}
}
