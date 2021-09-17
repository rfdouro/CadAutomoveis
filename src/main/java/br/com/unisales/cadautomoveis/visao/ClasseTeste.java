package br.com.unisales.cadautomoveis.visao;

import java.util.Scanner;

public class ClasseTeste {

 public static void main(String[] args) {
  int n;
  Scanner scn = new Scanner(System.in);
  System.out.println("Digite um número");
  try {
   n = scn.nextInt();
   System.out.println("o número digitado foi " + n);
  } catch (Exception ex) {
   System.out.println("Ocorreu um erro");
  }

  System.out.println("aqui acaba o programa");
 }
}
