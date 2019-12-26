package bd;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

import logica.LineaPedido;
import logica.Pedido;

public class IntroducirCodigoArticuloCantidad implements TaskListener {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Override
	public void notify(DelegateTask tareaDelegada) {
		// Acceso a las variables introducidas en el formulario
		int articulo = Integer.parseInt(ServicioBD.<String>getVariable(tareaDelegada, "IDArticulo"));
		int cantidadArticulo = Integer.parseInt(ServicioBD.<String>getVariable(tareaDelegada, "IDCantidadArticulo"));

		String pedidoActualVariableName = "IDPedidoActual";
		Pedido pedidoActual;
		if (tareaDelegada.getExecution().hasVariable(pedidoActualVariableName)) {
			pedidoActual = ServicioBD.getVariable(tareaDelegada, pedidoActualVariableName);
		} else {
			int idCliente = Integer.parseInt(ServicioBD.<String>getVariable(tareaDelegada, "IDCliente"));
			pedidoActual = new Pedido(new Date(), idCliente);
			tareaDelegada.getExecution().setVariable(pedidoActualVariableName, pedidoActual);
		}
		
		pedidoActual.getLineasPedido().add(new LineaPedido(articulo, cantidadArticulo, pedidoActual));
	}

}
