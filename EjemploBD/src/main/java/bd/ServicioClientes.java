package bd;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class ServicioClientes extends ServicioBD {
	
	private static final String tableName = "Cliente", primaryKeyName = "idCliente";
	private static final String[] fields = new String[] {"Nombre", "Direccion", "FechaAlta", "NumTarjeta", "Emisor", "Correoelectronico"};

	@Override
	public String[] getFields() {
		return fields;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public String getPrimaryKeyName() {
		return primaryKeyName;
	}
	
	/**
	 * Inserta un cliente en la base de datos
	 * 
	 * @param nombre
	 * @param direccion
	 * @param fechaAlta
	 * @param numTarjeta
	 * @param emisor
	 * @param correoElectronico
	 * @return La clave con la que se insertó
	 */
	public int insertar(String nombre, String direccion, java.util.Date fechaAlta, String numTarjeta, String emisor,
			String correoElectronico) {
		return insertar(
				new InsertarClienteConsumer(nombre, direccion, fechaAlta, numTarjeta, emisor, correoElectronico));
	}

	protected class InsertarClienteConsumer extends InsertarConsumer {
		private String nombre, direccion;
		private java.util.Date fechaAlta;
		private String numTarjeta, emisor, correoElectronico;

		public InsertarClienteConsumer(String nombre, String direccion, Date fechaAlta, String numTarjeta,
				String emisor, String correoElectronico) {
			super();
			this.nombre = nombre;
			this.direccion = direccion;
			this.fechaAlta = fechaAlta;
			this.numTarjeta = numTarjeta;
			this.emisor = emisor;
			this.correoElectronico = correoElectronico;
		}

		@Override
		public void insertar(PreparedStatement statement) throws SQLException {
			statement.setString(1, nombre);
			statement.setString(2, direccion);

			statement.setDate(3, toSqlDate(fechaAlta));

			statement.setString(4, numTarjeta);
			statement.setString(5, emisor);
			statement.setString(6, correoElectronico);
		}
	}
}
