package uce.edu.proyecto_final_pw_api_g1.repository;

import java.util.List;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Cliente;

public interface IClienteRepo {

	public void registrarCliente(Cliente cliente);
	
	public Cliente buscarClienteCedula(String idCliente);
	
	public List<Cliente> listarClientes();
	
	public void actualizar(Cliente cliente);
}
