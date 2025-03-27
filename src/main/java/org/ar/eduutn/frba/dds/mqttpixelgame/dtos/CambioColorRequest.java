package org.ar.eduutn.frba.dds.mqttpixelgame.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.ar.eduutn.frba.dds.mqttpixelgame.validations.ColorExistente;

public record CambioColorRequest(

    @PositiveOrZero
    Integer x,

    @PositiveOrZero
    Integer y,

    @ColorExistente
    String color
) {
}
