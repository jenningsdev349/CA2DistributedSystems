package com.jenningsdev.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.xml.bind.annotation.XmlRootElement;

@NamedQueries({ 
	@NamedQuery(name = "Emission.getEmission", query = "select o from Emission o where o.id = :id")})

@XmlRootElement(name = "Emission")
@Entity
public class Emission {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String category, gasUnits, scenario;
	private Double value; 
	private int year;
	
	public Emission() {
		
	}
	
	public Emission(String category, String gasUnits, String scenario, Double value, int year) {
		this.category = category;
		this.gasUnits = gasUnits;
		this.scenario = scenario;
		this.value = value;
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
