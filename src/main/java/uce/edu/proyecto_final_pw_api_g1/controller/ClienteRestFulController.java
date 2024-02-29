package uce.edu.proyecto_final_pw_api_g1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Cliente;
import uce.edu.proyecto_final_pw_api_g1.service.IClienteService;
import uce.edu.proyecto_final_pw_api_g1.service.to.ClienteAuxTo;
import uce.edu.proyecto_final_pw_api_g1.service.to.ClienteTo;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
public class ClienteRestFulController {
	
	@Autowired
	private IClienteService clienteService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String crear(@RequestBody Cliente cliente) {
		String msj = "Cliente ingresado correctamente";
		try {
			this.clienteService.registrarCliente(cliente);
		} catch (Exception e) {
			msj = "Error al ingresar el Cliente" + e;
		}
		return msj;
	}
	
	@GetMapping(path = "/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteAuxTo> buscarClienteCedula(@PathVariable("cedula") String cedula){
		return ResponseEntity.ok(this.clienteService.buscarClienteToCedula(cedula));
	}
	
	@GetMapping(path = "/vip" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteTo>> listarClientesVip(){
		return ResponseEntity.ok(this.clienteService.listaClientesVIP());
	}
}
