package com.betrybe.agrix.service.exception;

/**
 * Exception Fertilizer.
 */
public class FertilizerNotFoundException extends NotFoundException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
