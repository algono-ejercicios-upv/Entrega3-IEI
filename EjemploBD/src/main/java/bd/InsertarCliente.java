package bd;

import java.util.Date;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

import logica.Cliente;

public class InsertarCliente implements TaskListener {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask tareaDelegada) {
		// Acceso a las variables introducidas en el formulario
		String nombre = (String) tareaDelegada.getExecution().getVariable("IDNombreCliente");
		String email = (String) tareaDelegada.getExecution().getVariable("IDEmail");
		Date fechaAlta = (Date) tareaDelegada.getExecution().getVariable("IDFechaAlta");
		String numTarjeta = (String) tareaDelegada.getExecution().getVariable("IDTarjeta");

		// Acceso al tipo enumerado
		String emisor = (String) tareaDelegada.getExecution().getVariable("IDEmisor");
		String direccion = (String) tareaDelegada.getExecution().getVariable("IDDireccion");

		ServicioClientes servicioClientes = new ServicioClientes();
		int idcliente = servicioClientes.insertar(new Cliente(nombre, direccion, fechaAlta, numTarjeta, emisor, email));

		tareaDelegada.getExecution().setVariable("IDCliente", idcliente);

	}

}
