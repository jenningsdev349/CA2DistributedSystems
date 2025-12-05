package com.jenningsdev.dao;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GenericDAO {

	@Inject
	EntityManager em;
	
	public GenericDAO() {
		
	}

	public void persist(Object object) {
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Object object) {
		em.getTransaction().begin();
		em.remove(em.merge(object));
		em.getTransaction().commit();
		em.close();
	}
	
	public Object merge(Object object) {
		em.getTransaction().begin();
		Object updatedObject = em.merge(object);
		em.getTransaction().commit();
		em.close();
		return updatedObject;
	}
}
