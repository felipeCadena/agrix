package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.controller.dto.PersonCreationDto;
import com.betrybe.agrix.controller.dto.PersonDto;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Person.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  /**
   * Metodos e Atributos.
   */

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Rota Post para criar Person.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createFarm(@RequestBody PersonCreationDto personCreationDto) {
    return PersonDto.fromEntity(
        personService.create(personCreationDto.toEntity())
    );
  }
}
