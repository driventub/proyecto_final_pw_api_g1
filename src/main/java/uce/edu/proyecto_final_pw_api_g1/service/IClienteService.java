package uce.edu.proyecto_final_pw_api_g1.service;

import java.util.List;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Cliente;
import uce.edu.proyecto_final_pw_api_g1.service.to.ClienteAuxTo;

public interface IClienteService {

	public String registrarCliente(Cliente cliente);
	
	public Cliente buscarClienteCedula(String idCliente);
	
	public ClienteAuxTo buscarClienteToCedula(String idCliente);
	
	public void actualizarCliente(Cliente cliente);
	
	public void actualizarClienteParcial(Cliente cliente);
	
	
	public List<ClienteAuxTo> verClientes();
	
	public void eliminarCliente(Integer id) ;

	public void actualizarId(Cliente cliente);

	
}
