package org.ar.eduutn.frba.dds.mqttpixelgame.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class ResultadoCambio {
  private Boolean huboCambio;

  public Boolean huboCambio(){
    return this.huboCambio;
  }
}
