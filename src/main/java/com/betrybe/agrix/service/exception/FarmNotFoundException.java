package com.betrybe.agrix.service.exception;

/**
 * Exception Farm.
 */
public class FarmNotFoundException extends NotFoundException {
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }

}
