package uce.edu.proyecto_final_pw_api_g1.repository;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Reserva;

@Repository
@Transactional
public class ReservaRepoImpl implements IReservaRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void crearReserva(Reserva reserva) {
		this.entityManager.persist(reserva);
	}

	@Override
	public Reserva buscaReservaNumero(Integer nReserva) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery("SELECT r FROM Reserva r WHERE r.numeroReserva = :nReserva",Reserva.class);
		myQuery.setParameter("nReserva", nReserva);
		if(myQuery.getResultList().isEmpty()) {
			return null;
		}
		return myQuery.getResultList().get(0);
	}

	@Override
	public void actualiza(Reserva reserva) {
		this.entityManager.merge(reserva);
	}

	@Override
	public List<Reserva> reporteReserva(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		TypedQuery<Reserva> myQuery = this.entityManager
				.createQuery("SELECT r FROM Reserva r WHERE r.fechaInicio >= :fechaInicio AND r.fechaFin <= :fechaFin",Reserva.class);
		myQuery.setParameter("fechaInicio", fechaInicio);
		myQuery.setParameter("fechaFin", fechaFin);
		return myQuery.getResultList();
	}
	
}
