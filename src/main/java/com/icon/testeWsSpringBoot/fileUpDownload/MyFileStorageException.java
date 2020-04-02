package com.icon.testeWsSpringBoot.fileUpDownload;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class MyFileStorageException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MyFileStorageException(String exception) {
		super(exception);
	}
	public MyFileStorageException(String exception, Throwable cause) {
		super(exception, cause);
	}
	
}
