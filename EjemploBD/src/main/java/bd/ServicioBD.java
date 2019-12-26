package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;
import java.util.function.Function;

import org.camunda.bpm.engine.delegate.DelegateTask;

public abstract class ServicioBD<T> {
	public final String getSelectQuery() {
		String primaryKeyName = getPrimaryKeyName();
		String tableName = getTableName();
		return String.format("SELECT %s FROM %s where %s =?", primaryKeyName, tableName, primaryKeyName);
	}
	
	public final String getUpdateQuery() {
		String primaryKeyName = getPrimaryKeyName();
		String tableName = getTableName();
		
		String[] fields = getFields();
		int fieldCount = fields.length;

		String updateQueryValues = "";

		for (int i = 0; i < fieldCount; i++) {
			String field = fields[i];
			updateQueryValues += field + " =?";

			if (i < fieldCount - 1) {
				updateQueryValues += ",";
			}
		}
		
		return String.format("UPDATE %s SET %s WHERE %s =?", tableName, updateQueryValues, primaryKeyName);
	}

	public final String getInsertQuery() {
		String tableName = getTableName();
		String insertQuery = "INSERT INTO " + tableName;

		String[] fields = getFields();
		int fieldCount = fields.length;

		String insertQueryValues = "";

		for (int i = 0; i < fieldCount; i++) {
			if (i == 0) {
				insertQuery += " (";
				insertQueryValues += " VALUES (";
			}

			insertQuery += fields[i];
			insertQueryValues += "?";

			if (i < fieldCount - 1) {
				insertQuery += ",";
				insertQueryValues += ",";
			} else {
				insertQuery += ")";
				insertQueryValues += ")";
			}
		}

		insertQuery += insertQueryValues;

		return insertQuery;
	}

	public abstract String[] getFields();

	public abstract String getTableName();

	public abstract String getPrimaryKeyName();
	
	
	public boolean buscar(int id) {
		boolean encontrado = false;

		try {
			ResultSet result = obtenerSet(id);
			if (result != null) {
				encontrado = result.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return encontrado;
	}
	
	public abstract T obtener(int id);
	
	protected final T obtener(int id, ObtenerFunction obtenerCampos) {
		obtenerCampos.setId(id);
		try {
			return obtenerCampos.apply(obtenerSet(id));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return null;		
	}
	
	protected final ResultSet obtenerSet(int id) throws SQLException {
		ResultSet resultado = null;
		Connection conn = Conexion.abrirConexion();
		if (conn != null) {
			try {
				String SQL = getSelectQuery();
				PreparedStatement statement = conn.prepareStatement(SQL);
				statement.setInt(1, id);
			    resultado = statement.executeQuery();
			} finally {
				Conexion.cerrarConexion();
			}
		}
		
		return resultado;
	}
	
	public abstract int insertar(T item);

	protected final int insertar(InsertarConsumer insertarCampos) {
		int clave = 0;
		Connection conn = Conexion.abrirConexion();
		if (conn != null) {
			String SQL = getInsertQuery();
			try {
				PreparedStatement statement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				insertarCampos.accept(statement);

				statement.executeUpdate();
				clave = obtenerClave(statement);			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
		}

		return clave;
	}
	
	public abstract void actualizar(T item);
	
	protected final void actualizar(int id, InsertarConsumer insertarCampos) {
		Connection conn = Conexion.abrirConexion();
		if (conn != null) {
			String SQL = getUpdateQuery();
			try {
				PreparedStatement statement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(getFields().length + 1, id);
				insertarCampos.accept(statement);

				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
		}
	}
	
	protected static final int obtenerClave(PreparedStatement statement) throws SQLException {
		ResultSet claves = statement.getGeneratedKeys();
		claves.next();
		int clave = claves.getInt(1);
		return clave;
	}
	
	protected abstract class ObtenerFunction implements Function<ResultSet, T> {
		private int id;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		@Override
		public final T apply(ResultSet result) {
			try {
				return obtener(id, result);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
			return null;
		}

		public abstract T obtener(int id, ResultSet result) throws SQLException;
	}

	protected abstract class InsertarConsumer implements Consumer<PreparedStatement> {
		@Override
		public final void accept(PreparedStatement statement) {
			try {
				insertar(statement);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Conexion.cerrarConexion();
			}
		}

		public abstract void insertar(PreparedStatement statement) throws SQLException;
	}

	// Metodo de utilidad para obtener una variable a partir de la DelegateTask
	@SuppressWarnings("unchecked")
	public static final <T> T getVariable(DelegateTask delegateTask, String variableName) {
		return (T) delegateTask.getExecution().getVariable(variableName);
	}

	// Convertir java.util.Date a java.sql.Date
	public static final java.sql.Date toSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
}
