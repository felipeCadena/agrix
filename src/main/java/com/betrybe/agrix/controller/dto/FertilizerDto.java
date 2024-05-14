package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Fertilizer;

/**
 * Fertilizer dto.
 */
public record FertilizerDto(
    Long id,
    String name,
    String brand,
    String composition
) {

  /**
   * Metodo que converte entidade em dto.
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }
}
