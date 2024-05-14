package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * Person dto.
 */
public record PersonDto(
    Long id,
    String username,
    Role role
) {

  /**
   * Metodo que converte entidade em dto.
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }
}
