package logica;

public class LineaPedido {
	private int codigoArticulo, cantidad;
	private Pedido pedido;
	public LineaPedido(int codigoArticulo, int cantidad, Pedido pedido) {
		super();

		this.codigoArticulo = codigoArticulo;
		this.cantidad = cantidad;
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
