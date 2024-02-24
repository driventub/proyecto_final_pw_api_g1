package uce.edu.proyecto_final_pw_api_g1.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uce.edu.proyecto_final_pw_api_g1.repository.IVehiculoRepo;
import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Cliente;
import uce.edu.proyecto_final_pw_api_g1.repository.modelo.CobroRealizado;
import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Reserva;
import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Vehiculo;
import uce.edu.proyecto_final_pw_api_g1.service.to.VehiculoTo;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	public static final BigDecimal IVA = new BigDecimal("0.12");

	@Autowired
	private IVehiculoRepo vehiculoRepository;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IReservaService reservaService;

	@Override
	public void crear(Vehiculo vehiculo) {
		this.vehiculoRepository.crear(vehiculo);
	}

	@Override
	public void retiraVehiculoReservado(Integer nReserva) {
		Reserva aux = this.reservaService.buscaReservaNumero(nReserva);
		if (aux != null) {
			Vehiculo v = this.buscaVehiculoPlaca(aux.getVehiculo().getPlaca());
			v.setDisponible("ND");
			this.vehiculoRepository.actualiza(v);
			aux.setEstado("E");
			this.reservaService.actualiza(aux);
		}
	}

	@Override
	public List<VehiculoTo> buscaVehiculosDisponibles(String marca, String modelo) {
		List<Vehiculo> lista = this.vehiculoRepository.buscaVehiculosDisponibles(marca, modelo);
		List<VehiculoTo> listaFinal = lista.stream().map(vehiculo -> this.convertir(vehiculo))
				.collect(Collectors.toList());
		return listaFinal;
	}

	@Override
	public VehiculoTo buscaVehiculoPorPlaca(String placa) {
		Vehiculo vehiculo = this.vehiculoRepository.buscaVehiculoPorPlaca(placa);
		if (vehiculo == null) {
			return null;
		}
		VehiculoTo vehiculoFin = convertir(vehiculo);
		return vehiculoFin;
	}

	@Override
	public Vehiculo buscaVehiculoPlaca(String placa) {
		if (this.vehiculoRepository.buscaVehiculoPorPlaca(placa) == null) {
			return null;
		}
		return this.vehiculoRepository.buscaVehiculoPorPlaca(placa);
	}

	@Override
	public void actualiza(Vehiculo vehiculo) {
		this.vehiculoRepository.actualiza(vehiculo);
	}

	@Override
	public String reservaVehiculo(String placa, String cedula, String fechaInicio, String fechaFin,
			String numeroTarjeta) {

		LocalDateTime fInicio = LocalDateTime.parse(fechaInicio);
		LocalDateTime fFin = LocalDateTime.parse(fechaFin);
		String disponible = compruebaVehiculo(placa, fInicio, fFin);
		String mensaje = "";
		if (disponible == "ND") {
			mensaje = "El vehículo de placa: " + placa + " no está disponible";
			return mensaje;
		} else {
			Vehiculo vehiculo = this.vehiculoRepository.buscaVehiculoPorPlaca(placa);
			Reserva reserva = new Reserva();
			reserva.setFechaInicio(fInicio);
			reserva.setFechaFin(fFin);

			BigDecimal subtotal = this.diasReservado(fInicio, fFin).multiply(vehiculo.getValorDia());
			BigDecimal valIva = subtotal.multiply(IVA);
			BigDecimal valTotal = subtotal.add(valIva);

			reserva.setValorPagar(valTotal);
			Integer num = (int) (Math.random() * (10000 + 1));
			reserva.setNumeroReserva(num);
			reserva.setEstado("G");

			Cliente cliente = this.clienteService.buscarClienteCedula(cedula);
			cliente.setTarjetaCredito(numeroTarjeta);
			reserva.setCliente(cliente);
			reserva.setVehiculo(vehiculo);
			CobroRealizado cobroRealizado = new CobroRealizado();
			cobroRealizado.setFechaCobro(fechaInicio);
			cobroRealizado.setTarjeta(numeroTarjeta);
			cobroRealizado.setValorSubtotal(subtotal);
			cobroRealizado.setValorIva(valIva);
			cobroRealizado.setValorPagar(valTotal);
			cobroRealizado.setReserva(reserva);

			reserva.setCobroRealizado(cobroRealizado);
			vehiculo.añadirReserva(reserva);
			this.clienteService.actualizarCliente(cliente);
			this.reservaService.crearReserva(reserva);

			this.vehiculoRepository.actualiza(vehiculo);
			mensaje = "Vehiculo reservado correctamente, numero de reserva: " + num.toString();
			return mensaje;
		}

	}

	public BigDecimal diasReservado(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		Long diasReservado = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
		if (diasReservado > 0) {
			return new BigDecimal(diasReservado);
		} else {
			return new BigDecimal(1);
		}
	}

	public String compruebaVehiculo(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		Vehiculo vehiculo = vehiculoRepository.buscaVehiculoPorPlaca(placa);
		if (vehiculo != null) {
			return compruebaDisponible(fechaInicio, fechaFin, vehiculo.getReserva());
		} else {
			return null;
		}
	}

	public String compruebaVehiculoPorPlacaFecha(String placa, String fInicio, String fFin) {
		LocalDateTime fechaInicio = LocalDateTime.parse(fInicio);
		LocalDateTime fechaFin = LocalDateTime.parse(fFin);
		Vehiculo vehiculo = vehiculoRepository.buscaVehiculoPorPlaca(placa);
		String msj = "";
		if (vehiculo != null) {
			String d = compruebaDisponible(fechaInicio, fechaFin, vehiculo.getReserva());

			if (d == "D") {
				
				BigDecimal subtotal = this.diasReservado(fechaInicio,fechaFin).multiply(vehiculo.getValorDia());
				BigDecimal valTotal = subtotal.add(subtotal.multiply(IVA));
				
				msj = "El vehiculo de placa: " + placa + " está disponible, el valor a pagar es: "
						+ valTotal.toString();
			} else {
				msj = "El vehiculo de placa: " + placa + " no está disponible, sus proximas fechas son: "
						+ fechaFin.plusDays(1).toString();
			}
		}
		return msj;
	}

	public String compruebaDisponible(LocalDateTime fechaInicio, LocalDateTime fechaFin, List<Reserva> reservacion) {
		if (reservacion.isEmpty()) {
			return "D";
		} else {
			for (Reserva reserva : reservacion) {
				if (!fechaInicio.isBefore(reserva.getFechaFin()) && !fechaFin.isAfter(reserva.getFechaFin())) {
					return "ND";
				}
				if (!fechaFin.isBefore(reserva.getFechaInicio()) && !fechaFin.isAfter(reserva.getFechaFin())) {
					return "ND";
				}

			}
			return "D";
		}
	}

	private VehiculoTo convertir(Vehiculo vehiculo) {
		VehiculoTo vehiT = new VehiculoTo();
		vehiT.setId(vehiculo.getId());
		vehiT.setPlaca(vehiculo.getPlaca());
		vehiT.setModelo(vehiculo.getModelo());
		vehiT.setMarca(vehiculo.getMarca());
		vehiT.setAnioFablicacion(vehiculo.getAnioFablicacion().toString());
		vehiT.setPaisFabricacion(vehiculo.getPaisFabricacion());
		vehiT.setCilindraje(vehiculo.getCilindraje());
		vehiT.setPrecioVehiculo(vehiculo.getPrecioVehiculo());
		vehiT.setValorDia(vehiculo.getValorDia());
		vehiT.setDisponible(vehiculo.getDisponible());
		return vehiT;
	}

}
