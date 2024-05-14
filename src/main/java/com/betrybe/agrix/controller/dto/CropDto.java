package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;

/**
 * Crop Dto.
 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId
) {

  /**
   * Entidade para CropDto.
   */
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlanted_area(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()
    );
  }

}
