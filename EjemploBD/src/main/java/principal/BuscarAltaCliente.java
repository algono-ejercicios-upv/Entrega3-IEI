package principal;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;

@ProcessApplication("Acceso a bases de datos")
public class BuscarAltaCliente extends ServletProcessApplication {
	// La clase se deja vacia a proposito
	// La necesita Camunda como "main"
}
