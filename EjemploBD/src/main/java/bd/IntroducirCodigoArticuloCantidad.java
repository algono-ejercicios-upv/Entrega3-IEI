package bd;

import java.util.Date;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

import logica.LineaPedido;

public class IntroducirCodigoArticuloCantidad implements TaskListener {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask tareaDelegada) {
		// Acceso a las variables introducidas en el formulario
		int articulo = (int) tareaDelegada.getExecution().getVariable("IDArticulo");
		int cantidadArticulo = (int) tareaDelegada.getExecution().getVariable("IDCantidadArticulo");
		boolean masArticulos = (boolean) tareaDelegada.getExecution().getVariable("IDOtroArticulo");

		
	}

}
