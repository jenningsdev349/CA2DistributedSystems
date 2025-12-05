package com.jenningsdev;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadData {
	
	public void readXml() throws ParserConfigurationException, SAXException, IOException {
		File xmlFile = new File("GreenhouseGasEmissionsPredicted2025.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);   
        
        NodeList emissions = doc.getElementsByTagName("Row");
        
        for (int i = 0; i < emissions.getLength(); i++) {

            Node emission = emissions.item(i);
            
            if (emission.getNodeType() == Node.ELEMENT_NODE) {
            	
                Element elem = (Element) emission;
                
                Node node5 = elem.getElementsByTagName("Value").item(0);
                double value;
                
                try {
                    value = Double.parseDouble(node5.getTextContent());
                }
                catch (NumberFormatException e) {
                	value = 0;
                }

                Node node1 = elem.getElementsByTagName("Category__1_3").item(0);
                String category = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("Year").item(0);
                int year = Integer.parseInt(node2.getTextContent());

                Node node3 = elem.getElementsByTagName("Scenario").item(0);
                String scenario = node3.getTextContent();      
                
                Node node4 = elem.getElementsByTagName("Gas___Units").item(0);
                String gasUnits = node4.getTextContent();   
                       
                if(value > 0 && scenario.equals("WEM") && year == 2023) {
                	System.out.println("Category: " + category);
                    System.out.println("Year: " + year);
                    System.out.println("Scenario: " + scenario);
                    System.out.println("gasUnits: " + gasUnits);
                    System.out.println("value: " + value);
                }
            }
        }
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		ReadData readData = new ReadData();
		readData.readXml();
	}
}
