package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Crop.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  /**
   * Metodos e Atributos.
   */
  private final CropService cropService;
  private final FertilizerService fertilizerService;

  /**
   * Constructor Crop.
   */
  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {

    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Rota Get para Crops.
   */
  @GetMapping
  @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
  @ResponseStatus(HttpStatus.OK)
  public List<CropDto> findAll() {
    return cropService.findAll().stream()
        .map(CropDto::fromEntity)
        .toList();
  }

  @GetMapping("/{id}")
  public CropDto getCropById(@PathVariable Long id) throws CropNotFoundException {
    return CropDto.fromEntity(cropService.findById(id));
  }

  /**
   * Buscar Crop em intervalos de data de colheita.
   */
  @GetMapping("/search")
  public List<CropDto> getCropBySearch(
      @RequestParam("start") String start,
      @RequestParam("end") String end
  ) {

    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);

    List<Crop> crops = cropService.search(startDate, endDate);

    return crops.stream().map(CropDto::fromEntity).toList();
  }

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String addFertilizerCrop(@PathVariable Long cropId, @PathVariable Long fertilizerId) throws
      CropNotFoundException, FertilizerNotFoundException {
    cropService.addFertilizerCrop(cropId, fertilizerId);
    return "Fertilizante e plantação associados com sucesso!";
  }

  @GetMapping("/{cropId}/fertilizers")
  @ResponseStatus(HttpStatus.OK)
  public List<FertilizerDto> getAllFertilizerByCrop(@PathVariable Long cropId) throws
      CropNotFoundException {
    List<Fertilizer> fertilizer = cropService.findById(cropId).getFertilizers();
    return fertilizer.stream().map(FertilizerDto::fromEntity).toList();
  }
}
