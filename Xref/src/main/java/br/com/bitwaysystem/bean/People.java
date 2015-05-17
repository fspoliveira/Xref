package br.com.bitwaysystem.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class People implements TrXrefItem {

	private int age;
	private String name;

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
	public  Map<String, String> getXRefAtribuutes() {

		Map<String, String> xRefAtribuutes = new HashMap<String, String>();

		xRefAtribuutes.put("age", "idade");
		xRefAtribuutes.put("name", "nome");

		return xRefAtribuutes;

	}

}
