package com.dawg6.web.dhcalc.server;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dawg6.web.dhcalc.shared.calculator.Version;

public class VersionServlet extends HttpServlet {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(VersionServlet.class.getName());
	
	private static final long serialVersionUID = 1L;

	private static final DHCalcServiceImpl service = new DHCalcServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {

			Version version = service.getVersion();

			resp.setContentType("text/plain");
			resp.getWriter().println(version.getPrefixString());
			resp.getWriter().flush();
			
		} catch (Exception e) {
			resp.setContentType("text/plain");
			e.printStackTrace(resp.getWriter());
		}
	}

}
