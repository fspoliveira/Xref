package br.com.bitwaysystem.xref;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bitwaysystem.bean.TrXrefItem;

public class Xref {

	public static <T, F> List<T> listToList(Class<F> klazzFrom,
			Class<T> klazzTo, List<F> listFom) {

		List<T> listReturn = new ArrayList<T>();

		List<Field> fieldListFrom = new ArrayList<Field>(
				Arrays.asList(klazzFrom.getDeclaredFields()));
		Map<String, Class<?>> fieldsNameFrom = new HashMap<String, Class<?>>();

		List<Field> fieldListTo = new ArrayList<Field>(Arrays.asList(klazzTo
				.getDeclaredFields()));
		Map<String, Class<?>> fieldsNameTo = new HashMap<String, Class<?>>();

		// Xref betwenn Class from and Class To
		Map<String, String> xRef = new HashMap<String, String>();

		// Get field name and field type from "Class From"
		for (Field field : fieldListFrom) {
			fieldsNameFrom.put(field.getName(), field.getType());
		}

		// Get field name and field type from "Class To"
		for (Field field : fieldListTo) {
			fieldsNameTo.put(field.getName(), field.getType());
		}

		// Checks each attribute the origin class to exist on the target class
		for (Map.Entry<String, Class<?>> entry : fieldsNameFrom.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());

			// If the name attribute are same
			if (fieldsNameTo.containsKey(entry.getKey())) {
				xRef.put(entry.getKey(), entry.getKey());
			} else {

				F xrefData = null;
				try {
					xrefData = (F) klazzFrom.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Map<String, String> xrefAtributtes = ((TrXrefItem) xrefData)
						.getXRefAtribuutes();

				// Find in xe Xref the atibute exists in target class
				if (fieldsNameTo
						.containsKey(xrefAtributtes.get(entry.getKey()))) {
					xRef.put(entry.getKey(), xrefAtributtes.get(entry.getKey()));
				}
			}

		}

		// For each item from ArrayList
		for (int i = 0; i < listFom.size(); i++) {

			try {

				T klassToObject = klazzTo.newInstance();

				for (Map.Entry<String, String> entry : xRef.entrySet()) {

					// Get attribute from Original Class
					Method methodGet = klazzFrom.getMethod(toPojoGetStyle(entry
							.getKey()));

					// Set attribute value to Targe Class
					Field t = klazzTo.getDeclaredField(entry.getValue());
					t.setAccessible(true);
					try {
						t.set(klassToObject, methodGet.invoke(listFom.get(i)));
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// Return list with New
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
			}
		}

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
