package br.com.bitwaysystem.bean;

public class Pessoa {

	private int age;
	private String nome;

	
	public Pessoa(int age, String name) {
		super();
		this.age = age;
		this.nome = name;
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
		return nome;
	}

	public void setName(String name) {
		this.nome = name;
	}

	

}
