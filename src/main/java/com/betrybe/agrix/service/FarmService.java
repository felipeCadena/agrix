package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service Farm.
 */
@Service
public class FarmService {

  /**
   * Atributos e Metodos Crop.
   */
  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {

    this.farmRepository = farmRepository;
  }

  public Farm create(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  public Farm findById(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
  }
}
