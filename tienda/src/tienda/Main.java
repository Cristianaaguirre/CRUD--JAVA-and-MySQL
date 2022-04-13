package tienda;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import tienda.servicio.MenuService;


public class Main {

  public static void main(String[] args) throws Exception  {
    try (Scanner read = new Scanner(System.in)) {
      MenuService.mostrarMenu();
    } catch (Exception e) {
      Logger.getLogger(Main.class.getName()).log(Level.WARNING, null, e);
    }
  }
}
