package uce.edu.proyecto_final_pw_api_g1.service;

import java.util.List;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Vehiculo;
import uce.edu.proyecto_final_pw_api_g1.service.to.VehiculoTo;

public interface IVehiculoService {

	public String crear(Vehiculo vehiculo);
	
	public List<VehiculoTo> buscaVehiculosDisponibles(String marca, String modelo);
	
	public VehiculoTo buscaVehiculoPorPlaca(String placa);
	
	public Vehiculo buscaVehiculoPlaca(String placa);
	
	public void actualiza(Vehiculo vehiculo);
	
	public String reservaVehiculo(String placa, String cedula, String fechaInicio, String fechaFin, String numeroTarjeta);
	
	public String compruebaVehiculoPorPlacaFecha(String placa, String fInicio, String fFin);

	public void retiraVehiculoReservado(Integer nReserva);
	
	
	public void eliminarVehiculo(Integer id);
	
	public List<VehiculoTo> buscarVehiculo();
	
	public void actualizarId(Vehiculo vehiculo);
	
	
}
