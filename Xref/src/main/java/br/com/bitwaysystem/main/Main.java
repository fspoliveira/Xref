package br.com.bitwaysystem.main;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import br.com.bitwaysystem.bean.People;
import br.com.bitwaysystem.bean.Pessoa;
import br.com.bitwaysystem.xref.Xref;

public class Main {
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		
		People p1 = new People(35,"Fernando");
		People p2 = new People(2,"Gabriel");
		
		
		List<People> peoples = new ArrayList<People>();
		peoples.add(p1);
		peoples.add(p2);
		
		List<Pessoa>  pessoas= Xref.listTo(Pessoa.class, People.class, peoples);
		
		for(int i=0; i<pessoas.size();i++){
			System.out.println(pessoas.get(i));
		}		
	}
}