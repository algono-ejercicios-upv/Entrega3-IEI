package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logica.Pedido;

public class ServicioCabeceraPedidos extends ServicioBD<Pedido> {

	private static final String tableName = "cabecerapedidos", primaryKeyName = "idCabeceraPedidos";
	private static final String[] fields = new String[] { "FechaPedido", "Clientes_idClientes" };

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

	public int insertar(java.util.Date fechaPedido, int idCliente) {
		return insertar(new InsertarCabeceraPedidoConsumer(fechaPedido, idCliente));
	}

	protected class InsertarCabeceraPedidoConsumer extends InsertarConsumer {
		private java.util.Date fechaPedido;
		private int idCliente;

		public InsertarCabeceraPedidoConsumer(java.util.Date fechaPedido, int idCliente) {
			super();
			this.fechaPedido = fechaPedido;
			this.idCliente = idCliente;
		}

		@Override
		public void insertar(PreparedStatement statement) throws SQLException {
			statement.setDate(1, toSqlDate(fechaPedido));
			statement.setInt(2, idCliente);
		}
	}

	@Override
	public Pedido obtener(int id) {
		return obtener(id, new ObtenerArticuloFunction());
	}
	
	protected class ObtenerArticuloFunction extends ObtenerFunction {
		@Override
		public Pedido obtener(int id, ResultSet result) throws SQLException {
			result.first();
			
			java.util.Date fechaPedido = result.getDate(fields[0]);
			int idCliente = result.getInt(fields[1]);
			
			Pedido pedido = new Pedido(fechaPedido, idCliente);
			return pedido;
		}
	}
}
