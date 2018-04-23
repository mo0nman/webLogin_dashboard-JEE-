package com.optimum.dao;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import com.optimum.pojo.User;

public interface Blueprint_AccessDatabase {
	//Check methods
	boolean checkAdminCred(User refUser) throws SQLException;
	boolean checkUserCred(User refUser) throws SQLException;
	
	//Implementation methods
	boolean accountLockNotif(User refUser) throws SQLException;
	void lockAccount(String id) throws SQLException;
	boolean checkPass(String pass,String email) throws SQLException;
	void setNewPassword(String email) throws SQLException;
	
	//Admin menu methods
	 ArrayList returnNames() throws SQLException;
	 void unlockAccount(String email) throws SQLException;
	 
	 //Registration methods
	 boolean ifNRICExists(String NRIC) throws SQLException;
	 boolean ifEmailExists(String email) throws SQLException;
	 boolean ifMobileNoExists(int num) throws SQLException;
	 void createNewAccount(String name, String nric, String gender, String dob, String address,String country, String qualification,InputStream pic,String department, String email,String tempPass, int mobile) throws SQLException;
}
