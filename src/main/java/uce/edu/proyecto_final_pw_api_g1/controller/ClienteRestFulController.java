package uce.edu.proyecto_final_pw_api_g1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uce.edu.proyecto_final_pw_api_g1.repository.modelo.Cliente;
import uce.edu.proyecto_final_pw_api_g1.service.IClienteService;
import uce.edu.proyecto_final_pw_api_g1.service.to.ClienteAuxTo;


@RestController
@RequestMapping("/clientes")
@CrossOrigin
public class ClienteRestFulController {
	
	@Autowired
	private IClienteService clienteService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> crear(@RequestBody Cliente cliente) {
		String msg;
		HttpStatus status;
		try {
			 msg = this.clienteService.registrarCliente(cliente);
			if (msg == "Cliente registrado correctamente" ) {
				status = HttpStatus.OK;
			}else{
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			msg = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			
		}
		return ResponseEntity.status(status).body(msg);	
	}
	
	@GetMapping(path = "/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteAuxTo> buscarClienteCedula(@PathVariable("cedula") String cedula){
		return ResponseEntity.ok(this.clienteService.buscarClienteToCedula(cedula));
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String actualizarCliente(@RequestBody Cliente cliente) {
		String msj = "Cliente Actualizado correctamente";
		try {
			this.clienteService.actualizarCliente(cliente);
		} catch (Exception e) {
			msj = "Error al Actualizar el Cliente" + e;
		}
		return msj;
	}

	
	@PatchMapping(path = "/{cedula}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> actualizarCliente(@PathVariable("cedula") String cedula, @RequestBody Cliente cliente) {
	    cliente.setCedula(cedula);

	    try {
	        this.clienteService.actualizarCliente(cliente);
	        return ResponseEntity.status(HttpStatus.OK).body("Cliente actualizado correctamente");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar cliente");
	    }
	}
	
	@PutMapping("/{cedula}")
    public ResponseEntity<String> actualizarClienteParcial(@PathVariable String cedula, @RequestBody Cliente cliente) {
        try {
            cliente.setCedula(cedula); 
            clienteService.actualizarClienteParcial(cliente);
            return ResponseEntity.ok("Cliente actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el cliente: " + e.getMessage());
        }
    }
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteAuxTo>> listarClientes(){
		return ResponseEntity.ok(this.clienteService.verClientes());
	}
	
	
	@DeleteMapping(path = "/{id}")
	public void eliminarCliente(@PathVariable("id") Integer id){
		 this.clienteService.eliminarCliente(id);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<String> actualizarClienteId(@PathVariable Integer id, @RequestBody Cliente cliente) {
	    try {
	        cliente.setId(id);
	        clienteService.actualizarId(cliente);
	        return ResponseEntity.ok("Cliente actualizado correctamente");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el cliente: " + e.getMessage());
	    }
	}



}
