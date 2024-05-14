package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Service Crop.
 */
@Service
public class CropService {

  /**
   * Atributos e Metodos.
   */
  private final CropRepository cropRepository;
  private final FarmService farmService;
  private final FertilizerService fertilizerService;

  /**
   * Constructor Crop.
   */
  @Autowired
  public CropService(CropRepository cropRepository,
      FarmService farmService, FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Criar Crop.
   */
  public Crop create(Long farmId, Crop crop) throws FarmNotFoundException {
    Farm farm = farmService.findById(farmId);

    crop.setFarm(farm);

    return cropRepository.save(crop);
  }

  public List<Crop> findAll() {
    return cropRepository.findAll();
  }

  public Crop findById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id).orElseThrow(CropNotFoundException::new);
  }

  /**
   * Buscar Crop em intervalos de data de colheita.
   */
  public List<Crop> search(LocalDate start, LocalDate end) {
    List<Crop> allCrops = findAll();

    return allCrops.stream().filter((date) -> date.getHarvestDate().isAfter(start)
        && date.getHarvestDate().isBefore(end)).toList();
  }

  /**
   * Associar Fertilizer a Crop.
   */
  public void addFertilizerCrop(@PathVariable Long cropId, @PathVariable Long fertilizerId) throws
      CropNotFoundException, FertilizerNotFoundException {
    Crop crop = findById(cropId);
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);

    fertilizer.getCrops().add(crop);

    cropRepository.save(crop);
  }
}
