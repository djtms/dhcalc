package com.dawg6.web.sentry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.dawg6.web.sentry.shared.calculator.CharacterData;

public class MakeCharacterDataCopy {

	public static void main(String[] args) throws Exception {
		Field[] fields = CharacterData.class.getDeclaredFields();

		List<Field> list = new Vector<Field>(fields.length);
		Collections.addAll(list, fields);
		Collections.sort(list, new Comparator<Field>() {

			@Override
			public int compare(Field o1, Field o2) {
				return o1.getName().toLowerCase()
						.compareTo(o2.getName().toLowerCase());
			}
		});

		for (Field f : list) {
			if (!Modifier.isStatic(f.getModifiers())) {

				StringBuffer buf = new StringBuffer();

				buf.append("this." + f.getName() + " = ");

				Class<?> type = f.getType();

				if (type.isPrimitive() || type.equals(String.class)
						|| type.isEnum()) {
					buf.append("other." + f.getName());
				} else {
					buf.append("Util.copy(other." + f.getName() + ")");
				}

				buf.append(";");

				System.out.println(buf);
			}
		}
	}

}
