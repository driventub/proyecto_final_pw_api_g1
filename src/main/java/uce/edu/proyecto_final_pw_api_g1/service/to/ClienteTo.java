package uce.edu.proyecto_final_pw_api_g1.service.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClienteTo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cedula;

	private String nombre;

	private String apellido;

	private BigDecimal valorIva;

	private BigDecimal valorTotal;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public BigDecimal getValorIva() {
		return valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
