package bd;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class InsertarCliente implements TaskListener {
	
	private static final long serialVersionUID = 1L;


	@Override
	public void notify(DelegateTask tareaDelegada) {
		// TODO Auto-generated method stub
		// acceso a las variables introducidas en el formulario.
		String Nombre = (String) tareaDelegada.getExecution().getVariable("IDNombreCliente");
		String email = (String) tareaDelegada.getExecution().getVariable("IDEmail");
		java.util.Date fechaAlta = (java.util.Date) tareaDelegada.getExecution().getVariable("IDFechaAlta");
		String NumTarjeta = (String) tareaDelegada.getExecution().getVariable("IDTarjeta");
		// acceso al tipo enumerado
		String emisor = (String) tareaDelegada.getExecution().getVariable("IDEmisor");
		String caducidad = (String) tareaDelegada.getExecution().getVariable("IDCaducidad");
		ServicioClientes servicio = new ServicioClientes();
		int idcliente = servicio.insertarCliente(Nombre, email, fechaAlta, NumTarjeta, emisor, caducidad);
		tareaDelegada.getExecution().setVariable("IDCliente", idcliente);

	}

}
