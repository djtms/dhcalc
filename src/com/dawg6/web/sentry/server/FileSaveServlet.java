package com.dawg6.web.sentry.server;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class FileSaveServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(FileSaveServlet.class.getName());
	
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

				String key = null;
				String filename = null;
				
				while (part != null) {
//					log.info("Found part " + part.getName());
					
					if (part instanceof ParamPart) {
						
						if (part.getName().equals("key"))	 
							key = ((ParamPart)part).getStringValue();
						else if (part.getName().equals("filename"))
							filename = ((ParamPart)part).getStringValue();
					} else {
						log.info("Skipping part of type "
								+ part.getClass().getName());
					}

					part = parser.readNextPart();
				}

				if (key != null) {
					
					if (filename == null)
						filename = "filename";
					
//					Object data = ClientBuffer.getInstance().get(key);
//
//					if (data != null) {
						
						resp.setContentType("application/octet-stream");
						resp.setHeader("Content-Disposition",
								"attachment;filename=" + filename);
						
						ServletOutputStream out = resp.getOutputStream();
						
//						out.write((byte[])data);
						out.write(key.getBytes());
						
						out.flush();
						out.close();
//					} else {
//						log.warning("Unable to find client data: " + key);
//					}
					
				} else {
					log.warning("No Key");
				}
			}

		} catch (Exception e) {
			resp.setContentType("text/plain");
			e.printStackTrace(resp.getWriter());
		}
	}

}
