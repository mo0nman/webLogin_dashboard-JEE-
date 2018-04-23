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
import com.optimum.pojo.PlainUser;

@WebServlet("/recoveryController")
public class recoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccessDatabase AD;
	private RequestDispatcher refRequestDispatcher;  
	private PlainUser refUser;
       
    public recoveryController() {
       AD = new AccessDatabase();
       refUser = new PlainUser();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String getEmail = request.getParameter("recover_user_email");
		
		// Servlet method to control recovery of password to proper email
		try {
			if(AD.ifEmailExists(getEmail) == true) {
				
				AD.setNewPassword(getEmail);
				
				String name  = AD.getName(getEmail);
				System.out.println(name);
				
				AD.setNewPassword(getEmail);
				
				String password = AD.getPass(getEmail);
				System.out.println(password);
				
				AD.sendRecoveryEmail(name, getEmail, password);
				
				refRequestDispatcher = request.getRequestDispatcher("Login_Page.jsp");
				refRequestDispatcher.forward(request, response);
			}else if(AD.ifEmailExists(getEmail) == false){
				String alert = "<font color='red'>Please enter a valid email</font>";
				request.setAttribute("lockedAlert", alert);
				refRequestDispatcher = request.getRequestDispatcher("Login_Page.jsp");
				refRequestDispatcher.include(request, response);
			}
		}catch(Exception e) {
			
		}
		
		
	}
}
