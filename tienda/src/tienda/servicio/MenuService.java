package tienda.servicio;

import java.util.Scanner;

public class MenuService {

  private static void Menu() {
    Scanner read = new Scanner(System.in);
    try {
      System.out.println("Menu:"
              + "\n 1-Lista de productos"
              + "\n 2-Lista de precios y nombres de los productos"
              + "\n 3-Productos entre 120 y 202"
              + "\n 4-Listar portatitiles"
              + "\n 5-Producto mas barato"
              + "\n 6-Ingresar un producto"
              + "\n 7-Ingresar un fabricante"
              + "\n 8-Editar un producto"
      );
      System.out.print("Elija una opcion: ");
      Integer option = read.nextInt();

    } catch (Exception e) {
      throw  e;
    }
  }

  public static void mostrarMenu() throws Exception {
    // Con esta funcion vamos a abrir constantemente el menu sin necesidad de aplicar un bucle en la funcion Menu()
    Scanner read = new Scanner(System.in);
    System.out.println("Bienvenido");
    Integer option = 0;
    try {
      while (option != 2) {
        System.out.print("Desea abrir el menu? '1' si desea abrir o '2' si desea cerrar ");
        option = read.nextInt();
        if (option < 1 || option > 2) throw new Exception("Opcion incorrecta, vuleva a intentar");
        if (!"Integer".equals(option.getClass().getSimpleName())) throw new Exception("No es un numero, vuelva a intentar");
        else if (option != 2) Menu();
      }
    } catch (Exception e) {
      throw e;
    }
    System.out.println("Adios");
  }
}
