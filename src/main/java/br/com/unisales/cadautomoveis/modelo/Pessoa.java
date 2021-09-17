package br.com.unisales.cadautomoveis.modelo;

import java.util.Objects;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

@Indices(
        @Index(value = "cpf", type = IndexType.Unique)
)
public class Pessoa {

 @Id
 public NitriteId id;
 public String nome;
 public String cpf;
 
 public String codigo;

 @Override
 public String toString() {
  return nome + " (" + cpf + ")";
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj) {
   return true;
  }
  if (obj == null) {
   return false;
  }
  if (getClass() != obj.getClass()) {
   return false;
  }
  final Pessoa other = (Pessoa) obj;
  if (!Objects.equals(this.id, other.id)) {
   return false;
  }
  return true;
 }


 
 
}
