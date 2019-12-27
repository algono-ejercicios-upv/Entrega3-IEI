package bd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import logica.Articulo;
import logica.LineaPedido;
import logica.Pedido;

public class ComprobarStockyReservar implements JavaDelegate {

	@Override
	public void execute(DelegateExecution ejecucion) throws Exception {
		Pedido pedidoActual = (Pedido) ejecucion.getVariable("IDPedidoActual");
		Map<Integer, Articulo> mapArticulos = new HashMap<Integer, Articulo>();
		ServicioArticulos servicioArticulos = new ServicioArticulos();
		
		System.out.println("Comprobando reservas a realizar...");
		
		List<LineaPedido> lineasPedido = pedidoActual.getLineasPedido();
		for(int i = lineasPedido.size() - 1; i >= 0; i--) {
			LineaPedido lineaPedido = lineasPedido.get(i);
			Articulo articulo = servicioArticulos.obtener(lineaPedido.getIdArticulo());
			int cantidadAReservar = lineaPedido.getCantidad();
			
			if(cantidadAReservar <= articulo.getStock()) {
				System.out.println("Reserva de articulo: " + articulo.getCodigoArticulo() + ", realizada.");
				articulo.setReservado(articulo.getReservado() + cantidadAReservar);
				articulo.setStock(articulo.getStock() - cantidadAReservar);
				System.out.println("Actualizando artículo en la base de datos.");
				servicioArticulos.actualizar(articulo);
			} else {
				System.out.println("No se ha podido realizar la reserva. Esta línea de pedido ha sido borrada del pedido.");
				lineasPedido.remove(i);
			}
			if(!mapArticulos.containsKey(articulo.getIdArticulo())) {
				mapArticulos.put(articulo.getIdArticulo(), articulo);	
			} 
		}
		
		ServicioCabeceraPedidos servicioPedidos = new ServicioCabeceraPedidos();
		servicioPedidos.insertar(pedidoActual);
	}
}
