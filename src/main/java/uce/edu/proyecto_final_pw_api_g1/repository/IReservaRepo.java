package uce.edu.proyecto_final_pw_api_g1.repository;

import java.time.LocalDateTime;
import java.util.List;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Reserva;

public interface IReservaRepo {
	
	public void crearReserva(Reserva reserva);
	
	public Reserva buscaReservaNumero(Integer nReserva);
	
	public void actualiza(Reserva reserva);
	
	public List<Reserva> reporteReserva(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
}
