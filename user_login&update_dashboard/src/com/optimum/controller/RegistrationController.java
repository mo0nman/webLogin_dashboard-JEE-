package com.optimum.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.optimum.dao.AccessDatabase;

@WebServlet("/RegistrationController") 
@MultipartConfig
public class RegistrationController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private AccessDatabase AD;
	private RequestDispatcher refRequestDispatcher;

	public RegistrationController() {
		AD = new AccessDatabase();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Regex pattern checkers
		String NRICpattern = "^[a-zA-Z][0-9]{7}[a-zA-Z]$";
		String DOBpattern = "^(((0[1-9]|[12][0-9]|3[01])[- /.](0[13578]|1[02])|(0[1-9]|[12][0-9]|30)[- /.](0[469]|11)|(0[1-9]|1\\d|2[0-8])[- /.]02)[- /.]\\d{4}|29[- /.]02[- /.](\\d{2}(0[48]|[2468][048]|[13579][26])|([02468][048]|[1359][26])00))$";
		String EmailPattern = "^([A-Z|a-z|0-9](\\.|_){0,1})+[A-Z|a-z|0-9]\\@([A-Z|a-z|0-9])+((\\.){0,1}[A-Z|a-z|0-9]){2}\\.[a-z]{2,3}$";
		String MobilePattern = "^[0-9]{8}$";
		String NamePattern = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
		
		//Setting initial values
		String userName = null;
		String userNRIC = null;
		String userGender = null;
		String userDOB = null;
		String userAddress = null;
		String userEmail = null;
		String userMobile = null;
		int newUserMob = 0;
		
		//Values for password
		String firstFour = null;
		String lastFour = null;
		
		//Name register(1)
		try {
			if(request.getParameter("reg_name").matches(NamePattern)) {
				userName = request.getParameter("reg_name");
			}else {
				String alert = "<font color='red'>Please enter a valid name</font>";
				request.setAttribute("NAMEnotification", alert);
			}
			
		}catch(Exception e) {
		}
		
		//NRIC register(2)
		try {
			if(request.getParameter("reg_nric").matches(NRICpattern) && AD.ifNRICExists(request.getParameter("reg_nric")) == false) {
				userNRIC = request.getParameter("reg_nric").toUpperCase();
				firstFour = userNRIC.substring(1, 5);
			}else {
				userNRIC = null;
				firstFour = null;
				String alert = "<font color='red'>NRIC is unavailable/not valid.</font>";
				request.setAttribute("NRICnotification", alert);
			}
			}catch(Exception e) {
				System.out.println("nric not in");
			}
		
		//Gender register(3)
		
		if(request.getParameter("reg_gender") != null) {
			userGender = request.getParameter("reg_gender");
		}else {
			String alert = "<font color='red'>Please select a gender.</font>";
			request.setAttribute("Gendernotification", alert);
		}
		
		
		//DOB register(4)
		try {
			if(request.getParameter("reg_dob").matches(DOBpattern)) {
				userDOB = request.getParameter("reg_dob");
			}else {
				String alert = "<font color='red'>Please enter a valid date</font>";
				request.setAttribute("DOBnotification", alert);
			}
		}catch(Exception e) {
			System.out.println("dob not in");
		}
		
		//Address register(5)
		if(request.getParameter("reg_address") != null) {
		 userAddress = request.getParameter("reg_address");
		}else {
			String alert = "<font color='red'>Please enter a valid date</font>";
			request.setAttribute("Addnotification", alert);
		}
		
		//Country register(6)
		String userCountry = request.getParameter("reg_country");
		
		//Qualification register(7)
		String userQualification = request.getParameter("reg_qualification");
		
		//Certificate upload register(8)
		InputStream cert = null;
		Part fp = request.getPart("reg_cert");
		
		if(fp != null) {
			cert = fp.getInputStream();
		}else {
			String alert = "<font color='red'>Please upload a file (<= 1MB)</font>";
			request.setAttribute("CERTnotification", alert);
		}
		
		//Department register(9)
		String userDepartment = request.getParameter("reg_department");
		
		//Email register(10)
		try {
		if(request.getParameter("reg_email").matches(EmailPattern) && AD.ifEmailExists(request.getParameter("reg_email")) == false) {
			userEmail = request.getParameter("reg_email");
		}else {
			String alert = "<font color='red'>Email is invalid/in use.</font>";
			request.setAttribute("emailNotification", alert);
		}
		}catch(Exception e) {
			
		}
		
		//Mobile register(11)
		try {
			if(request.getParameter("reg_mobile").matches(MobilePattern)) {
				userMobile = request.getParameter("reg_mobile");
				lastFour = userMobile.substring(4, 8);
				newUserMob = Integer.parseInt(userMobile);
					if(AD.ifMobileNoExists(newUserMob) == true) {
						userMobile = null;
						lastFour = null;
						newUserMob = 0;
						String alert = "<font color='red'>Mobile is invalid/in use.</font>";
						request.setAttribute("mobileNotification", alert);
					}
			}
		}catch(Exception e) {
			
		}
		
		String newPassword = firstFour + lastFour;
		
		System.out.println(userName + " "+ userNRIC+" " + userGender+" " + userDOB+" " + userAddress+" "+userCountry+" " + userQualification+ " " + userDepartment+""+ userEmail+" " + newUserMob);
		
		if(userName != null && userNRIC != null && userGender != null && userDOB != null && userAddress != null && userQualification != null && cert != null && userEmail != null && newUserMob != 0) {
			AD.createNewAccount(userName, userNRIC, userGender, userDOB, userAddress, userCountry, userQualification, cert, userDepartment, userEmail, newPassword, newUserMob);
			refRequestDispatcher = request.getRequestDispatcher("SuccessPage.jsp");
			refRequestDispatcher.forward(request, response);
		}else {
			refRequestDispatcher = request.getRequestDispatcher("CreateNewEmp.jsp");
			refRequestDispatcher.forward(request, response);
		}
	}
}