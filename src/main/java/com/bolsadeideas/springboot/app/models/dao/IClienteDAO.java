package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteDAO {
	
	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
}
