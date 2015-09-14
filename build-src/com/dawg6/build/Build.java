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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.build;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class Build {

	private static final String propertiesFile = "build-info/build.properties";
	private static final String MINOR_VERSION = "version.minor";
	private static final String VERSION_DATE = "version.date";
	private static final String VERSION_BUILD = "version.build";
	
	private Build() {

		try {
			createVersionFile();
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	private static final SimpleDateFormat dateString = new SimpleDateFormat("MM/dd/yy");
	private static final SimpleDateFormat dateVersion = new SimpleDateFormat("yyMMdd");
	
	private void createVersionFile() throws Exception {
		
		String template = "templates/Version.java";
		String versionFile = "src/com/dawg6/web/dhcalc/shared/calculator/Version.java";
		
		Date today = new Date();
		
		Properties props = new Properties();
		props.load(new FileInputStream(propertiesFile));

		String versionDate = dateString.format(today);
		String minorVersion = dateVersion.format(today);
		
		String prevDate = props.getProperty(VERSION_DATE);
		int build = -1;
		
		if (!prevDate.equals(versionDate)) {
			build = 1;
			props.setProperty(VERSION_DATE, versionDate);
			props.setProperty(MINOR_VERSION, minorVersion);
		} else {
			build = Integer.parseInt(props.getProperty(VERSION_BUILD)) + 1;
		}

		props.setProperty(VERSION_BUILD, String.valueOf(build));
		
		props.store(new FileOutputStream(propertiesFile), null);
		
		String text = readTemplate(new File(template));

		text = replace(text, props);
		
		writeFile(text, new File(versionFile));
	}

	private String replace(String text, Properties props) {

		for (Map.Entry<Object, Object> e : props.entrySet()) {
			String key = e.getKey().toString();
			String value = e.getValue().toString();
			System.out.println("Setting property: " + key + " = " + value);
			text = text.replaceAll("%" + key + "%", value);
		}
		
		return text;
	}

	private void writeFile(String text, File file) throws Exception {
		FileOutputStream stream = new FileOutputStream(file);
		PrintWriter writer =  new PrintWriter(stream);
		
		writer.append(text);
		
		writer.flush();
		writer.close();
	}

	private String readTemplate(File file) throws Exception {
		FileInputStream stream = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line;
		StringBuffer buf = new StringBuffer();
		
		do {
			line = reader.readLine();
			
			if (line != null)
				buf.append(line + "\n");
		} while (line != null);

		reader.close();
		
		return buf.toString();
	}

	public static void main(String[] args) {
		new Build();
	}

}
