package br.com.bitwaysystem.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class People implements TrXrefAttributes {

	private int age;
	private String name;
	private List<Phone> phones;
	private List<Address> address;
	
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public People() {
		super();
	}

	public People(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Xref to Class Pessoa
	public Map<String, String> getXRefAtributes() {

		Map<String, String> xRefAtribuutes = new HashMap<String, String>();

		xRefAtribuutes.put("age", "idade");
		xRefAtribuutes.put("name", "nome");
		xRefAtribuutes.put("phones", "telefones"); //Array List
		xRefAtribuutes.put("address", "enderecos"); //Array List

		return xRefAtribuutes;

	}
	
	/*public Map<Class<?>, Class<?>> getXRefClasses() {

		Map<Class<?>, Class<?>> xRefClasses = new HashMap<Class<?>, Class<?>>();

		xRefClasses.put(Phone.class, Telefone.class);
	
		return xRefClasses;

	}	*/
}
