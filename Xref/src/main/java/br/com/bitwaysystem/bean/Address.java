package br.com.bitwaysystem.bean;

import java.util.HashMap;
import java.util.Map;

public class Address implements TrXrefAttributes, TrXrefClass {

	private String street;
	private int number;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Map<Class<?>, Class<?>> getXRefClasses() {

		Map<Class<?>, Class<?>> xRefClasses = new HashMap<Class<?>, Class<?>>();

		xRefClasses.put(Address.class, Endereco.class);

		return xRefClasses;

	}

	public Map<String, String> getXRefAtributes() {

		Map<String, String> xRefAtribuutes = new HashMap<String, String>();

		xRefAtribuutes.put("street", "rua");
		xRefAtribuutes.put("number", "numero");

		return xRefAtribuutes;
	}

}
