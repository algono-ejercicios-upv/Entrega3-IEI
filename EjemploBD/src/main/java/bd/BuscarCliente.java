package bd;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BuscarCliente implements JavaDelegate {
	

	@Override
	public void execute(DelegateExecution ejecucion) throws Exception {
		int codCliente = Integer.parseInt(((String) ejecucion.getVariable("IDCliente")));
		System.out.println("Buscando cliente " + codCliente);
		
		ServicioClientes servicioClientes = new ServicioClientes();
		boolean encontrado = servicioClientes.buscar(codCliente);
		
		System.out.println("Encontrado " + encontrado);
		
		// Añadir el resultado al motor
		ejecucion.setVariable("IDEncontrado", encontrado);
	}
}
