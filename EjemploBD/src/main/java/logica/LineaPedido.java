package logica;

import java.io.Serializable;

public class LineaPedido implements Serializable {
	private static final long serialVersionUID = 42069L;
	
	private int codigoArticulo, cantidad, codigoPedido;
	
	public LineaPedido(int codigoArticulo, int cantidad) {
		super();

		this.codigoArticulo = codigoArticulo;
		this.cantidad = cantidad;
	}
	
	public LineaPedido(int codigoArticulo, int cantidad, int codigoPedido) {
		this(codigoArticulo, cantidad);
		this.codigoPedido = codigoPedido;
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
	
	public int getCodigoPedido() {
		return this.codigoPedido;
	}

	public void setCodigoPedido(int codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	
}
