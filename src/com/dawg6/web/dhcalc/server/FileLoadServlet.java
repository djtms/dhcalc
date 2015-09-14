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
package com.dawg6.web.dhcalc.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dawg6.web.dhcalc.shared.calculator.FormData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class FileLoadServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(FileLoadServlet.class.getName());
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			
			MultipartParser parser = new MultipartParser(req, Integer.MAX_VALUE);
			
			Part part = parser.readNextPart();

			if (part == null) {
				log.severe("No First Part");
			} else {

				String client = null;
				FormData data = null;
				
				while (part != null) {
//					log.info("Found part " + part.getName());

					if (part instanceof FilePart) {
						FilePart file = ((FilePart)part);
						InputStream stream = file.getInputStream();
						ObjectMapper mapper = new ObjectMapper();
						
						try {
							data = mapper.reader(FormData.class).readValue(stream);
						} catch (Exception e) {
							log.log(Level.WARNING, "Exception parsing FormData", e);
						}
					} else if (part instanceof ParamPart) {
						client = ((ParamPart)part).getStringValue();
					} else {
						log.info("Skipping part of type "
								+ part.getClass().getName());
					}

					part = parser.readNextPart();
				}

//				log.info("Done with parts");

				String key = client + ".FormData";

				if (client == null) {
					log.severe("No client Key");
				} else if (data == null) {
					log.severe("No FormData");
					ClientBuffer.getInstance().put(key, new FormData());
				} else {
//					log.info("Created Client Buffer " + key + " = " + data);

					ClientBuffer.getInstance().put(key, data);
				}

			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception", e);
		}
	}

}
