package tienda.servicio;

import java.sql.SQLException;
import tienda.persistencia.FabricanteDAO;
import tienda.entidades.Fabricante;

public class FabricanteService {
  
  private FabricanteDAO f;
  
  public FabricanteService() {
    this.f = new FabricanteDAO();
  }
  
 public void crearFabricante(String nombre) throws MiException {
   try {
     if(nombre == null || nombre.trim().isEmpty()) throw new MiException("NOMBRE INVALIDO");
     Fabricante fabricante = new Fabricante();
     fabricante.setNombre(nombre);
     f.ingresarFabricante(fabricante);
   } catch (ClassNotFoundException | SQLException | MiException e) {
     System.out.println(e.getMessage());
     throw new MiException("ERROR AL CREAR");
   }
 }
 
 public Fabricante buscarFabricante(Integer aux) throws MiException, SQLException {
   try {
     if(aux == null || aux < 0) throw new MiException("ERROR EN EL NUMERO");
     Fabricante fab = f.buscarFabricantePorCodigo(aux);
     return fab;
   } catch (MiException e) {
     throw e;
   }
 }
}
