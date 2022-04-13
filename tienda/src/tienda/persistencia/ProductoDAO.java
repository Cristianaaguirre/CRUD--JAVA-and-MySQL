package tienda.persistencia;

import java.sql.SQLException;
import tienda.servicio.MiException;
import tiendal.entidades.Producto;

public class ProductoDAO extends DAO{
  
  public void guardarProducto(Producto aux) throws MiException, ClassNotFoundException, SQLException {
    try {
     if(aux == null) throw new MiException("No ha ingresado un usuario");
     
     String query = "INSERT INTO tienda (nombre, precio) VALUES('" + aux.getNombre()+ "','" + aux.getPrecio() + "','" + ");";
      modificarDB(query);
    } catch (MiException e) {
      throw e;
    }
  }
}
