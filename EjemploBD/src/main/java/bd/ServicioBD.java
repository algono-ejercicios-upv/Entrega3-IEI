package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;
import org.camunda.bpm.engine.delegate.DelegateTask;

public abstract class ServicioBD {
	public boolean buscar(int id) {
		boolean encontrado = false;
        
    	Connection conn = Conexion.abrirConexion();
        if(conn !=null) {
            String SQL = getSelectQuery();
            try {
                PreparedStatement statement = conn.prepareStatement(SQL);
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();
                if (result.next()) encontrado= true;
                else encontrado = false;
                Conexion.cerrarConexion();
                return (encontrado);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        Conexion.cerrarConexion();
        return false;
	}
	
	public final String getSelectQuery() {
		String primaryKeyName = getPrimaryKeyName();
		String tableName = getTableName();
		return String.format("SELECT %s FROM %s where %s =?", primaryKeyName, tableName, primaryKeyName);
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
	
	protected final int insertar(InsertarConsumer insertarCampos) {
		int clave = 0;
    	Connection conn = Conexion.abrirConexion();
        if (conn !=null) {
            String SQL = getInsertQuery();
            try {
                PreparedStatement statement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                insertarCampos.accept(statement);
                
                statement.executeUpdate();
                ResultSet claves = statement.getGeneratedKeys();
                claves.next();
                clave = claves.getInt(1);
                return clave;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            	Conexion.cerrarConexion();
            }
        }
        
        return clave;
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
	public static final<T> T getVariable(DelegateTask delegateTask, String variableName) {
		return (T) delegateTask.getExecution().getVariable(variableName);
	}
	
	// Convertir java.util.Date a java.sql.Date
	public static final java.sql.Date toSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
}
