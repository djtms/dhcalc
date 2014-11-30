package com.dawg6.web.sentry.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FetchServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(FetchServlet.class.getName());
	
	private static final long serialVersionUID = 1L;

	private static final String BASE_URL = "http://us.battle.net/d3/en/tooltip/item";

//	private static final int MAX_BUFFER = 102400;

	private static final String USER_AGENT = "Mozilla/5.0";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String path = req.getPathInfo();
			
			if (req.getQueryString() != null)
				path += ("?" + req.getQueryString());
			
			log.info("Path = " + path);
			
			URL obj = new URL(BASE_URL + path);

	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
	        // optional default is GET
	        con.setRequestMethod("GET");
	 
	        //add request header
	        con.setRequestProperty("User-Agent", USER_AGENT);
	 
//	        int responseCode = con.getResponseCode();
//	        int len = con.getContentLength();
	        
//	        log.info("Expected length:" + len);
	        
	        resp.setContentType(con.getContentType());
	        resp.setCharacterEncoding(con.getContentEncoding());
	        
//	        System.out.println("\nSending 'GET' request to URL : " + obj.toString());
//	        System.out.println("Response Code : " + responseCode);

//	        byte[] buf = new byte[MAX_BUFFER];
	        
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream()));
	        String inputLine;
	 
	        ServletOutputStream sout = resp.getOutputStream();
//	        int l = 0;
	        
	        while ((inputLine = in.readLine()) != null) {
	        	byte[] bytes = inputLine.getBytes();
	            sout.write(bytes);
//	            l += bytes.length;
	        }
	 
	        sout.flush();

		} catch (Exception e) {
			resp.setContentType("text/plain");
			e.printStackTrace(resp.getWriter());
		}
	}
	
}
