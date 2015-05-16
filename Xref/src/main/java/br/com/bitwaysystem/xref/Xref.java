package br.com.bitwaysystem.xref;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Xref {

	public static <T, F> List<T> listToList(Class<F> klazzFrom,
			Class<T> klazzTo, List<F> listFom) throws NoSuchFieldException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			InvocationTargetException {

		List<T> listReturn = new ArrayList<T>();

		List<Field> fieldListFrom = new ArrayList<Field>(Arrays.asList(klazzFrom.getDeclaredFields()));
		Map<String, Class<?>> fieldsNameFrom = new HashMap<String, Class<?>>();

		List<Field> fieldListTo = new ArrayList<Field>(Arrays.asList(klazzTo.getDeclaredFields()));
		Map<String, Class<?>> fieldsNameTo = new HashMap<String, Class<?>>();

		Map<String, String> xRef = new HashMap<String, String>();
		
		for (Field field : fieldListFrom) {
			fieldsNameFrom.put(field.getName(), field.getType());
		}
		
		for (Field field : fieldListTo) {
			fieldsNameTo.put(field.getName(), field.getType());
		}
		
		for (Map.Entry<String,  Class<?>> entry : fieldsNameFrom.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		    
		   if(fieldsNameTo.containsKey(entry.getKey())){  
			   xRef.put(entry.getKey(), entry.getKey());
		   }
		    
		}

		for (int i = 0; i < listFom.size(); i++) {		

			try {

				T klassToObject = klazzTo.newInstance();
				
				for (Map.Entry<String, String> entry : xRef.entrySet()){

					Method methodGet = klazzFrom.getMethod(toPojoGetStyle(entry.getKey()));

					Field t = klazzTo.getDeclaredField(entry.getValue());
					t.setAccessible(true);
					t.set(klassToObject, methodGet.invoke(listFom.get(i)));
				}

				listReturn.add(klassToObject);

			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
