package org.example.quanlisukien.exception;

public class NotFoundException extends RuntimeException{

  public NotFoundException(String messenger) {
    super(messenger);
  }

}
