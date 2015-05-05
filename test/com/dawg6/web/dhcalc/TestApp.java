/*******************************************************************************
 * Copyright (c) 2014, 2015 Scott Clarke (scott@dawg6.com).
 *
 * This file is part of Dawg6's Demon Hunter DPS Calculator.
 *
 * Dawg6's Demon Hunter DPS Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dawg6's Demon Hunter DPS Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.dawg6.web.dhcalc.shared.calculator.CharacterData;

public class TestApp {

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
