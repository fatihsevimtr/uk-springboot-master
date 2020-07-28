package com.exerate.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Exception class to handle  the exception if IP not found in DB
 */


@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "No Ip Found in DB")
public class IPNotFoundException extends RuntimeException {

    // private  String message;

}
