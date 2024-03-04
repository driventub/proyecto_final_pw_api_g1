package uce.edu.proyecto_final_pw_api_g1.service.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class VehiculoTo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String placa;
	
	private String modelo;

	private String marca;

	private String anioFablicacion;
	
	private String paisFabricacion;
	
	private BigDecimal cilindraje;
	
	private BigDecimal precioVehiculo;
	
	private BigDecimal valorDia;
	
	private String disponible;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAnioFablicacion() {
		return anioFablicacion;
	}

	public void setAnioFablicacion(String localDate) {
		this.anioFablicacion = localDate;
	}

	public String getPaisFabricacion() {
		return paisFabricacion;
	}

	public void setPaisFabricacion(String paisFabricacion) {
		this.paisFabricacion = paisFabricacion;
	}

	public BigDecimal getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(BigDecimal cilindraje) {
		this.cilindraje = cilindraje;
	}

	public BigDecimal getPrecioVehiculo() {
		return precioVehiculo;
	}

	public void setPrecioVehiculo(BigDecimal precioVehiculo) {
		this.precioVehiculo = precioVehiculo;
	}

	public BigDecimal getValorDia() {
		return valorDia;
	}

	public void setValorDia(BigDecimal valorDia) {
		this.valorDia = valorDia;
	}

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	
}
