package com.ernestas.danske.fxexhange.exception;

public class WebException extends RuntimeException {

  protected String code;

  public WebException(String message) {
    super(message);
  }

}

