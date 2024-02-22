package org.example.quanlisukien.exception;

public class InternalServerException extends RuntimeException{

  public InternalServerException(String messenger) {
    super(messenger);
  }

}
