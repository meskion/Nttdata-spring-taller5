package com.nttdata.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.spring.persistence.Client;
import com.nttdata.spring.services.ClientManagementServiceI;

@RestController
@RequestMapping("/api")
public class ClientApiController {
	
	@Autowired
	private ClientManagementServiceI service;
	
	@GetMapping("/clients")
	public List<Client> findAll(){
		return service.searchAll();
	}
	
	@GetMapping("/clients/{id}")
    public Client getClient(@PathVariable Long id){
        Client client = service.searchById(id);

        if(client == null) {
            throw new RuntimeException("User id not found -"+ id);
        }
        //retornará al usuario con id pasado en la url
        return client;
    }
	@PostMapping("clients")
	public void addClient(@RequestBody Client client) {
		
		service.insertNewClient(client);
		
	}
	
	public void deleteClient(@PathVariable Long id) {
		Client client = service.searchById(id);
		if (client != null)
			service.deleteClient(client);
	}

}
