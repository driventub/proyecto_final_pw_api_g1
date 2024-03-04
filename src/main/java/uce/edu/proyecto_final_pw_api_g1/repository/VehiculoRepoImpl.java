package uce.edu.proyecto_final_pw_api_g1.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Vehiculo;

@Repository
@Transactional
public class VehiculoRepoImpl implements IVehiculoRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void crear(Vehiculo vehiculo) {
		this.entityManager.persist(vehiculo);	
	}

	@Override
	public List<Vehiculo> buscaVehiculosDisponibles(String marca, String modelo) {
		TypedQuery<Vehiculo> myQuery = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v WHERE v.marca =:marca AND v.modelo =:modelo", Vehiculo.class);
		myQuery.setParameter("marca", marca);
		myQuery.setParameter("modelo", modelo);
		return myQuery.getResultList();
	}

	@Override
	public Vehiculo buscaVehiculoPorPlaca(String placa) {
		TypedQuery<Vehiculo> myQuery = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v WHERE v.placa =:placa", Vehiculo.class);
		myQuery.setParameter("placa", placa);
		if(myQuery.getResultList().isEmpty()) {
			return null;
		}
		return myQuery.getResultList().get(0);
	}

	@Override
	public void actualiza(Vehiculo vehiculo) {
		this.entityManager.persist(vehiculo);
	}

	@Override
	public void eliminarVehiculo(Integer id) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo = this.buscarVehiculoId(id);
		this.entityManager.remove(vehiculo);
	}

	@Override
	public List<Vehiculo> listarVehiculo() {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery("SELECT v FROM Vehiculo v",Vehiculo.class);
		return myQuery.getResultList();
	}

	@Override
	public Vehiculo buscarVehiculoId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Vehiculo.class, id);
	}
	
	public void actualizarId(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		
		this.entityManager.merge(vehiculo);

	}


}
