package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Repository
public class ClienteDAOImpl implements IClienteDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public void save(Cliente cliente) {
		Long id = cliente.getId();
		if (isValidID(id))
			em.merge(cliente);
		else
			em.persist(cliente);

	}

	@Override
	public Cliente findOne(long id) {

		return em.find(Cliente.class, id);
	}

	@Override
	public void delete(long id) {
		em.remove(findOne(id));

	}

	private boolean isValidID(Long id) {
		return id != null && id > 0;
	}
}
