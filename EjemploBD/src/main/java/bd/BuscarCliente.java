package bd;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BuscarCliente implements JavaDelegate {
	

	@Override
	public void execute(DelegateExecution ejecucion) throws Exception {
		// TODO Auto-generated method stub
		int codCliente = Integer.parseInt(((String) ejecucion.getVariable("IDCliente")));
		System.out.println("Buscando cliente " + codCliente);
		ServicioClientes servicio = new ServicioClientes();
		boolean encontrado = servicio.buscarCliente(codCliente);
		System.out.println("Encontrado " + encontrado); // eco en consola de ejecución
		// añadir el resultado al motor.
		ejecucion.setVariable("IDEncontrado", encontrado);
	}
}
