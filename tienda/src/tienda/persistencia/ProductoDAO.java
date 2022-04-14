package tienda.persistencia;

import java.sql.SQLException;
import java.util.*;
import tienda.servicio.MiException;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;

public class ProductoDAO extends DAO {

  public void guardarProducto(Producto aux) throws MiException, ClassNotFoundException, SQLException {
    try {
      if (aux == null) {
        throw new MiException("ERROR OBJETO NULO");
      }

      String query = "INSERT INTO producto (nombre, precio,codigo_fabricante) "
              + "VALUES('" + aux.getNombre() + "'," + aux.getPrecio() + "," + aux.getFabricante().getCodigo() + ");";

      modificarDB(query);
    } catch (MiException e) {
      throw e;
    }
  }

  public void modificarProducto(Producto aux) throws MiException {
    try {
      if (aux == null) {
        throw new MiException("No ha ingresado un usuario");
      }

      String query = "UPDATE ";
    } catch (MiException e) {
      System.out.println(e.getMessage());
      throw new MiException("ERROR AL MODIFICAR");
    }
  }

  public Producto buscarPorCodigo(Integer aux) throws MiException, ClassNotFoundException, SQLException {
    try {
      if (aux == null) {
        throw new MiException("No ha ingresado un usuario");
      }
      String query = "SELECT * FROM producto p "
              + "INNER JOIN fabricante f "
              + "ON f.codigo = p.codigo_fabricante "
              + "WHERE p.codigo = " + aux + ";";
      consultarDB(query);
      Producto p = null;
      Fabricante f = null;

      while (resultado.next()) {
        p = new Producto();
        f = new Fabricante();

        p.setCodigo(resultado.getInt(1));
        p.setNombre(resultado.getString(2));
        p.setPrecio(resultado.getDouble(3));
        //Primero debo llenar el codigo del fabricante para poder setear el codigo_fabricante del producto
        f.setCodigo(resultado.getInt(5));
        f.setNombre(resultado.getString(6));
        p.setFabricante(f);
      }
      return p;
    } catch (MiException e) {
      System.out.println(e.getMessage());
      throw new MiException("ERROR EN LA BUSQUEDA");
    } finally {
      desconectarDB();
    }
  }

  public void eliminarPorCodigo(Integer aux) throws MiException {
    try {
      if (aux == null) {
        throw new MiException("NO SE ENCONTRO EL CODIGO");
      }
      String query = "DELETE FROM producto WHERE codigo = " + aux + ";";
      modificarDB(query);
    } catch (ClassNotFoundException | SQLException | MiException e) {
      System.out.println(e.getMessage());
      throw new MiException("ERROR AL ELIMINAR");
    }
  }

  public ArrayList<Producto> listarProductos() throws ClassNotFoundException, SQLException, MiException {
    try {

      String query = "SELECT * FROM producto p "
              + "INNER JOIN fabricante f "
              + "ON f.codigo = p.codigo_fabricante ";
      consultarDB(query);
      Producto p = null;
      Fabricante f = null;
      ArrayList<Producto> lista = new ArrayList();
      while (resultado.next()) {
        p = new Producto();
        f = new Fabricante();
        p.setCodigo(resultado.getInt(1));
        p.setNombre(resultado.getString(2));
        p.setPrecio(resultado.getDouble(3));
        //Primero debo llenar el codigo del fabricante para poder setear el codigo_fabricante del producto
        f.setCodigo(resultado.getInt(5));
        f.setNombre(resultado.getString(6));
        p.setFabricante(f);
        lista.add(p);
      }
      return lista;
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println(e.getMessage());
      throw new MiException("ERROR EN LA BUSQUEDA");
    } finally {
      desconectarDB();
    }
  }
}
