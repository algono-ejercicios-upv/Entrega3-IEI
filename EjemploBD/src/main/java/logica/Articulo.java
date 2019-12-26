package logica;

public class Articulo {
	private int idArticulo, stock, reservado;
	private int codigoArticulo;
	private String descripcion;
	private double precioUnitario;
	
	public Articulo(int idArticulo, int codigoArticulo, String descripcion, int stock, int reservado, double precioUnitario) {
		this.idArticulo = idArticulo;
		this.codigoArticulo = codigoArticulo;
		this.descripcion = descripcion;
		this.stock = stock;
		this.reservado = reservado;
		this.precioUnitario = precioUnitario;			
	}
	
	public int getIdArticulo() {
		return this.idArticulo;
	}
	
	public void setIdArticuloo(int idArticulo) {
		this.idArticulo = idArticulo;
	}
	
	public int getCodigoArticulo() {
		return this.codigoArticulo;
	}
	
	public void setCodigoArticulo(int codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getReservado() {
		return this.reservado;
	}
	
	public void setReservado(int reservado) {
		this.reservado = reservado;
	}
	
	public double getPrecioUnitario() {
		return this.precioUnitario;
	}
	
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
}
