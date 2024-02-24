package uce.edu.proyecto_final_pw_api_g1.service;

import java.util.List;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Cliente;
import uce.edu.proyecto_final_pw_api_g1.service.to.ClienteAuxTo;
import uce.edu.proyecto_final_pw_api_g1.service.to.ClienteTo;

public interface IClienteService {

	public void registrarCliente(Cliente cliente);
	
	public Cliente buscarClienteCedula(String idCliente);
	
	public ClienteAuxTo buscarClienteToCedula(String idCliente);
	
	public List<ClienteTo> listaClientesVIP();
	
	public void actualizarCliente(Cliente cliente);
	
}
