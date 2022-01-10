package com.watolua.mcserverapp.httpstatus.internalerror;

public class InternalServerErrorException extends RuntimeException {

	public InternalServerErrorException() {
		super("Internal server error. Contact the admin to notify him the error.");
	}
}
