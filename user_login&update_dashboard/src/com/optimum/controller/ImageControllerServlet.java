package com.optimum.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.optimum.dao.AccessDatabase;


@WebServlet("/ImageControllerServlet")
@MultipartConfig
public class ImageControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccessDatabase AD;
       

    public ImageControllerServlet() {
    	AD = new AccessDatabase();
    }
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String emailAddress = (String) session.getAttribute("emailHere");
		
		OutputStream img;
		
		try {
			byte[] picStream = AD.getPic(emailAddress);
			response.setContentType("image/jpg");
			img = response.getOutputStream();
			img.write(picStream);
			img.flush();
			img.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
