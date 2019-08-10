package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.models.dao.IClienteDAO;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteDAO clienteDAO;

	@GetMapping(value = "/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Clientes");
		model.addAttribute("clientes", clienteDAO.findAll());
		return "listar";
	}

	@GetMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();

		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}

	@PostMapping(value = "/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Listado de Clientes");
			return "form";
		}

		clienteDAO.save(cliente);
		status.setComplete();
		return "redirect:/listar";
	}

	@GetMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {
		Cliente cliente = null;

		if (id > 0)
			cliente = clienteDAO.findOne(id);
		else
			return "redirect:/listar";
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Editar Cliente");
		
		return "form";
	}
	
	@GetMapping(value ="/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0)
			clienteDAO.delete(id);
		
		return "redirect:/listar";
	}
}
