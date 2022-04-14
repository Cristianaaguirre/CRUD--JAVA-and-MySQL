package tienda;

import java.util.logging.Level;
import java.util.logging.Logger;
import tienda.servicio.MenuService;



public class Main {

  public static void main(String[] args) throws Exception  {
    try {
      MenuService menu = new MenuService();
      menu.mostrarMenu();
    } catch (Exception e) {
      Logger.getLogger(Main.class.getName()).log(Level.WARNING, null, e);
    }
  }
}
