package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ServicioClientes {
	
	public boolean buscarCliente(int codCliente) {
		boolean encontrado = false;
        
    	Connection conn = Conexion.abrirConexion();
        if(conn !=null) {
            String SQL = "SELECT idCliente FROM Cliente where idCliente =?";
            try {
                PreparedStatement statement = conn.prepareStatement(SQL);
                statement.setInt(1, codCliente);
                ResultSet result = statement.executeQuery();
                if (result.next()) encontrado= true;
                else encontrado = false;
                Conexion.cerrarConexion();
                return (encontrado);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        Conexion.cerrarConexion();
        return false;
    }
	
	
	
	// devuelve la clave con la que se insertó el cliente.
    public int insertarCliente(String nombre, String Email, java.util.Date FechaAlta, 
    		                 String TarjetaCredito, String Emisor, String Caducidad) 
        {
        int clave = 0;
    	Connection conn = Conexion.abrirConexion();
        if(conn !=null) {
            String SQL = "INSERT INTO CLIENTE (Nombre, Email, FechaAlta, TarjetaCredito, Emisor, Caducidad, PendientedePago) VALUES (?,?,?,?,?,?,?)";
            try {
                PreparedStatement statement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1,nombre);
                statement.setString(2,Email);
                // convertir java.util.Date a java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(FechaAlta.getTime());
                statement.setDate(3,sqlDate);
                statement.setString(4,TarjetaCredito);
                statement.setString(5,Emisor);
                statement.setString(6,Caducidad);
                statement.setDouble(7, 0.0);
                statement.executeUpdate();
                ResultSet claves = statement.getGeneratedKeys();
                claves.next();
                clave = claves.getInt(1);
                return clave;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally { Conexion.cerrarConexion();}
        } return clave;
    }
    

}
