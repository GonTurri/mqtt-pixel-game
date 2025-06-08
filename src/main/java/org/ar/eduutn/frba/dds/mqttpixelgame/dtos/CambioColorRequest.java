package org.ar.eduutn.frba.dds.mqttpixelgame.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.ar.eduutn.frba.dds.mqttpixelgame.validations.ColorExistente;

public record CambioColorRequest(

    @NotBlank @Size(min=36, max=36)
    String id,

    @PositiveOrZero
    Integer x,

    @PositiveOrZero
    Integer y,

    @ColorExistente
    String color
) {
}
