package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;

/**
 * Crop Creation.
 */
public record CropCreationDto(
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate
) {

  public Crop toEntity() {
    return new Crop(name, plantedArea, plantedDate, harvestDate);
  }
}
