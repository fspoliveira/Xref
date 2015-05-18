package br.com.bitwaysystem.xref;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import br.com.bitwaysystem.bean.People;
import br.com.bitwaysystem.bean.Pessoa;
import br.com.bitwaysystem.bean.Phone;
import br.com.bitwaysystem.bean.Telefone;
import br.com.bitwaysystem.bean.TrXrefItem;

public class Xref {

	private static Logger logger = LoggerFactory.getLogger(Xref.class);

	public static <T, F> List<T> listToList(Class<F> klazzFrom,
			Class<T> klazzTo, List<F> listFom) {

		// List return To (Type "Class To")
		List<T> listReturn = new ArrayList<T>();

		// List attributes from "From Class"
		List<Field> fieldListFrom = new ArrayList<Field>(
				Arrays.asList(klazzFrom.getDeclaredFields()));
		Map<String, Class<?>> fieldsNameFrom = new HashMap<String, Class<?>>();

		// List attributes from "To Class"
		List<Field> fieldListTo = new ArrayList<Field>(Arrays.asList(klazzTo
				.getDeclaredFields()));
		Map<String, Class<?>> fieldsNameTo = new HashMap<String, Class<?>>();

		// Xref betwenn attributes from "Class From"and "Class To"
		Map<String, String> xRef = new HashMap<String, String>();

		// Get field name and field type from "Class From"
		logger.info("Fiedls Class From");
		for (Field field : fieldListFrom) {
			fieldsNameFrom.put(field.getName(), field.getType());
			System.out.println(field.getName());
		}

		// Get field name and field type from "Class To"
		logger.info("Fiedls Class To");
		for (Field field : fieldListTo) {
			fieldsNameTo.put(field.getName(), field.getType());
			System.out.println(field.getName());
		}

		// Checks each attribute from "Class From" in Class To".
		for (Map.Entry<String, Class<?>> fieldNameFrom : fieldsNameFrom
				.entrySet()) {
			System.out.println(fieldNameFrom.getKey() + "/"
					+ fieldNameFrom.getValue());

			// If the name attribute are same between Class from and Class To
			if (fieldsNameTo.containsKey(fieldNameFrom.getKey())) {
				// Put atributte in Xref
				xRef.put(fieldNameFrom.getKey(), fieldNameFrom.getKey());
			} else {

				// call getXRefAtribuutes from "From Class"
				F xrefData = null;
				try {
					xrefData = (F) klazzFrom.newInstance();

					// Get attribues Xref
					Map<String, String> xrefAtributtes = ((TrXrefItem) xrefData)
							.getXRefAtribuutes();

					// Find in Xref the attribute exists in "To Class"
					if (fieldsNameTo.containsKey(xrefAtributtes
							.get(fieldNameFrom.getKey()))) {
						// Put attributte in Xref
						xRef.put(fieldNameFrom.getKey(),
								xrefAtributtes.get(fieldNameFrom.getKey()));
					}
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		// For each item from ArrayList
		for (int i = 0; i < listFom.size(); i++) {

			try {

				T klassToObject = klazzTo.newInstance();

				for (Map.Entry<String, String> entry : xRef.entrySet()) {

					// Get Method from "From Class"
					Method methodGet = klazzFrom.getMethod(toPojoGetStyle(entry
							.getKey()));

					// Set attribute value to "To Class"
					Field t = klazzTo.getDeclaredField(entry.getValue());
					t.setAccessible(true);

					// Verify if attribute is a ArrayList, case is ArrayList
					// call recursive function
					// to get new list

					if (entry.getKey().equals("phones")) {
						System.out.println("Chegou no Array");

						System.out.println(methodGet.invoke(listFom.get(i)));

						@SuppressWarnings("unchecked")
						List<Phone> teste = (List<Phone>) methodGet
								.invoke(listFom.get(i));

						List<Telefone> telefones = (ArrayList<Telefone>) Xref
								.listToList(Phone.class, Telefone.class, teste);

						t.set(klassToObject, telefones);

					} else {
						t.set(klassToObject, methodGet.invoke(listFom.get(i)));

					}

				}

				// add new Object "Class to"
				listReturn.add(klassToObject);

			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Return list with New "Class To" objects
		return listReturn;

	}

	public static String toPojoSetStyle(String property) {
		return toPojoStyle(property, "set");
	}

	public static String toPojoGetStyle(String property) {
		return toPojoStyle(property, "get");
	}

	private static String toPojoStyle(String property, String prefix) {
		String result = prefix;
		if ((!Character.isLowerCase(property.charAt(0)))
				|| (!Character.isUpperCase(property.charAt(1)))) {
			result = result + property.substring(0, 1).toUpperCase();
			result = result + property.substring(1, property.length());
		} else {
			result = result + property;
		}
		return result;
	}

}
