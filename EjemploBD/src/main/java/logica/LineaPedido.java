package logica;

import java.util.ArrayList;
import java.util.List;

public class LineaPedido {
	private int cantidad, codigoArticulo;
	private List<Articulo> articulos;
	
	public LineaPedido(int cantidad, int codigoArticulo) {
		super();

		this.cantidad = cantidad;
		this.codigoArticulo = codigoArticulo;
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
}
