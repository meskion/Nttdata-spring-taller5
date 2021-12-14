package com.nttdata.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nttdata.spring.persistence.Client;
import com.nttdata.spring.services.ClientManagementServiceI;

@SpringBootApplication
public class NttdataSpringMain implements CommandLineRunner {

	@Autowired
	ClientManagementServiceI service;
	
	public static void main(String[] args)  {
		SpringApplication.run(NttdataSpringMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Client c1 = new Client();
		c1.setDni("123123123");
		c1.setName("manu");
		c1.setFirstSurname("fdez");
		c1.setSecondSurname("delgado");
		service.insertNewClient(c1);
	}

	
}


