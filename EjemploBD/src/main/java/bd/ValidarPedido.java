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
		
		for (int i = pedidoActual.getLineasPedido().size() - 1; i >= 0; i--) {
			LineaPedido lineaPedido = pedidoActual.getLineasPedido().get(i);
			
			System.out.println("Comprobando si existe el código: " + lineaPedido.getIdArticulo());
			boolean encontrado = servicioArticulos.buscar(lineaPedido.getIdArticulo());

			if (!encontrado) {
				LineaPedido lineaEliminada = pedidoActual.getLineasPedido().remove(i);
				System.out.println("Elemento " + lineaEliminada + "no existe.");
			}
		}
		
		valido = pedidoActual.getLineasPedido().size() > 0;
		ejecucion.setVariable("IDValido", valido);
	}
}
