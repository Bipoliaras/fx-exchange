package com.ernestas.danske.fxexchange.exception;

public class WebException extends RuntimeException {

  protected String code;

  public WebException(String message) {
    super(message);
  }

}

