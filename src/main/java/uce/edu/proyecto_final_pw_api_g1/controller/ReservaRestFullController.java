package uce.edu.proyecto_final_pw_api_g1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Reserva;
import uce.edu.proyecto_final_pw_api_g1.service.IReservaService;
import uce.edu.proyecto_final_pw_api_g1.service.to.ReservaReporteTo;
import uce.edu.proyecto_final_pw_api_g1.service.to.ReservaTo;

@RestController
@RequestMapping("/reservas")
@CrossOrigin
public class ReservaRestFullController {
	
	@Autowired
	private IReservaService reservaService;
	
	@GetMapping(path = "/numeroR/{nReserva}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ReservaTo buscaReservaNumeroTo(@PathVariable("nReserva") Integer nReserva) {
		return this.reservaService.buscaReservaNumeroTo(nReserva);
	}
	
	@GetMapping(path = "/{numReserva}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reserva> getReserva(@PathVariable("numReserva") Integer numReserva){
		return ResponseEntity.ok(this.reservaService.buscaReservaNumero(numReserva));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReservaReporteTo>> reporteReservas(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin){
		return ResponseEntity.ok(this.reservaService.reporteReserva(fechaInicio, fechaFin));
	}
	
}
