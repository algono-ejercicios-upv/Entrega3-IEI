package bd;

public class ServicioArticulos extends ServicioBD {
	
	private static final String tableName = "articulos", primaryKeyName = "idArticulos";
	private static final String[] fields = new String[] {"CodigoArticulo", "Descripcion", "Stock", "Reservado", "PrecioUnitario"};

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
}
