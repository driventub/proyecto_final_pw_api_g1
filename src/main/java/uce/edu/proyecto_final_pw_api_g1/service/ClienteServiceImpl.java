package uce.edu.proyecto_final_pw_api_g1.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uce.edu.proyecto_final_pw_api_g1.repository.ClienteRepoImpl;
import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Cliente;
import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Reserva;
import uce.edu.proyecto_final_pw_api_g1.service.to.ClienteAuxTo;
import uce.edu.proyecto_final_pw_api_g1.service.to.ClienteTo;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepoImpl clienteRepository;

	@Override
	public void registrarCliente(Cliente cliente) {
		this.clienteRepository.registrarCliente(cliente);
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

	@Override
	public List<ClienteTo> listaClientesVIP() {
		List<Cliente> lstClientes = this.clienteRepository.listarClientes();
		List<ClienteTo> lstClientesTo = new ArrayList<>();
		for (Cliente c : lstClientes) {
			lstClientesTo.add(convertirClienteTo(c));
		}
		lstClientesTo.sort(Comparator.comparing(ClienteTo::getValorTotal).reversed());
		return lstClientesTo;
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

	private ClienteTo convertirClienteTo(Cliente cliente) {
		ClienteTo clTo = new ClienteTo();
		clTo.setCedula(cliente.getCedula());
		clTo.setNombre(cliente.getNombre());
		clTo.setApellido(cliente.getApellido());
		clTo.setValorIva(valorIva(cliente.getReservas()));
		clTo.setValorTotal(valorTotal(cliente.getReservas()));

		return clTo;
	}

	private BigDecimal valorIva(List<Reserva> reservasCliente) {
		BigDecimal valorI = new BigDecimal(0);
		for (Reserva reserva : reservasCliente) {

			valorI = valorI.add(reserva.getCobroRealizado().getValorIva());
		}
		return valorI;
	}

	private BigDecimal valorTotal(List<Reserva> reservasCliente) {
		BigDecimal valorT = new BigDecimal(0);
		for (Reserva reserva : reservasCliente) {
			valorT = valorT.add(reserva.getValorPagar());
		}
		return valorT;
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		this.clienteRepository.actualizar(cliente);
	}

	@Override
	public void actualizarClienteParcial(Cliente cliente) {
		// TODO Auto-generated method stub
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
