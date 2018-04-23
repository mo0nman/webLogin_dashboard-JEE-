package com.optimum.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.optimum.dao.AccessDatabase;


@WebServlet("/UpdateParticularsController")
@MultipartConfig(maxFileSize = 1048576)
public class UpdateParticularsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccessDatabase AD;
	private RequestDispatcher refRequestDispatcher;
	
	public UpdateParticularsController() {
		AD = new AccessDatabase();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				HttpSession userSession  = request.getSession();
				
				//Regex pattern checkers
				String MobilePattern = "^[0-9]{8}$";
				
				//Setting initial values
				String userName = (String) userSession.getAttribute("emailHere");
				String userAddress = null;
				String userMobile = null;
				InputStream cert = null;
				InputStream pic = null;
				int newUserMob = 0;
				String checker = request.getParameter("update");
				
				//Additional profile picture upload
				if(checker.equals("Update profile pic!")) {
					Part fp1 = request.getPart("reg_pic");
					pic = fp1.getInputStream();
					try {
						AD.updateProfilePic(pic, userName);
						response.sendRedirect("UpdateUserProfile.jsp");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else {
					String alert = "<font color='red'>Please upload a file</font>";
					request.setAttribute("CERTnotification", alert);
				}
				
				//Address register
				if(checker.equals("Update address!")) {
				 userAddress = request.getParameter("reg_address");
				 try {
					AD.updateAddress(userAddress, userName);
					response.sendRedirect("UpdateUserProfile.jsp");
				} catch (SQLException e) {
					e.printStackTrace();
					}
				}
				
				//Qualification update
				if(checker.equals("Update Qualification!")) {
				try {
					AD.updateQualification(request.getParameter("reg_qualification"), userName);
					response.sendRedirect("UpdateUserProfile.jsp");
				} catch (SQLException e1) {
					e1.printStackTrace();
					}
				}
				
				
				//Additional certificate upload
				if(checker.equals("Update Certificate!")) {
					Part fp2 = request.getPart("reg_cert");
					System.out.println(fp2);
					cert = fp2.getInputStream();
					try {
						AD.updateCertificate(cert, userName);
						response.sendRedirect("UpdateUserProfile.jsp");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else {
					String alert = "<font color='red'>Please upload a file</font>";
					request.setAttribute("CERTnotification", alert);
				}
				
				//Mobile update
				if(checker.equals("Update mobile!")) {
				try {
					if(request.getParameter("reg_mobile").matches(MobilePattern)) {
						userMobile = request.getParameter("reg_mobile");
						newUserMob = Integer.parseInt(userMobile);
						AD.updateMobile(newUserMob, userMobile);
						response.sendRedirect("UpdateUserProfile.jsp");
							if(AD.ifMobileNoExists(newUserMob) == true) {
								userMobile = null;
								newUserMob = 0;
								String alert = "<font color='red'>Mobile is invalid/in use.</font>";
								request.setAttribute("mobileNotification", alert);
							}
					}
				}catch(Exception e) {
					
					}
			}
		}
	}

