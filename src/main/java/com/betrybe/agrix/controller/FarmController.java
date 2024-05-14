package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropCreationDto;
import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller Farm.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  /**
   * Atributos e Metodos Crop.
   */
  private final FarmService farmService;
  private final CropService cropService;

  /**
   * Contructor.
   */
  @Autowired
  public FarmController(FarmService farmService,
      CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Rota post para criar farms.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.fromEntity(
        farmService.create(farmCreationDto.toEntity())
    );
  }

  /**
   * Rota que recupera todas as farms.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarms = farmService.findAll();
    return allFarms.stream()
        .map(FarmDto::fromEntity)
        .toList();
  }

  /**
   * Rota que busca uma farm por id.
   */
  @GetMapping("/{id}")
  public FarmDto getFarmById(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.fromEntity(farmService.findById(id));
  }

  /**
   * Rota Post para criar Crop.
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto createCrop(
      @PathVariable Long farmId,
      @RequestBody CropCreationDto cropCreationDto
  ) throws FarmNotFoundException {
    return CropDto.fromEntity(
        cropService.create(farmId, cropCreationDto.toEntity())
    );
  }

  /**
   * Rota Get para encontrar todas as Crops.
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> getAllCropsByFarmId(@PathVariable Long farmId) throws FarmNotFoundException {
    Farm farm = farmService.findById(farmId);

    return farm.getCrops().stream()
        .map(CropDto::fromEntity)
        .toList();
  }
}
