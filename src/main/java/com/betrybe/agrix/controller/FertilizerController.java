package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
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
 * Controller Crop.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  /**
   * Atributos e Metodos.
   */
  private FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Cria Fertilizers.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createFertilizer(@RequestBody FertilizerCreationDto fertilizerCreationDto) {
    return FertilizerDto.fromEntity(fertilizerService.create(fertilizerCreationDto.toEntity()));
  }

  /**
   * Retorna todos os Fertilizers.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<FertilizerDto> findAll() {
    List<Fertilizer> fertilizers = fertilizerService.findAll();
    return fertilizers.stream()
        .map(FertilizerDto::fromEntity)
        .toList();
  }

  /**
   * Retorna um Fertilizer especifico.
   */
  @GetMapping("/{fertilizerId}")
  public FertilizerDto getFarmById(@PathVariable Long fertilizerId) throws
      FertilizerNotFoundException {
    return FertilizerDto.fromEntity(fertilizerService.findById(fertilizerId));
  }
}
