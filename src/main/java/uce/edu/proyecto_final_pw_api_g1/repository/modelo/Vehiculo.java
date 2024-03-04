package uce.edu.proyecto_final_pw_api_g1.repository.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "vehi_id")
	private Integer id;
	
	@Column(name = "vehi_placa")
	private String placa;
	
	@Column(name = "vehi_modelo")
	private String modelo;
	
	@Column(name = "vehi_marca")
	private String marca;
	
	@Column(name = "vehi_anio_fabricacion")
	private String anioFablicacion;
	
	@Column(name = "vehi_pais_fabricacion")
	private String paisFabricacion;
	
	@Column(name = "vehi_cilindraje")
	private BigDecimal cilindraje;
	
	@Column(name = "vehi_precio_vehiculo")
	private BigDecimal precioVehiculo;
	
	@Column(name = "vehi_valor_dia")
	private BigDecimal valorDia;
	
	@Column(name = "vehi_disponible")
	private String disponible;
	
	@OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
	private List<Reserva> reserva;
	
	public String getAnioFablicacion() {
		return anioFablicacion;
	}

	public void setAnioFablicacion(String anioFablicacion) {
		this.anioFablicacion = anioFablicacion;
	}

	public void añadirReserva(Reserva r) {
		this.reserva.add(r);
	}

	public List<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}

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
