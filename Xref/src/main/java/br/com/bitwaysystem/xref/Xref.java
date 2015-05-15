package br.com.bitwaysystem.xref;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Xref {

	public static <T, F> List<T> listTo(Class<T> klazzTo, Class<F> klazzFrom,
			List<F> listFom) throws NoSuchFieldException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			InvocationTargetException {

		List<T> listReturn = new ArrayList<T>();

		for (int i = 0; i < listFom.size(); i++) {

			try {

				
				T klassToObject = klazzTo.newInstance();

				Field fieldlist[] = klazzFrom.getDeclaredFields();

				for (int j = 0; j < fieldlist.length; j++) {
					Field fld = fieldlist[j];

					Method methodGet = klazzFrom.getMethod(toPojoGetStyle(fld
							.getName()));	
					
					Field t = klazzTo.getDeclaredField(fld
							.getName());
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
