package com.jenningsdev.resources;

import com.jenningsdev.ReadData;
import com.jenningsdev.dao.GenericDAO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;


@Path("/emissions")
@ApplicationScoped
public class EmissionsResource {
	@Inject
	GenericDAO dao;
	ReadData readData;
	
	
}
