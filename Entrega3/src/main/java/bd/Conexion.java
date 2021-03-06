package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Connection conexion = null;

	public static Connection abrirConexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// nombre de la base de datos Entrega2
			// password (root, root) en el laboratorio.
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/entrega3procesos", "root", "root"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Crear la conexi�n a la BD de personas
		return conexion;
	}

	public static void cerrarConexion() {
		if (conexion != null) {
			try {
				// cerrar la BD
				conexion.close();
				conexion = null;
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexi�n con la BD");
			}
		}
	}

}
