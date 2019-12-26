package logica;

import java.util.ArrayList;
import java.util.List;

public class LineaPedido {
	private int cantidad, codigoArticulo;
	private Pedido pedido;
	public LineaPedido(int cantidad, int codigoArticulo, Pedido pedido) {
		super();

		this.cantidad = cantidad;
		this.codigoArticulo = codigoArticulo;
		this.pedido = pedido;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getCodigoArticulo() {
		return this.codigoArticulo;
	}
	
	public void setCodigoArticulo(int codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}
	
	public Pedido getPedido() {
		return this.pedido;
	}
	
}
