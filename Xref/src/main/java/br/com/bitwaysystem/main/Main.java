package br.com.bitwaysystem.main;

import java.util.ArrayList;
import java.util.List;

import br.com.bitwaysystem.bean.Address;
import br.com.bitwaysystem.bean.Endereco;
import br.com.bitwaysystem.bean.People;
import br.com.bitwaysystem.bean.Pessoa;
import br.com.bitwaysystem.bean.Phone;
import br.com.bitwaysystem.bean.Telefone;
import br.com.bitwaysystem.xref.Xref;

public class Main {

	public static void main(String[] args) {

		List<People> peoples = new ArrayList<People>();
		List<Phone> phones = new ArrayList<Phone>();
		List<Address> address = new ArrayList<Address>();

		// *****************************************************************
		// People 1 Fernando
		// *****************************************************************
		People p1 = new People(35, "Fernando");

		Phone phoneCom = new Phone();
		phoneCom.setNumber(43247959);
		phoneCom.setType("COM");

		Phone phoneCel = new Phone();
		phoneCel.setNumber(972110135);
		phoneCel.setType("CEL");

		phones.add(phoneCom);
		phones.add(phoneCel);

		p1.setPhones(phones);

		Address a1 = new Address();
		a1.setNumber(111);
		a1.setStreet("Dom Antonio Barreiros");

		Address a2 = new Address();
		a2.setNumber(293);
		a2.setStreet("Norte Sul");

		address.add(a1);
		address.add(a2);

		p1.setAddress(address);

		peoples.add(p1);

		// *****************************************************************
		// People 2 Gabriel
		// *****************************************************************
		People p2 = new People(2, "Gabriel");

		phoneCom = new Phone();
		phoneCom.setNumber(707070);
		phoneCom.setType("FAKE");

		phones = new ArrayList<Phone>();

		phones.add(phoneCom);

		p2.setPhones(phones);

		peoples.add(p2);

		List<Pessoa> pessoas = (ArrayList<Pessoa>) Xref.listToList(
				People.class, Pessoa.class, peoples);

		for (Pessoa pe : pessoas) {

			System.out.println(pe.getAge());
			System.out.println(pe.getName());

			if (pe.getTelefones() != null) {
				for (Telefone fones : pe.getTelefones()) {
					System.out.println(fones.getNumero());
				}
			}

			if (pe.getEnderecos() != null) {

				for (Endereco end : pe.getEnderecos()) {
					System.out.println(end.getNumero());
					System.out.println(end.getRua());
				}
			}

		}
	}
}