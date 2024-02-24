package uce.edu.proyecto_final_pw_api_g1.repository;

import java.util.List;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Vehiculo;

public interface IVehiculoRepo {
	
	public void crear(Vehiculo vehiculo);
	
	public List<Vehiculo> buscaVehiculosDisponibles(String marca, String modelo);
	
	public Vehiculo buscaVehiculoPorPlaca(String placa);
	
	public void actualiza(Vehiculo vehiculo); 
	
}
