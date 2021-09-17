package br.com.unisales.cadautomoveis.modelo;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

/**
 * classe auxiliar para geração de códigos para outras tabelas
 *
 * @author romulo
 */
public class Codigo {

 @Id
 public String nomeTabela;
 public int valorSequencia;
}
