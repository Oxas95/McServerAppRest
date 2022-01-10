package com.watolua.mcserverapp.httpstatus.serviceunavailable;

public class ServiceUnavailableException extends RuntimeException {

	public ServiceUnavailableException() {
		super("Unable to verify the connection. Contact the admin to notify him the error.");
	}
}
