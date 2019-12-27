package logica;

import java.io.Serializable;

public class LineaPedido implements Serializable {
	private static final long serialVersionUID = 42069L;
	
	private int idArticulo, cantidad, codigoPedido;
	
	public LineaPedido(int idArticulo, int cantidad) {
		super();

		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
	}
	
	public LineaPedido(int idArticulo, int cantidad, int codigoPedido) {
		this(idArticulo, cantidad);
		this.codigoPedido = codigoPedido;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getIdArticulo() {
		return this.idArticulo;
	}
	
	public void setIdArticulo(int codigoArticulo) {
		this.idArticulo = codigoArticulo;
	}
	
	public int getCodigoPedido() {
		return this.codigoPedido;
	}

	public void setCodigoPedido(int codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	
}
