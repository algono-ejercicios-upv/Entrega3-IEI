package logica;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private java.util.Date fechaPedido;
	private int idCliente;
	private List<LineaPedido> lineasPedido;

	public Pedido(java.util.Date fechaPedido, int idCliente) {
		super();
		this.fechaPedido = fechaPedido;
		this.idCliente = idCliente;
		lineasPedido = new ArrayList<LineaPedido>();
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
