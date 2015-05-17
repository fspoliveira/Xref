package br.com.bitwaysystem.main;

import java.util.ArrayList;
import java.util.List;
import br.com.bitwaysystem.bean.People;
import br.com.bitwaysystem.bean.Pessoa;
import br.com.bitwaysystem.bean.Phone;
import br.com.bitwaysystem.xref.Xref;

public class Main {

	public static void main(String[] args) {

		People p1 = new People(35, "Fernando");
		People p2 = new People(2, "Gabriel");

		Phone phoneCom = new Phone();
		phoneCom.setNumber(123456);
		phoneCom.setType("COM");

		Phone phoneCel = new Phone();
		phoneCel.setNumber(7890);
		phoneCel.setType("CEL");

		List<Phone> phones = new ArrayList<Phone>();
		phones.add(phoneCom);
		phones.add(phoneCel);

		List<People> peoples = new ArrayList<People>();
		peoples.add(p1);
		peoples.add(p2);

		List<Pessoa> pessoas = (ArrayList<Pessoa>) Xref.listToList(
				People.class, Pessoa.class, peoples);

		for (Pessoa pe : pessoas) {

			System.out.println(pe.getAge());
			System.out.println(pe.getName());

		}
	}
}