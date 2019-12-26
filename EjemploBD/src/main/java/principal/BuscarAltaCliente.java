package principal;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateTask;

@ProcessApplication("Acceso a bases de datos")
public class BuscarAltaCliente extends ServletProcessApplication {
	// La clase se deja vacia a proposito
	// La necesita Camunda como "main"
	
	// Metodo de utilidad para obtener una variable a partir de la DelegateTask
	@SuppressWarnings("unchecked")
	public static final<T> T getVariable(DelegateTask delegateTask, String variableName) {
		return (T) delegateTask.getExecution().getVariable(variableName);
	}
}
