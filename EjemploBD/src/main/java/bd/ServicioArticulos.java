package bd;

import java.sql.PreparedStatement;
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
	public void actualizar(Articulo articulo) {
		actualizar(articulo.getIdArticulo(), new InsertarArticuloConsumer(articulo));
	}
	
	@Override
	public int insertar(Articulo articulo) {
		// A pesar de que se podría implementar fácilmente mediante InsertarArticuloConsumer,
		// decidimos no hacerlo a propósito, puesto que es una funcionalidad que no tiene sentido en la aplicación
		// (tal y como está concebida)
		throw new UnsupportedOperationException();
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
	
	protected class InsertarArticuloConsumer extends InsertarConsumer {
		private Articulo articulo;

		public InsertarArticuloConsumer(Articulo articulo) {
			super();
			this.articulo = articulo;
		}

		@Override
		public void insertar(PreparedStatement statement) throws SQLException {
			statement.setString(1, articulo.getCodigoArticulo());
			statement.setString(2, articulo.getDescripcion());

			statement.setInt(3, articulo.getStock());
			statement.setInt(4, articulo.getReservado());
			
			statement.setDouble(5, articulo.getPrecioUnitario());
		}
	}
}
