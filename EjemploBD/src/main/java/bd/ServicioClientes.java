package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Cliente;

public class ServicioClientes extends ServicioBD<Cliente> {

	private static final String tableName = "clientes", primaryKeyName = "idClientes";
	private static final String[] fields = new String[] { "Nombre", "Direccion", "FechaAlta", "NumTarjeta", "Emisor",
			"Correoelectronico" };

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
	
	@Override
	public Cliente obtener(int id) {
		return obtener(id, new ObtenerClienteFunction());
	}
	
	protected class ObtenerClienteFunction extends ObtenerFunction {
		@Override
		public Cliente obtener(int id, ResultSet result) throws SQLException {
			result.first();
			
			String nombre = result.getString(fields[0]);
			String direccion = result.getString(fields[1]);
			java.util.Date fechaAlta = result.getDate(fields[2]);
			String numTarjeta = result.getString(fields[3]);
			String emisor = result.getString(fields[4]);
			String correoElectronico = result.getString(fields[5]);
			
			Cliente cliente = new Cliente(nombre, direccion, fechaAlta, numTarjeta, emisor, correoElectronico);
			return cliente;
		}
	}

	@Override
	public void actualizar(Cliente cliente) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int insertar(Cliente cliente) {
		return insertar(
				new InsertarClienteConsumer(cliente));
	}

	protected class InsertarClienteConsumer extends InsertarConsumer {
		private Cliente cliente;

		public InsertarClienteConsumer(Cliente cliente) {
			super();
			this.cliente = cliente;
		}

		@Override
		public void insertar(PreparedStatement statement) throws SQLException {
			statement.setString(1, cliente.getNombre());
			statement.setString(2, cliente.getDireccion());

			statement.setDate(3, toSqlDate(cliente.getFechaAlta()));

			statement.setString(4, cliente.getNumTarjeta());
			statement.setString(5, cliente.getEmisor());
			statement.setString(6, cliente.getCorreoElectronico());
		}
	}
}
