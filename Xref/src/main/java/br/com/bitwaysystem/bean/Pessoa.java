package br.com.bitwaysystem.bean;

import java.util.List;

public class Pessoa {

	private int idade;
	private String nome;
	private List<Telefone> telefones;

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

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
