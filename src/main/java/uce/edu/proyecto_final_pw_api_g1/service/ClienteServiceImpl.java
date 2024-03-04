package uce.edu.proyecto_final_pw_api_g1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uce.edu.proyecto_final_pw_api_g1.repository.ClienteRepoImpl;
import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Cliente;
import uce.edu.proyecto_final_pw_api_g1.service.to.ClienteAuxTo;

@Service

public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepoImpl clienteRepository;

	@Override
	public String registrarCliente(Cliente cliente) {

		try {
			Cliente clie = this.buscarClienteCedula(cliente.getCedula());
			if (clie == null) {
				this.clienteRepository.registrarCliente(cliente);
				return "Cliente registrado correctamente";
			}
		} catch (Exception e) {
			
			return "Algo Fallo al ingresar el Cliente".concat(e.getMessage());
		}

		return "Ya existe un Cliente registrado con la cédula ingresada";

	}

	@Override
	public ClienteAuxTo buscarClienteToCedula(String idCliente) {
		if (this.clienteRepository.buscarClienteCedula(idCliente) == null) {
			return null;
		}
		return convertirCliAuxTo(this.clienteRepository.buscarClienteCedula(idCliente));
	}

	@Override
	public Cliente buscarClienteCedula(String idCliente) {
		if (this.clienteRepository.buscarClienteCedula(idCliente) == null) {
			return null;
		}

		return this.clienteRepository.buscarClienteCedula(idCliente);
	}

	

	private ClienteAuxTo convertirCliAuxTo(Cliente cliente) {
		ClienteAuxTo clAuxTo = new ClienteAuxTo();
		clAuxTo.setId(cliente.getId());
		clAuxTo.setCedula(cliente.getCedula());
		clAuxTo.setNombre(cliente.getNombre());
		clAuxTo.setApellido(cliente.getApellido());
		clAuxTo.setFechaNacimiento(cliente.getFechaNacimiento());
		clAuxTo.setGenero(cliente.getGenero());
		clAuxTo.setTipoRegistro(cliente.getTipoRegistro());
		clAuxTo.setTarjetaCredito(cliente.getTarjetaCredito());
		return clAuxTo;
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		this.clienteRepository.actualizar(cliente);
	}

	@Override
	public void actualizarClienteParcial(Cliente cliente) {
		this.clienteRepository.actualizarParcial(cliente);
	}

	@Override
	public List<ClienteAuxTo> verClientes() {
		List<Cliente> clientes = this.clienteRepository.listarClientes();
		List<ClienteAuxTo> clienteTos = new ArrayList<>();

		for (Cliente cliente : clientes) {
			ClienteAuxTo clienteTo = this.convertirCliAuxTo(cliente);
			clienteTos.add(clienteTo);
		}

		return clienteTos;
	}

	@Override
	public void eliminarCliente(Integer id) {
		// TODO Auto-generated method stub
		this.clienteRepository.eliminarCliente(id);
	}

	@Override
	public void actualizarId(Cliente cliente) {
		// TODO Auto-generated method stub
		this.clienteRepository.actualizarId(cliente);
	}

}
