package com.optimum.dao;


import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.optimum.connection.DatabaseUtility;
import com.optimum.pojo.User;

public class AccessDatabase implements Blueprint_AccessDatabase{
	private Connection con;
	static Logger log4j;
	
	public AccessDatabase() {
		con = DatabaseUtility.getConnection();
		log4j = Logger.getLogger(AccessDatabase.class);
	}
	
	//---------------------------------------------LOGIN CHECK METHODS---------------------------------------------\\
	
	//Method to check Admin's credentials
	public boolean checkAdminCred(User refUser) throws SQLException{
		
		
		PreparedStatement p = con.prepareStatement("SELECT * from JEEuserdata WHERE emailadd=? and password=?");  
	       
		 p.setString(1, refUser.getLogin());  
	     p.setString(2, refUser.getPassword());  
	     
	     ResultSet r= p.executeQuery();  
	       
	        if(r.next()){  
	            String str1, str2;  
	            str1=r.getString("emailadd");  
	            str2=r.getString("password"); 
	            
	            if(str1.equals("admin@theoptimum.net") && str2.equals("admin123")){  
	            	return true;
	            }
	        }
	        return false;
	}//end of checkAdminCred
	
	//Method to check User's credentials
		public boolean checkUserCred(User refUser) throws SQLException{
			
			String loginPass = getMD5(refUser.getPassword());
			
			PreparedStatement p = con.prepareStatement("SELECT * from JEEuserdata WHERE emailadd=? and password=?");  
		       
			 p.setString(1, refUser.getLogin());  
		     p.setString(2, loginPass);  
		     
		     ResultSet r= p.executeQuery();  
		       
		        if(r.next()){  
		            String str1, str2;  
		            str1=r.getString("emailadd");  
		            str2=r.getString("password"); 
		            
		            if(str1.equals(refUser.getLogin()) && str2.equals(loginPass)){  
		            	return true;
		            }
		        }
		        log4j.warn("Wrong employee login.");
		        return false;
		}//end of checkUserCred
		
		//					---End of LoginCheck methods---			  \\
		
		//---------------------------------------------------------------------------------------------------\\
		//----------------------------------------------------------------------------------------------------\\
		
		
		//					---Start of User based methods---			\\
		
		
		public boolean accountLockNotif(User refUser) throws SQLException {
			
				PreparedStatement stmnt = con.prepareStatement("SELECT status FROM JEEuserdata WHERE EmailAdd=?");
				
				stmnt.setString(1, refUser.getLogin());
				
				ResultSet r = stmnt.executeQuery();
				
				while(r.next()) {
					
						String status = r.getString("status");
						if(status.equals("Locked")) {
							return true;
						}
						//log4j.warn("Locked notification sent.");
					}
				
				return false;
			}
		
		public boolean checkPass(String pass,String email) throws SQLException{
			
			PreparedStatement stmnt = con.prepareStatement("SELECT Password FROM JEEuserdata WHERE EmailAdd=?");
			stmnt.setString(1, email);
			
			ResultSet r = stmnt.executeQuery();
			
			if(r.next()) {
				String value;
				value = r.getString("Password");
				
				if(value.equals(pass)) {
					return true;
				}
			}
			return false;
		}
		
		public String getName(String email) throws SQLException{
			
			String username = "";
			
			PreparedStatement stmnt = con.prepareStatement("SELECT name FROM JEEuserdata WHERE emailadd=?");
			stmnt.setString(1, email);
			
			ResultSet rs = stmnt.executeQuery();
			
			while(rs.next()) {
				username = rs.getString("name");
			}
			
			return username;
		}
		
		public String getMobile(String email) throws SQLException{
			
			String usermobile = "";
			
			PreparedStatement stmnt = con.prepareStatement("SELECT mobile FROM JEEuserdata WHERE emailadd=?");
			stmnt.setString(1, email);
			
			ResultSet rs = stmnt.executeQuery();
			
			while(rs.next()) {
				usermobile = rs.getString("mobile");
			}
			
			return usermobile;
		}
		
		public String getAddress(String email) throws SQLException{
			
			String userAddress = "";
			
			PreparedStatement stmnt = con.prepareStatement("SELECT address FROM JEEuserdata WHERE emailadd=?");
			stmnt.setString(1, email);
			
			ResultSet rs = stmnt.executeQuery();
			
			while(rs.next()) {
				userAddress = rs.getString("address");
			}
			
			return userAddress;
		}
		
		public String getQualification(String email) throws SQLException{
			
			String q = "";
			
			PreparedStatement stmnt = con.prepareStatement("SELECT qualification from JEEuserdata WHERE emailadd=?");
			stmnt.setString(1, email);
			
			ResultSet rs = stmnt.executeQuery();
			
			while(rs.next()) {
				q = rs.getString("qualification");
			}
			
			return q;
		}
		
		public byte[] getPic(String email) throws SQLException{
			
			byte[] imgData = null;
			
			PreparedStatement stmnt = con.prepareStatement("SELECT profilepic from JEEuserdata WHERE emailadd=?");
			stmnt.setString(1, email);
			
			ResultSet rs = stmnt.executeQuery();
			
			while(rs.next()) {
				imgData = rs.getBytes("profilepic");
			}
			
			return imgData;
		}
		
		public String getPass(String email) throws SQLException{
			
			String password = "";
			String FF = "";
			String LF = "";
			
			PreparedStatement stmnt = con.prepareStatement("SELECT nric,mobile FROM JEEuserdata WHERE emailadd=?");
			stmnt.setString(1, email);
			
			ResultSet rs = stmnt.executeQuery();
			
			while(rs.next()) {
				FF = rs.getString("nric").substring(1, 5);
				LF = rs.getString("mobile").substring(4, 8);
				password = FF + LF;
			}
			
			return password;
		}
		
		//--------------------------------------- SET NEW PASSWORD ----------------------------------------------\\
		
		public void setNewPassword(String email) throws SQLException{
			
			String forTransform = getPass(email);
			String newPassword = getMD5(forTransform);
			
			PreparedStatement stmnt = con.prepareStatement("UPDATE JEEuserdata SET password=? WHERE emailadd=?");
			stmnt.setString(1, newPassword);
			stmnt.setString(2, email);
			
			stmnt.executeUpdate();
			
			log4j.warn("New password created.");
		}
		
		//--------------------------------------------------------------------------------------------------------------\\
		

		
						//						---End of User based methods---					\\
		
		

	//                          -----Start of Admin methods----- 				//
		
		//These are the methods inside the admin menu
	
	public ArrayList<String> returnNames() throws SQLException {		//Returns all entries for admin
		
		ArrayList<String> names = new ArrayList<>();
		
		Statement states = con.createStatement();
		ResultSet rs = states.executeQuery("SELECT name FROM JEEuserdata");
		
		while(rs.next()) {
			names.add(rs.getString("name"));
		}
		
		return names;
	}
	
	public ArrayList<String> returnNRIC() throws SQLException{
		
		ArrayList<String> nric = new ArrayList<>();
		
		Statement stmnt = con.createStatement();
		ResultSet rs = stmnt.executeQuery("SELECT nric FROM JEEuserdata");
		
		while(rs.next()) {
			nric.add(rs.getString("nric"));
		}
		return nric;
	}
	
	public ArrayList<String> returnEmail() throws SQLException{
		
		ArrayList<String> email = new ArrayList<>();
		
		Statement stmnt = con.createStatement();
		ResultSet rs = stmnt.executeQuery("SELECT emailadd FROM JEEuserdata");
		
		while(rs.next()) {
			email.add(rs.getString("emailadd"));
		}
		
		return email;
	}
	
	public ArrayList<String> returnDepartment() throws SQLException{
		
		ArrayList<String> department = new ArrayList<>();
		
		Statement stmnt = con.createStatement();
		ResultSet rs = stmnt.executeQuery("SELECT department FROM JEEuserdata");
		
		while(rs.next()) {
			department.add(rs.getString("department"));
		}
		
		return department;
	}
	
	public ArrayList<String> returnStatus() throws SQLException{
		
		ArrayList<String> status = new ArrayList<>();
		
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("SELECT status FROM JEEuserdata");
		
		while(rs.next()) {
			status.add(rs.getString("status"));
		}
		
		return status;
	}
	
	public void lockAccount(String email) throws SQLException{
		PreparedStatement stmnt = con.prepareStatement("UPDATE JEEuserdata SET status='Locked' WHERE emailadd=?");
		
		stmnt.setString(1, email);
		
		stmnt.executeUpdate();
		log4j.warn("User account was locked.");
	}
	
	
	public void unlockAccount(String email) throws SQLException{		//Method to unlock user account by resetting counters
		PreparedStatement stmnt = con.prepareStatement("UPDATE JEEuserdata SET status='Unlocked' WHERE emailadd=?");
		
		stmnt.setString(1, email);
		
		stmnt.executeUpdate();
		log4j.warn("User account was unlocked.");
	}
	
	//						-----End of Admin methods-----					//
	
	
	//					-----Start of Registration, Updates and Checker methods-----   			//
	
	public boolean ifNRICExists(String NRIC) throws SQLException{		//Check if NRIC exists in database
		Statement stmnt = con.createStatement();
		ResultSet rs = stmnt.executeQuery("SELECT * FROM JEEuserdata where NRIC=\'" + NRIC + "\'");
		
		while(rs.next()) {
			if(rs.getString("NRIC") != null) {
				return true;
			}
		}
		return false;
	}
	
	public boolean ifEmailExists(String email) throws SQLException{		//Check if email exists in database
		Statement stmnt = con.createStatement();
		ResultSet rs = stmnt.executeQuery("SELECT * FROM JEEuserdata where EmailAdd=\'" + email + "\'");
		
		while(rs.next()) {
			if(rs.getString("EmailAdd") != null) {
				return true;
			}
		}
		return false;
	}
	
	public boolean ifMobileNoExists(int num) throws SQLException{		//Check if mobileNo exists in database
		Statement stmnt = con.createStatement();
		ResultSet rs = stmnt.executeQuery("SELECT * FROM JEEuserdata where Mobile=\'" + num + "\'");
		
		while(rs.next()) {
			if(rs.getString("Mobile") != null) {
				return true;
			}
		}
		return false;
	}
	
	//Hashing password
	
	public String getMD5(String userInput) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(userInput.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	//New account creation
	public void createNewAccount(String name, String nric, String gender, String dob, String address,String country, String qualification,InputStream pic,String department, String email,String tempPass, int mobile) {
	
		String year = dob.substring(6);
		String month = dob.substring(3, 5);
		String day = dob.substring(0, 2);
		
		String sqlDate = year + "-" + month + "-" + day;
		
		sendTempPassword(name,tempPass,email);
		
		String hashPassword = getMD5(tempPass);
		
		try {
			
		String query = "INSERT INTO JEEuserdata(name, nric, gender, dob, address, country, qualification, certificate, department, emailadd, password, mobile, status)values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmnt = con.prepareStatement(query);
		
		stmnt.setString(1, name);
		stmnt.setString(2, nric);
		stmnt.setString(3, gender);
		stmnt.setDate(4, Date.valueOf(sqlDate));
		stmnt.setString(5, address);
		stmnt.setString(6, country);
		stmnt.setString(7, qualification);
		stmnt.setBlob(8, pic);
		stmnt.setString(9, department);
		stmnt.setString(10, email);
		stmnt.setString(11, hashPassword);
		stmnt.setInt(12, mobile);
		stmnt.setString(13, "Unlocked");
		
		stmnt.executeUpdate();
		
		log4j.warn("New user account was created.");
		
	}catch(Exception e) {
		System.out.println(e);
		System.out.println("Exception found..");
		log4j.warn("Error with account creation..");
		}
	}
	
	public void updateAddress(String userAdd, String userName) throws SQLException {
		
		PreparedStatement stmnt = con.prepareStatement("UPDATE JEEuserdata SET address=? WHERE emailadd=?");
		
		stmnt.setString(1, userAdd);
		stmnt.setString(2, userName);
		
		stmnt.executeUpdate();
	}
	
	public void updateQualification(String userQual, String userName) throws SQLException {
		
		PreparedStatement stmnt = con.prepareStatement("UPDATE JEEuserdata SET qualification=? WHERE emailadd=?");
		
		stmnt.setString(1, userQual);
		stmnt.setString(2, userName);
		
		stmnt.executeUpdate();
	}
	
	public void updateCertificate(InputStream pic, String userName) throws SQLException{
		
		PreparedStatement stmnt = con.prepareStatement("UPDATE JEEuserdata SET certificate=? WHERE emailadd=?");
			
			stmnt.setBlob(1, pic);
			stmnt.setString(2, userName);
			
			stmnt.executeUpdate();
	}
	
	public void updateMobile(int userMobile, String userName) throws SQLException{
		
		PreparedStatement stmnt = con.prepareStatement("UPDATE JEEuserdata SET mobile=? WHERE emailadd=?");
		
		stmnt.setInt(1, userMobile);
		stmnt.setString(2, userName);
		
		stmnt.executeUpdate();
	}
	
	public void updateProfilePic(InputStream pic, String userName) throws SQLException{
		
		PreparedStatement stmnt = con.prepareStatement("UPDATE JEEuserdata SET profilepic=? WHERE emailadd=?");
		
		stmnt.setBlob(1, pic);
		stmnt.setString(2, userName);
		
		stmnt.executeUpdate();
	}
	
	// 								------End of Registration, Update and Checker methods-----							//
	
	//								------Send temp password to email method------							//
	
	public void sendTempPassword(String name, String password, String email) {
		
		String to = email;
		String from = "optimum.batch5@gmail.com";
		String emailPass = "Optimum2018";
		
		//Getting session object
			Properties props = System.getProperties();
				props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
				props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
				props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
				props.put("mail.smtp.port", "465"); // SMTP Port
				
				Authenticator auth = new Authenticator(){
					protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from,emailPass);
			}
		};
		Session session = Session.getDefaultInstance(props, auth); 
			//compose the message
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			msg.setSubject("Temp password");
			msg.setText("Hello " + name + "! The following is your temporary password: " + password);
			
			//Send message
			Transport.send(msg);
			System.out.println("Temporary password has been sent to " + email + ".");
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void sendRecoveryEmail(String name, String email, String password) {
		
		String to = email;
		String from = "optimum.batch5@gmail.com";
		String emailPass = "Optimum2018";
		
		//Getting session object
			Properties props = System.getProperties();
				props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
				props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
				props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
				props.put("mail.smtp.port", "465"); // SMTP Port
				
				Authenticator auth = new Authenticator(){
					protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from,emailPass);
			}
		};
		Session session = Session.getDefaultInstance(props, auth); 
			//compose the message
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			msg.setSubject("Password Recovery");
			msg.setText("Hello " + name + "! You seem to have forgotten your password. Your password is: " + password);
			
			//Send message
			Transport.send(msg);
			System.out.println("Temporary password has been sent to " + email + ".");
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
