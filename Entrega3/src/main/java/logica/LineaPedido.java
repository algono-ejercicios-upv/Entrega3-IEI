package logica;

import java.io.Serializable;

public class LineaPedido implements Serializable {
	private static final long serialVersionUID = 42069L;
	
	private int idArticulo, cantidad, codigoPedido;
	private String codigoArticulo;
	
	public LineaPedido(int idArticulo, int cantidad) {
		super();

		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
	}
	
	public LineaPedido(int idArticulo, int cantidad, int codigoPedido) {
		this(idArticulo, cantidad);
		this.codigoPedido = codigoPedido;
	}
	
	public LineaPedido(String codigoArticulo, int cantidad) {
		super();

		this.codigoArticulo = codigoArticulo;
		this.cantidad = cantidad;
	}
	
	public LineaPedido(String codigoArticulo, int cantidad, int codigoPedido) {
		this(codigoArticulo, cantidad);
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
	
	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}
	
	public int getCodigoPedido() {
		return this.codigoPedido;
	}

	public void setCodigoPedido(int codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public String getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}
	
}
