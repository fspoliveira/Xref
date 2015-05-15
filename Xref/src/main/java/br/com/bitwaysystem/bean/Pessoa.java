package br.com.bitwaysystem.bean;

public class Pessoa {

	private int age;
	private String name;

	
	public Pessoa(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public Pessoa() {
		super();
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

	

}
