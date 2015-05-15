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

				@SuppressWarnings("unused")
				T klassTo = klazzTo.newInstance();

				Field fieldlist[] = klazzFrom.getDeclaredFields();

				for (int j = 0; j < fieldlist.length; j++) {
					Field fld = fieldlist[j];

					System.out.println("atribute name  = " + fld.getName());					

					Method method = klazzFrom.getMethod(toPojoGetStyle(fld
							.getName()));

					Object o = method.invoke(listFom.get(i));

					System.out.println(o.toString());

				}

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
