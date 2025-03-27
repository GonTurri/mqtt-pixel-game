package org.ar.eduutn.frba.dds.mqttpixelgame.mappers;

import org.ar.eduutn.frba.dds.mqttpixelgame.entities.models.Color;

public class ColorMapper {
  private ColorMapper(){}

  public static Color colorFromString(String s){
    return Color.valueOf(s.toUpperCase());
  }

  public static String ColorToRgbString(Color c){
    return "rgb(" +
        c.getRgb().red() +
        "," +
        c.getRgb().green() +
        "," +
        c.getRgb().blue() +
        ")";
  }
}
