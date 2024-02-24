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
		TypedQuery<Cliente> myQuery = this.em.createQuery("SELECT c FROM Cliente c WHERE c.cedula = :idCliente",Cliente.class);
		myQuery.setParameter("idCliente", idCliente);
		if(myQuery.getResultList().isEmpty()) {
			return null;
		}
		return myQuery.getResultList().get(0);
	}

	@Override
	public List<Cliente> listarClientes() {
		TypedQuery<Cliente> myQuery = this.em.createQuery("SELECT c FROM Cliente c",Cliente.class);
		return myQuery.getResultList();
	}

	@Override
	public void actualizar(Cliente cliente) {
		this.em.merge(cliente);
	}
	
}
