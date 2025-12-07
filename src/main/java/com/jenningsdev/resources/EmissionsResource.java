package com.jenningsdev.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.jenningsdev.ReadData;
import com.jenningsdev.dao.GenericDAO;
import com.jenningsdev.entities.Emission;
import com.jenningsdev.entities.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@Path("/emissions")
@ApplicationScoped
public class EmissionsResource {
	@Inject
	GenericDAO dao;
	ReadData readData;
	
	@GET
    @Path("/users/{userId}")
    @Produces(MediaType.TEXT_PLAIN)
    public User getUser(@PathParam("userId")int userId){
		return dao.getUser(userId);		
    }
	
	@POST
	@Path("/register")
	@Consumes(MediaType.TEXT_XML)
	public String addUser(User user) {
		dao.persist(user);
		return "User registered: " + user.getEmail();
	}
	
	@PUT
    @Path("/updateUser/{userId}")
    @Produces("application/json")
    public User updateEmployee(User user){
		return (User) dao.merge(user);	
    }
	
	@DELETE
    @Path("/deleteUser/{userId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteUser(@PathParam("userId")int userId){
		User user = dao.getUser(userId);
		dao.remove(user);
		return "User " + userId +" deleted";
    }
	
	@GET
    @Path("/emissions/{emissionId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Emission getEmission(@PathParam("emissionId")int emissionId){
		return dao.getEmission(emissionId);		
    }
	
	@POST
	@Path("/addEmission")
	@Consumes(MediaType.TEXT_XML)
	public String addEmission(Emission emission) {
		dao.persist(emission);
		return "Emission created: " + emission.getId();
	}
	
	@PUT
    @Path("/updateEmission/{emissionId}")
    @Produces("application/json")
    public Emission updateEmission(Emission emission){
		return (Emission) dao.merge(emission);	
    }
	
	@DELETE
    @Path("/deleteEmission/{emissionId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteEmission(@PathParam("emissionId")int emissionId){
		Emission emission = dao.getEmission(emissionId);
		dao.remove(emission);
		return "Emission " + emissionId +" deleted";
    }
}
