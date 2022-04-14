package tienda.servicio;

import java.sql.SQLException;
import java.util.*;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;

public class MenuService {

  private final ProductoService pServ;
  private final FabricanteService fServ;
  private final Scanner sc;

  public MenuService() {
    pServ = new ProductoService();
    fServ = new FabricanteService();
    sc = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
  }

  public void mostrarMenu() throws Exception {
    // Con esta funcion vamos a abrir constantemente el menu sin necesidad de aplicar un bucle en la funcion Menu()
    Scanner read = new Scanner(System.in);
    System.out.println("Bienvenido");
    Boolean close = false;
    do {
      try {
        System.out.println("============================"
                + "\nMenu:"
                + "\n 1-Lista de productos"
                + "\n 2-Lista de precios y nombres de los productos"
                + "\n 3-Productos entre 120 y 202"
                + "\n 4-Listar portatitiles"
                + "\n 5-Producto mas barato"
                + "\n 6-Ingresar un producto"
                + "\n 7-Ingresar un fabricante"
                + "\n 8-Editar un producto"
                + "\n 9-Cerrar"
        );
        System.out.print("Elija una opcion: ");
        Integer option = read.nextInt();

        switch (option) {
          case 1:
            listaDeNombres();
            break;
          case 2:
            listarNombrePrecio();
            break;
          case 3:

            break;
          case 4:
            listarPortatil();
            break;
          case 5:
            productoBarato();
            break;
          case 6:
            ingresarProducto();
            break;
          case 7:
            nuevoFabricante();
            break;
          case 8:
            break;
          case 9:
            close = true;
            System.out.println("Adios");
            break;
          default:
            System.out.println("Opcion incorrecta, vuelva a intentar");
            break;
        }

      } catch (MiException e) {
        System.out.println(e.getMessage());
        throw new MiException("ERROR AL ABRIR EL MENU");
      }
    } while (close != true);
  }

  //Listar el nombre de todos los productos
  public void listaDeNombres() throws ClassNotFoundException, SQLException, MiException {
    try {
      List<Producto> lista = pServ.listar();
      lista.forEach((p) -> {
        System.out.println("Nombre: " + p.getNombre());
      });
    } catch (ClassNotFoundException | SQLException | MiException e) {
      throw e;
    }
  }

  //Listar el nombre y el precio
  public void listarNombrePrecio() throws ClassNotFoundException, SQLException, MiException {
    try {
      List<Producto> lista = pServ.listar();
      if (lista.isEmpty()) {
        throw new MiException("LISTA VACIA");
      }
      lista.forEach((p) -> {
        System.out.println("Nombre: " + p.getNombre() + " , " + "Precio: " + p.getPrecio());
      });
    } catch (ClassNotFoundException | SQLException | MiException e) {
      throw e;
    }
  }

  //Listar los elementos que sean portatiles
  public void listarPortatil() throws Exception {
    try {
      ArrayList<Producto> producto = pServ.listar();
      for (Producto p : producto) {
        if (p.getNombre().startsWith("Port")) {
          System.out.println(p);
        }
      }
    } catch (ClassNotFoundException | SQLException | MiException e) {
      throw e;
    }
  }

  public void productoBarato() throws ClassNotFoundException, SQLException, MiException {
    try {
      List list = pServ.listar();
      if (list.isEmpty()) {
        throw new MiException("LISTA VACIA");
      }
      list.sort(compararPrecio);
      Producto barato = (Producto) list.get(0);
      System.out.println(barato.getNombre() + " , " + barato.getPrecio());
    } catch (ClassNotFoundException | SQLException | MiException e) {
      throw e;
    }
  }
  
    //Ingresar un Producto a la DB
  private void ingresarProducto() throws SQLException, MiException, Exception {
    System.out.println("Ingrese un nuevo Producto");
    try {
      //Solicitud de Datos
      System.out.print("Ingrese un nombre: ");
      String nombre = sc.next();
      System.out.print("Ingrese un precio: ");
      Double precio = sc.nextDouble();
      System.out.print("Ingrese un fabricante: ");
      Integer fab = sc.nextInt();
      //Validaciones
      if (nombre.trim().isEmpty()) {
        throw new MiException("ERROR EN EL NOMBRE");
      }
      if (precio < 0) {
        throw new MiException("ERROR EN EL PRECIO");
      }
      if (fab < 0) {
        throw new MiException("ERROR EN EL FABRICANTE");
      }
      //Seteo e ingreso a la DB del producto
      Fabricante f = fServ.buscarFabricante(fab);
      pServ.crearProducto(nombre, precio, f);
    } catch (SQLException | MiException e) {
      throw e;
    }
  }

  //Ingresar un Fabricante a la DB
  private void nuevoFabricante() throws MiException {
    System.out.print("Ingrese un nuevo fabricante");
    try {
      String palabra = sc.next().trim();
      fServ.crearFabricante(palabra);
    } catch (MiException e) {
      throw e;
    }
  }

  //Comparator
  public static Comparator<Producto> compararPrecio = (Producto p1, Producto p2) -> p1.getPrecio().compareTo(p2.getPrecio());
}
