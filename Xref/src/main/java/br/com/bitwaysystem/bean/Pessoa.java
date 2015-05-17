package br.com.bitwaysystem.bean;

public class Pessoa {

	private int idade;
	private String nome;

	public Pessoa() {
		super();
	}

	public Pessoa(int age, String name) {
		super();
		this.idade = age;
		this.nome = name;
	}

	public int getAge() {
		return idade;
	}

	public void setAge(int age) {
		this.idade = age;
	}

	public String getName() {
		return nome;
	}

	public void setName(String name) {
		this.nome = name;
	}

}
