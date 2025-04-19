package org.ar.eduutn.frba.dds.mqttpixelgame.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Color {

  BLANCO(new Rgb(255,255,255)),
  NEGRO(new Rgb(0,0,0)),
  BEIGE(new Rgb(177,152,128)),
  ROSA(new Rgb(244,184,183)),
  ROJO(new Rgb(218,51,72)),
  AMARILLO(new Rgb(253,241,91)),
  MARRON(new Rgb(163,135,74));


  private final Rgb rgb;


}
