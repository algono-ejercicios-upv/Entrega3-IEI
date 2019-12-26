package bd;

import java.sql.ResultSet;
import java.sql.SQLException;

import logica.Articulo;

public class ServicioArticulos extends ServicioBD<Articulo> {

	private static final String tableName = "articulos", primaryKeyName = "idArticulos";
	private static final String[] fields = new String[] { "CodigoArticulo", "Descripcion", "Stock", "Reservado",
			"PrecioUnitario" };

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
	public Articulo obtener(int id) {
		return obtener(id, new ObtenerArticuloFunction());
	}
	
	protected class ObtenerArticuloFunction extends ObtenerFunction {
		@Override
		public Articulo obtener(int id, ResultSet result) throws SQLException {
			result.first();
			
			String codigo = result.getString(fields[0]);
			String descripcion = result.getString(fields[1]);
			int stock = result.getInt(fields[2]);
			int reservado = result.getInt(fields[3]);
			double precioUnitario = result.getDouble(fields[4]);
			
			Articulo articulo = new Articulo(id, codigo, descripcion, stock, reservado, precioUnitario);
			return articulo;
		}
	}
}
