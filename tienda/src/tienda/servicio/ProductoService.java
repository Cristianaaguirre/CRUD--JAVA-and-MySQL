package tienda.servicio;

import java.sql.SQLException;
import java.util.ArrayList;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;

public class ProductoService {
  
  private final ProductoDAO pDAO;
  
  public ProductoService() {
    this.pDAO = new ProductoDAO();
  }
  
  public void crearProducto(String nombre, Double precio, Fabricante f) throws Exception {
    try {
      if(nombre == null || nombre.trim().isEmpty()) throw new MiException("ERROR EN EL NOMBRE");
      if(precio == null || precio < 0) throw new MiException("ERROR EN EL NUMERO");
      if(f== null || f.getCodigo() < 0) throw new MiException("ERROR EN EL FABRICANTE");
      
      Producto aux = new Producto();
      aux.setNombre(nombre);
      aux.setPrecio(precio);
      aux.setFabricante(f);
      
      pDAO.guardarProducto(aux);
    } catch (MiException e) {
      System.out.println(e.getMessage());
      throw e;
    }
  }
  
  public ArrayList listar() throws ClassNotFoundException, SQLException, MiException {
    try {
      ArrayList lista = pDAO.listarProductos();
      if(lista == null) throw new MiException("ERROR EN LA LISTA");
      if(lista.isEmpty()) throw new MiException("LA LISTA ESTA VACIA");
      return lista;
    } catch (ClassNotFoundException | SQLException | MiException e) {
      throw e;
    }
  }
  
}
