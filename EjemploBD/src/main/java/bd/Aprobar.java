package bd;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class Aprobar implements TaskListener {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	
	@Override
	public void notify(DelegateTask tareaDelegada) {
		boolean aprobado = (boolean) tareaDelegada.getExecution().getVariable("IDAprobado");
		System.out.println("Terminaci�n del proceso con resultado: " + aprobado);
	}

}
