package bd;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

import logica.LineaPedido;
import logica.Pedido;

public class IntroducirCodigoArticuloCantidad implements TaskListener {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	public static final Map<Integer, Pedido> pedidosEnCurso = new HashMap<Integer, Pedido>();

	@Override
	public void notify(DelegateTask tareaDelegada) {
		// Acceso a las variables introducidas en el formulario
		int articulo = ServicioBD.getVariable(tareaDelegada, "IDArticulo");
		int cantidadArticulo = ServicioBD.getVariable(tareaDelegada, "IDCantidadArticulo");

		String pedidoActualVariableName = "IDPedidoActual";
		Pedido pedidoActual;
		if (tareaDelegada.getExecution().hasVariable(pedidoActualVariableName)) {
			int pedidoActualHashCode = ServicioBD.getVariable(tareaDelegada, pedidoActualVariableName);
			pedidoActual = pedidosEnCurso.get(pedidoActualHashCode);
		} else {
			int idCliente = Integer.parseInt(ServicioBD.<String>getVariable(tareaDelegada, "IDCliente"));
			pedidoActual = new Pedido(new Date(), idCliente);
			int pedidoActualHashCode = pedidoActual.hashCode();
			pedidosEnCurso.put(pedidoActualHashCode, pedidoActual);
			tareaDelegada.getExecution().setVariable(pedidoActualVariableName, pedidoActualHashCode);
		}
		
		pedidoActual.getLineasPedido().add(new LineaPedido(articulo, cantidadArticulo, pedidoActual));
	}

}
