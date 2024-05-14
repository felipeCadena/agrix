package com.betrybe.agrix.service.exception;

/**
 * Exception do Crop.
 */
public class CropNotFoundException extends NotFoundException {

  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
