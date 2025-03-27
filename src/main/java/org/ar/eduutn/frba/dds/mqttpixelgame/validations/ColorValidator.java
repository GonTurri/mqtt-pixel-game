package org.ar.eduutn.frba.dds.mqttpixelgame.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.Color;
import java.util.Arrays;

public class ColorValidator implements ConstraintValidator<ColorExistente,String> {

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return Arrays.stream(Color.values()).
        anyMatch(color ->  s.toUpperCase().equals(color.name()));
  }
}