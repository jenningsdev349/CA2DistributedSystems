package com.jenningsdev.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jenningsdev.dao.GenericDAO;
import com.jenningsdev.entities.Emission;
import com.jenningsdev.entities.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EmissionsService {
	@Inject
    EntityManager em; 
	
	GenericDAO dao = new GenericDAO();
	
	@Transactional
	public String readXmlData() throws ParserConfigurationException, SAXException, IOException {
		File xmlFile = new File("GreenhouseGasEmissionsPredicted2025.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);   
        
        NodeList emissions = doc.getElementsByTagName("Row");
        
        for (int i = 0; i < emissions.getLength(); i++) {

            Node emissionNode = emissions.item(i);
            
            if (emissionNode.getNodeType() == Node.ELEMENT_NODE) {
            	
                Element elem = (Element) emissionNode;
                
                Node node1 = elem.getElementsByTagName("Value").item(0);
                double value;
                
                try {
                    value = Double.parseDouble(node1.getTextContent());
                }
                catch (NumberFormatException e) {
                	value = 0;
                }

                Node node2 = elem.getElementsByTagName("Category__1_3").item(0);
                String category = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("Year").item(0);
                int year = Integer.parseInt(node3.getTextContent());

                Node node4 = elem.getElementsByTagName("Scenario").item(0);
                String scenario = node4.getTextContent();      
                
                Node node5 = elem.getElementsByTagName("Gas___Units").item(0);
                String gasUnits = node5.getTextContent();   
                       
                if(value > 0 && scenario.equals("WEM") && year == 2023) {
                	Emission emission = new Emission(category, gasUnits, scenario, value, year);
                	em.persist(emission);
                }
            }
        }
        return "DB Populated!";
	}
	
	@Transactional
	public String readJsonData() throws FileNotFoundException {
		FileInputStream fis = new FileInputStream("GreenhouseGasEmissions2025.json");
        
        JsonReader jsonReader = Json.createReader(fis);
        JsonArray emissionsArray = jsonReader.readObject().getJsonArray("Emissions");
        
        
        for (Object object : emissionsArray) {
            JsonObject emissionObj = (JsonObject) object;
            System.out.println("Category: " + emissionObj.get("Category"));
            System.out.println("Gas Units: " + emissionObj.get("Gas Units"));
            System.out.println("Value: " + emissionObj.get("Value"));
            System.out.println("---------------------------------");
        }
        return "DB Populated!";
	}
	
	@Transactional 
	public User getUser(int userId) {
		return dao.getUser(userId);		
	}
	
	@Transactional
	public String addUser(User user) {
		em.persist(user);
		return "User registered: " + user.getEmail();
	}
	
	@Transactional
	public User updateUser(User user){
		return em.merge(user);	
    }
	
	@Transactional
	public String deleteUser(int userId){
		User user = dao.getUser(userId);
		em.remove(user);
		return "User " + userId +" deleted";
    }
	
	@Transactional
	public Emission getEmission(int emissionId){
		return dao.getEmission(emissionId);		
    }
	
	@Transactional
	public String addEmission(Emission emission) {
		em.persist(emission);
		return "Emission created: " + emission.getId();
	}
	
	@Transactional
    public Emission updateEmission(Emission emission){
		return (Emission) em.merge(emission);	
	}
	
	@Transactional
	public String deleteEmission(int emissionId){
		Emission emission = dao.getEmission(emissionId);
		em.remove(emission);
		return "Emission " + emissionId + " deleted";
    }
}

