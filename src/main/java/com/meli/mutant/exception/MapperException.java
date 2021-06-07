package com.meli.mutant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MapperException extends RuntimeException {

  public MapperException(final String message) {
    super(message);
  }

  public MapperException(final String message, Throwable cause) {
    super(message, cause);
  }

}
