package uce.edu.proyecto_final_pw_api_g1.repository.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "rese_id")
	private Integer id;
	
	@Column(name = "rese_numero_reserva")
	private Integer numeroReserva;
	
	@Column(name = "rese_fecha_inicio")
	private LocalDateTime fechaInicio;
	
	@Column(name = "rese_fecha_fin")
	private LocalDateTime fechaFin;
	
	@Column(name = "rese_estado")
	private String estado;//Generado-Ejecutado
	
	@Column(name = "rese_valor_pagar")
	private BigDecimal valorPagar;//iva+subtotal
	
	@ManyToOne
	@JoinColumn(name = "clie_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "vehi_id")
	private Vehiculo vehiculo;
	
	@OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
	private CobroRealizado cobroRealizado;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public CobroRealizado getCobroRealizado() {
		return cobroRealizado;
	}

	public void setCobroRealizado(CobroRealizado cobroRealizado) {
		this.cobroRealizado = cobroRealizado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(Integer numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(BigDecimal valorPagar) {
		this.valorPagar = valorPagar;
	}
	
	
	

}
