package com.jenningsdev.dao;

import java.util.List;

import com.jenningsdev.entities.Emission;
import com.jenningsdev.entities.User;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

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
	
	public User getUser(int userId) {
		List<User> users = (List<User>) 
		em.createNamedQuery("User.getUser").setParameter("id", userId).getResultList();
		em.close();
		User user = new User();
		for(User u: users) {
			if (u.getId() == userId) {
				return u;
			}
		}
		return user;
	}
	
	public Emission getEmission(int emissionId) {
		List<Emission> emissions = (List<Emission>) 
		em.createNamedQuery("Emission.getEmission").setParameter("id", emissionId).getResultList();
		em.close();
		Emission emission = new Emission();
		for(Emission e: emissions) {
			if (e.getId() == emissionId) {
				return e;
			}
		}
		return emission;
	}
}
