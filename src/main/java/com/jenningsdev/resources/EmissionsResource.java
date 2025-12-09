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

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.jenningsdev.entities.Emission;
import com.jenningsdev.entities.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Path("/emissions")
@ApplicationScoped
public class EmissionsResource {
	
	@Inject 
	EmissionsService emissionsService;
	
	@GET
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId")int userId){
		return emissionsService.getUser(userId);
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_XML)
	public String addUser(User user) {
		return emissionsService.addUser(user);
	}
	
	@PUT
    @Path("/updateUser/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User updateEmployee(User user){
		return emissionsService.updateUser(user);
    }
	
	@DELETE
    @Path("/deleteUser/{userId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteUser(@PathParam("userId")int userId){
		return emissionsService.deleteUser(userId);
    }
	
	@GET
	@Path("/getXmlData")
	@Produces(MediaType.TEXT_PLAIN)
	public String getXmlEmissions() throws ParserConfigurationException, SAXException, IOException {
		return emissionsService.readXmlData();
	}
	
	@GET
	@Path("/getJsonData")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJsonEmissions() throws ParserConfigurationException, SAXException, IOException {
		return emissionsService.readJsonData();
	}
	
	@GET
    @Path("/getEmission/{emissionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Emission getEmission(@PathParam("emissionId")int emissionId){
		return emissionsService.getEmission(emissionId);
    }
	
	@GET
	@Path("/getAllEmissions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emission> getAllEmissions(){
		return emissionsService.getAllEmissions();
	}
	
	@GET
	@Path("/getAllEmissionsByCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Emission> getAllEmissionsByCategory(){
		return emissionsService.getAllEmissionsByCategory();
	}
	
	@POST
	@Path("/addEmission")
	@Consumes(MediaType.TEXT_XML)
	public String addEmission(Emission emission) {
		return emissionsService.addEmission(emission);
	}
	
	@PUT
    @Path("/updateEmission/{emissionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Emission updateEmission(Emission emission){
		return emissionsService.updateEmission(emission);
    }
	
	@DELETE
    @Path("/deleteEmission/{emissionId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteEmission(@PathParam("emissionId")int emissionId){
		return emissionsService.deleteEmission(emissionId);
    }
}
