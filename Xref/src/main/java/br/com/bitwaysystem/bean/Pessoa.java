package br.com.bitwaysystem.bean;

public class Pessoa {

	private int age;
	private String name;

	public Pessoa() {
		super();
	}

	public Pessoa(int idade, String nome) {
		super();
		this.age = idade;
		this.name = nome;
	}

	public int getIdade() {
		return age;
	}

	public void setIdade(int idade) {
		this.age = idade;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

}
