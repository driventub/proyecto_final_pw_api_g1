package uce.edu.proyecto_final_pw_api_g1.service;

import java.util.List;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Reserva;
import uce.edu.proyecto_final_pw_api_g1.service.to.ReservaReporteTo;
import uce.edu.proyecto_final_pw_api_g1.service.to.ReservaTo;

public interface IReservaService {

	public void crearReserva(Reserva reserva);
	
	public Reserva buscaReservaNumero(Integer nReserva);
	
	public void actualiza(Reserva reserva);
	
	public ReservaTo buscaReservaNumeroTo(Integer nReserva);
	
	public List<ReservaReporteTo> reporteReserva(String fechaInicio, String fechaFin);
	
	
}
