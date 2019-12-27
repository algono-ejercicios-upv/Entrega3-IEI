package bd;

import java.util.List;

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
		
		List<LineaPedido> lineasPedido = pedidoActual.getLineasPedido();
		for (int i = lineasPedido.size() - 1; i >= 0; i--) {
			LineaPedido lineaPedido = lineasPedido.get(i);
			
			String codigoArticulo = lineaPedido.getCodigoArticulo();
			System.out.println("Comprobando si existe el código: " + codigoArticulo);
			int idArticulo = servicioArticulos.buscar(codigoArticulo);

			if (idArticulo > 0) {
				lineaPedido.setIdArticulo(idArticulo);
			} else {
				LineaPedido lineaEliminada = lineasPedido.remove(i);
				System.out.println("Elemento " + lineaEliminada + " no existe.");
			}
		}
		
		valido = lineasPedido.size() > 0;
		ejecucion.setVariable("IDValido", valido);
	}
}
