package com.nttdata.spring.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nttdata.spring.persistence.Client;
import com.nttdata.spring.services.ClientManagementServiceI;

@Controller

public class ClientController {

	@Autowired
	ClientManagementServiceI service;

//	@GetMapping("/index")
	@GetMapping(path = { "/index", "/searchclient" })
	public String showClientList(Model model, String name) {

		if (name != null && !name.isBlank()) {

			model.addAttribute("clients", service.searchByName(name));
		} else {
			model.addAttribute("clients", service.searchAll());
		}
		return "index";
	}

	@GetMapping("/signup")
	public String showAddForm(Client newClient) {
		return "add-client";
	}

	@PostMapping("/addclient")
	public String addClient(@Valid Client newClient, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-client";
		}
		service.insertNewClient(newClient);
		return "redirect:/index";

	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Client updatedClient = service.searchById(id);
		model.addAttribute("client", updatedClient);
		return "update-client";
	}

	@PostMapping("/update/{id}")
	public String updateClient(@PathVariable("id") long id, @Valid Client updatedClient, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			updatedClient.setId(id);
			return "update-client";
		}

		service.updateClient(updatedClient);
		return "redirect:/index";
	}

	@GetMapping("/delete/{id}")
	public String deleteClient(@PathVariable("id") long id, Model model) {
		Client deletedClient = service.searchById(id);
		service.deleteClient(deletedClient);
		return "redirect:/index";
	}

}
