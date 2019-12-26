package logica;

public class Pedido {
	private java.util.Date fechaPedido;
	private int idCliente;

	public Pedido(java.util.Date fechaPedido, int idCliente) {
		super();
		this.fechaPedido = fechaPedido;
		this.idCliente = idCliente;
	}

	public java.util.Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(java.util.Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
}
