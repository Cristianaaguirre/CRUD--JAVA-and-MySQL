package tienda.persistencia;

import java.sql.SQLException;
import tienda.servicio.MiException;
import tiendal.entidades.Fabricante;

public class FabricanteDAO extends DAO {

  public void ingresarFabricante(Fabricante aux) throws MiException, ClassNotFoundException, SQLException {
    try {
      if (aux == null) {
        throw new MiException("Objeto Fabricante nulo");
      }
      String query = "INSERT INTO fabricante (nombre) value('" + aux.getNombre() + "');";
      modificarDB(query);
    } catch (MiException e) {
      System.out.println(e.getMessage());
      throw new MiException("Error al guardar");
    }
  }

  public void modificarFabricante(Fabricante aux) throws Exception {
    try {
      if (aux == null) {
        throw new MiException("Objeto Fabricante nulo");
      }
      String query = "UPDATE fabricante SET nombre ='" + aux.getNombre() + "' WHERE codigo =" + aux.getCodigo();
      modificarDB(query);
    } catch (MiException e) {
      System.out.println(e.getMessage());
      throw new Exception("ERROR AL MODIFICAR");
    }
  }
  
    public void eliminarUsuario(String nombre) throws MiException {
    try {
      if(nombre == null) throw new MiException("NOMBRE NO ESPECIFICADO");
      String query = "DELETE FROM fabricante WHERE nombre ='" + nombre + "';";
      modificarDB(query);
    } catch (ClassNotFoundException | SQLException | MiException e) {
      System.out.println(e.getMessage());
      throw new MiException("ERROR AL ELIMINAR");
    }
  }

  public Fabricante buscarFabricantePorCodigo(Integer aux) throws MiException, SQLException {
    try {
      String query = "SELECT * FROM fabricante WHERE codigo = " + aux + ";";
      consultarDB(query);
      Fabricante fabricante = null;
      while (resultado.next()) {
        fabricante = new Fabricante();
        fabricante.setCodigo(resultado.getInt(1));
        fabricante.setNombre(resultado.getString(2));
      }
      return fabricante;
    } catch (ClassNotFoundException | SQLException e) {
      throw new MiException("ERROR AL BUSCAR");
    } finally {
      desconectarDB();
    }
  }
}
