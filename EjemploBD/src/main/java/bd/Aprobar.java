package bd;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class Aprobar implements TaskListener {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	
	@Override
	public void notify(DelegateTask tareaDelegada) {
		// TODO Auto-generated method stub
		boolean Aprobado = (Boolean) tareaDelegada.getExecution().getVariable("IDAprobado");
		
		System.out.println("Terminación del proceso con resultado " + Aprobado);
	}

}
