package br.com.unisales.cadautomoveis.modelo;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

public class Automovel {

 @Id
 public NitriteId id;
 //vamos supor a placa única
 public String placa;
 public int ano;
 public String modelo;
 
 //campo do tipo Pessoa
 //que indica o dono do automóvel
 public Pessoa proprietario;

 public String toString() {
  return placa + " : " + modelo + " [" + ano + "]";
 }
}
