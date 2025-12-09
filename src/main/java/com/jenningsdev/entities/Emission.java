package com.jenningsdev.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.xml.bind.annotation.XmlRootElement;

@NamedQueries({
	@NamedQuery(name = "Emission.getAllEmissions", query = "select o from Emission o"),
	@NamedQuery(name = "Emission.getAllEmissionsByCategory", query = "select o from Emission o ORDER BY o.category DESC"),
	@NamedQuery(name = "Emission.getEmission", query = "select o from Emission o where o.id = :id")}
)

@XmlRootElement(name = "Emission")
@Entity
public class Emission {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
    private int id;
	private String category, gasUnits, scenario, description;
	private double predictedValue, actualValue; 
	private int year;
	private boolean isFinal;
	
	public Emission() {
		
	}
	
	public Emission(String category, String gasUnits, String scenario, String description, double predictedValue, double actualValue, int year) {
		this.category = category;
		this.gasUnits = gasUnits;
		this.scenario = scenario;
		this.description = description;
		this.predictedValue = predictedValue;
		this.actualValue = actualValue;
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGasUnits() {
		return gasUnits;
	}

	public void setGasUnits(String gasUnits) {
		this.gasUnits = gasUnits;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPredictedValue() {
		return predictedValue;
	}

	public void setPredictedValue(double predictedValue) {
		this.predictedValue = predictedValue;
	}

	public double getActualValue() {
		return actualValue;
	}

	public void setActualValue(double actualValue) {
		this.actualValue = actualValue;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
}
