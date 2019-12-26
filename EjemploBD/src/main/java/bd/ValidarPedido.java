package bd;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import logica.LineaPedido;
import logica.Pedido;

public class ValidarPedido implements JavaDelegate {

	@Override
	public void execute(DelegateExecution ejecucion) throws Exception {
		boolean valido;
		Pedido pedidoActual = (Pedido) ejecucion.getVariable("IDPedidoActual");
		ServicioArticulos servicioArticulos = new ServicioArticulos();
		for(int i = 0; i < pedidoActual.getLineasPedido().size(); i++) {
			LineaPedido lineaPedido = pedidoActual.getLineasPedido().get(i);
			
			System.out.println("Comprobando si existe el código: " + lineaPedido.getCodigoArticulo());
			boolean encontrado = servicioArticulos.buscar(lineaPedido.getCodigoArticulo());

			if(!encontrado) {
				System.out.println("Elemento " + pedidoActual.getLineasPedido().remove(i) + "no existe.");
			}
		}
		
		if(pedidoActual.getLineasPedido().size() > 0) {
			valido = true;
		} else {
			valido = false;
		}
		ejecucion.setVariable("IDValido", valido);
	}
}
