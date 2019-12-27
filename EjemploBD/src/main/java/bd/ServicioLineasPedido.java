package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logica.LineaPedido;

public class ServicioLineasPedido extends ServicioBD<LineaPedido> {

	private static final String tableName = "lineapedidos", primaryKeyName = "idLineaPedidos";
	private static final String[] fields = new String[] { "Cantidad", "CabeceraPedidos_idCabeceraPedidos", "Articulos_idArticulos" };

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
	public void actualizar(LineaPedido lineaPedido) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int insertar(LineaPedido lineaPedido) {
		return insertar(new InsertarCabeceraPedidoConsumer(lineaPedido));
	}

	protected class InsertarCabeceraPedidoConsumer extends InsertarConsumer {
		private LineaPedido lineaPedido;

		public InsertarCabeceraPedidoConsumer(LineaPedido lineaPedido) {
			super();
			this.lineaPedido = lineaPedido;
		}

		@Override
		public void insertar(PreparedStatement statement) throws SQLException {
			statement.setInt(1, lineaPedido.getCantidad());
			statement.setInt(2, lineaPedido.getCodigoPedido());
			statement.setInt(3, lineaPedido.getIdArticulo());
		}
	}

	@Override
	public LineaPedido obtener(int id) {
		return obtener(id, new ObtenerArticuloFunction());
	}
	
	protected class ObtenerArticuloFunction extends ObtenerFunction {
		@Override
		public LineaPedido obtener(int id, ResultSet result) throws SQLException {
			if (result.first()) {
				int cantidad = result.getInt(fields[0]);
				int codigoPedido = result.getInt(fields[1]);
				int idArticulo = result.getInt(fields[2]);
				
				LineaPedido lineaPedido = new LineaPedido(idArticulo, cantidad, codigoPedido);
				return lineaPedido;	
			} else {
				return null;
			}
		}
	}
}
