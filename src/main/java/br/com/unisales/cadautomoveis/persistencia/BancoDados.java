package br.com.unisales.cadautomoveis.persistencia;

import br.com.unisales.cadautomoveis.modelo.Automovel;
import br.com.unisales.cadautomoveis.modelo.Codigo;
import br.com.unisales.cadautomoveis.modelo.Pessoa;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;

public class BancoDados {
 
 public static ObjectRepository<Pessoa> tabelaPessoas;
 public static ObjectRepository<Automovel> tabelaAutomoveis;
 //tabela auxiliar para geração de códigos
 public static ObjectRepository<Codigo> tabelaCodigos;

 /**
  * código executado no momento da execução
  */
 static {
  Nitrite bancoDados = Nitrite.builder()
          .compressed()
          .filePath("baseDados.db")
          .openOrCreate("root", "salesiano");
  
  tabelaPessoas = bancoDados.getRepository(Pessoa.class);
  tabelaAutomoveis = bancoDados.getRepository(Automovel.class);
  tabelaCodigos = bancoDados.getRepository(Codigo.class);
  
 }
 /**
  * retorna um objeto do tipo codigo com o 
  * próximo valor de sequencia para uma determinada tabela
  * cujo nome é passado como parâmetro
  * 
  * @param nomeTabela
  * @return 
  */
 public static Codigo getNextCode(String nomeTabela){
  Codigo codigo = BancoDados.tabelaCodigos.find(
           ObjectFilters.eq("nomeTabela", nomeTabela)
   ).firstOrDefault();
   if (codigo == null) {
    codigo = new Codigo();
    codigo.nomeTabela = nomeTabela;
    codigo.valorSequencia = 1;
    BancoDados.tabelaCodigos.insert(codigo);
   } else {
    codigo.valorSequencia++;
    BancoDados.tabelaCodigos.update(codigo);
    System.out.println(codigo.valorSequencia);
   }
   return codigo;
 }
}
