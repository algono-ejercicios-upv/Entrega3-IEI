package logica;

import java.util.Date;

public class Cliente {
	private String nombre, direccion;
	private java.util.Date fechaAlta;
	private String numTarjeta, emisor, correoElectronico;

	public Cliente(String nombre, String direccion, Date fechaAlta, String numTarjeta,
			String emisor, String correoElectronico) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaAlta = fechaAlta;
		this.numTarjeta = numTarjeta;
		this.emisor = emisor;
		this.correoElectronico = correoElectronico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public java.util.Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(java.util.Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
}
