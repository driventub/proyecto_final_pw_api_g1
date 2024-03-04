package uce.edu.proyecto_final_pw_api_g1.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Cliente;

@Repository
@Transactional
public class ClienteRepoImpl implements IClienteRepo {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void registrarCliente(Cliente cliente) {
		this.em.persist(cliente);
	}

	@Override
	public Cliente buscarClienteCedula(String idCliente) {
		TypedQuery<Cliente> myQuery = this.em.createQuery("SELECT c FROM Cliente c WHERE c.cedula = :idCliente",
				Cliente.class);
		myQuery.setParameter("idCliente", idCliente);
		if (myQuery.getResultList().isEmpty()) {
			return null;
		}
		return myQuery.getSingleResult();
	}

	@Override
	public List<Cliente> listarClientes() {
		TypedQuery<Cliente> myQuery = this.em.createQuery("SELECT c FROM Cliente c", Cliente.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizar(Cliente cliente) {
		Cliente clienteExistente = this.em
				.createQuery("SELECT c FROM Cliente c WHERE c.cedula = :cedula", Cliente.class)
				.setParameter("cedula", cliente.getCedula()).getSingleResult();
		clienteExistente.setNombre(cliente.getNombre());
		clienteExistente.setApellido(cliente.getApellido());
		clienteExistente.setFechaNacimiento(cliente.getFechaNacimiento());
		clienteExistente.setGenero(cliente.getGenero());
		clienteExistente.setTipoRegistro(cliente.getTipoRegistro());
		clienteExistente.setTarjetaCredito(cliente.getTarjetaCredito());

		this.em.merge(clienteExistente);
	}

	public void actualizarParcial(Cliente cliente) {
		Cliente clienteExistente = this.em
				.createQuery("SELECT c FROM Cliente c WHERE c.cedula = :cedula", Cliente.class)
				.setParameter("cedula", cliente.getCedula()).getSingleResult();

		if (cliente.getNombre() != null) {
			clienteExistente.setNombre(cliente.getNombre());
		}
		if (cliente.getApellido() != null) {
			clienteExistente.setApellido(cliente.getApellido());
		}
		if (cliente.getFechaNacimiento() != null) {
			clienteExistente.setFechaNacimiento(cliente.getFechaNacimiento());
		}
		if (cliente.getGenero() != null) {
			clienteExistente.setGenero(cliente.getGenero());
		}
		if (cliente.getTipoRegistro() != null) {
			clienteExistente.setTipoRegistro(cliente.getTipoRegistro());
		}
		if (cliente.getTarjetaCredito() != null) {
			clienteExistente.setTarjetaCredito(cliente.getTarjetaCredito());
		}

		this.em.merge(clienteExistente);
	}

	@Override
	public void eliminarCliente(Integer id) {
		Cliente cliente = this.buscarClienteId(id);
		this.em.remove(cliente);

	}

	@Override
	public Cliente buscarClienteId(Integer id) {
		return this.em.find(Cliente.class, id);
	}

	@Override
	public void actualizarId(Cliente cliente) {
		// TODO Auto-generated method stub
		
		this.em.merge(cliente);

	}

}
