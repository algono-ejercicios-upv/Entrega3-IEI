package bd;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServicioCabeceraPedidos extends ServicioBD {

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
}
