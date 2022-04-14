package tienda.persistencia;

import java.sql.*;

public abstract class DAO {
  
  protected Connection conexion = null;
  protected ResultSet resultado = null;
  protected Statement sentencia = null;
  
 // De esta forma podrias tratar la clase conexion, de forma mas simple, lo pasas al Driver Manager
//  private final String USER = "root";
//  private final String PASSWORD = "as424543454";
//  private final String DRIVER = "com.mysql.jdbc.Driver";
//  private final String URL = "jdbc:mysql://localhost:3306/tienda?userSSL=false";
  
  protected void contectarDB() throws SQLException, ClassNotFoundException {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda?useSSL=false", "root", "as424543454");
    } catch (ClassNotFoundException | SQLException e) {
      throw e;
    }
  }
  
  protected void desconectarDB() throws SQLException {
    try {
      if (conexion != null) {
        conexion.close();
      }
      if (sentencia != null) {
        sentencia.close();
      }
      if (resultado != null) {
        resultado.close();
      }
    } catch (SQLException e) {
      throw e;
    }
  }
  
  protected void modificarDB(String sql) throws ClassNotFoundException, SQLException {
    try {
      contectarDB();
      sentencia = conexion.createStatement();
      sentencia.executeUpdate(sql);
    } catch (ClassNotFoundException | SQLException e) {
      throw e;
    } finally {
      desconectarDB();
    }
  }
  
  protected void consultarDB(String sql) throws ClassNotFoundException, SQLException {
    try {
      contectarDB();
      sentencia = conexion.createStatement();
      resultado = sentencia.executeQuery(sql);
    } catch (ClassNotFoundException | SQLException e) {
      throw e;
    }
  }
}
