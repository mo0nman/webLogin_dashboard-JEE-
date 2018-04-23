package com.optimum.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.optimum.dao.AccessDatabase;
import com.optimum.pojo.User;

@WebServlet("/LoginServletController")
public class LoginServletController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private AccessDatabase AD;
	private User refUser;
	private RequestDispatcher refRequestDispatcher;   
	
    public LoginServletController() {
    	
    	AD = new AccessDatabase();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String login = request.getParameter("log_id");
		String pass = request.getParameter("pass_id");
		String alert = "";
		
		refUser = new User(login,pass);
		
		try {
		if(AD.checkAdminCred(refUser) == true) {
			HttpSession adminSession  = request.getSession();
			long loginTime = adminSession.getCreationTime();
			Date dateOfStamp = new Date(loginTime);
			adminSession.setAttribute("lastTimestamp", dateOfStamp);
			String insertTime = df.format(dateOfStamp);
			adminSession.setAttribute("timestamp", insertTime);
			refRequestDispatcher = request.getRequestDispatcher("Admin_Home.jsp");
			refRequestDispatcher.forward(request, response);
			}
		else if(AD.checkUserCred(refUser) ==  true) {
			if(AD.accountLockNotif(refUser) == true) {
				alert = " <font color = 'red'>ACCOUNT IS LOCKED. PLEASE VISIT AN ADMIN</font>";
				request.setAttribute("lockedAlert", alert);
				refRequestDispatcher = request.getRequestDispatcher("Login_Page.jsp");
				refRequestDispatcher.forward(request, response);
			}else {
				//Get values from current session
				HttpSession session = request.getSession();
				String name = AD.getName(login);	
				String mobile = AD.getMobile(login);
				String address = AD.getAddress(login);
				String quali = AD.getQualification(login);
				
				//Set values for later
				session.setAttribute("userQualification", quali);
				session.setAttribute("userAddress", address);
				session.setAttribute("userMobile", mobile);
				session.setAttribute("userName",name);
				session.setAttribute("emailHere", login);
				
				//Set values for last login time and date
				long timestamp = session.getCreationTime();
				Date loginTime = new Date(timestamp);
				session.setAttribute("lastTimestamp", loginTime);
				String insertTime = df.format(loginTime);
				session.setAttribute("timestamp", insertTime);
				refRequestDispatcher = request.getRequestDispatcher("User_Home.jsp");
				refRequestDispatcher.forward(request, response);
			}
		}else {
			alert = " <font color = 'red'>ACCOUNT DOES NOT EXIST. PLEASE TRY AGAIN.</font>";
			request.setAttribute("lockedAlert", alert);
			refRequestDispatcher = request.getRequestDispatcher("Login_Page.jsp");
			refRequestDispatcher.forward(request, response);
		}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
