package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Farm;


/**
 * Farm Dto Creation.
 */
public record FarmCreationDto(
    String name,
    Double size
) {

  /**
   * Metodos.
   */
  public Farm toEntity() {
    return new Farm(name, size);
  }
}
