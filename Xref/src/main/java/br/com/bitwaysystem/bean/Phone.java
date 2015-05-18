package br.com.bitwaysystem.bean;

import java.util.HashMap;
import java.util.Map;

public class Phone  implements TrXrefItem{

	private String type;
	private int number;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Map<String, String> getXRefAtribuutes() {
		
		Map<String, String> xRefAtribuutes = new HashMap<String, String>();

		xRefAtribuutes.put("type", "tipo");
		xRefAtribuutes.put("number", "numero");
	

		return xRefAtribuutes;
	}
}
