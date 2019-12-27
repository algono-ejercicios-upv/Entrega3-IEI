package bd;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import logica.Cliente;

public class BuscarCliente implements JavaDelegate {

	@Override
	public void execute(DelegateExecution ejecucion) throws Exception {
		int idCliente = Integer.parseInt(((String) ejecucion.getVariable("IDCliente")));
		System.out.println("Buscando cliente " + idCliente);

		ServicioClientes servicioClientes = new ServicioClientes();
		Cliente cliente = servicioClientes.obtener(idCliente);
		boolean encontrado = cliente != null;

		System.out.println("Encontrado " + encontrado);

		// Añadir el resultado al motor
		ejecucion.setVariable("IDEncontrado", encontrado);
		
		// Insertar su email, para cuando se le informe
		ejecucion.setVariable("IDEmail", cliente.getCorreoElectronico());
	}
}
