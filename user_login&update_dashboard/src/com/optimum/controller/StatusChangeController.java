package com.optimum.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.optimum.dao.AccessDatabase;


@WebServlet("/StatusChangeController")
public class StatusChangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccessDatabase AD;
	private RequestDispatcher refRequestDispatcher;

	public StatusChangeController() {
		AD = new AccessDatabase();
	}

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String status = request.getParameter("status_change");
    	String email = request.getParameter("get_email");
    	
    	// Method to send lock or unlock status to .jsp page
    	try {
    	if(status.equals("unlock")) {
    		AD.unlockAccount(email);
    		response.sendRedirect("RequestStatus.jsp");
    		}
    	}catch(Exception e) {
    		
    	}
    	
    	try {
    		if(status.equals("lock")) {
    			AD.lockAccount(email);
    			response.sendRedirect("RequestStatus.jsp");
    		}
    		
    	}catch(Exception e) {
    		
    	}
	}
}
