package com.systex.model;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import java.io.*;
import java.util.*;
public class AccountDAO {
	//Insert SQL
	private static final String INSERT_USERS_STMT =
	"INSERT INTO users(user_name,user_pass) VALUES(?,?)";
	private static final String INSERT_USER_ROLES_STMT =
	"INSERT INTO user_roles(user_name,role_name) VALUES(?,?)";
	//Update SQL
	private static final String UPDATE_USERS_STMT =
	"UPDATE users SET user_pass=? where user_name = ?";
	private static final String UPDATE_USER_ROLES_STMT =
	"UPDATE user_roles SET role_name=? where user_name = ?";
	//Delete SQL
	private static final String DELETE_USERS_STMT =
	"DELETE FROM users where user_name=?";
	private static final String DELETE_USER_ROLES_STMT =
	"DELETE FROM user_roles where user_name=?";
	//Retrieve SQL
	private static final String RETRIEVE_ALL_USERS_STMT =
	"SELECT * FROM users";
	
	private static final String RETRIEVE_USERS_STMT =
	"SELECT * FROM users  WHERE user_name=?";
	private static final String RETRIEVE_USER_ROLES_STMT =
	"SELECT * FROM user_roles  WHERE user_name=?";
	
	
	public static Connection getConnection(){
		DataSource ds = null;
		Connection con = null;
		try{
			Context ctx = new InitialContext();
			if(ctx == null){
				throw new RuntimeException("JDNI Context could not found");
			}
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/crmDB");
			if(ds == null){
				throw new RuntimeException("DataSource could not found");
			}
			con = ds.getConnection();
		} catch(SQLException e){
			throw new RuntimeException("A database error occured. "+ e.getMessage());
		} catch(NamingException e){
			throw new RuntimeException("A JNDI error occured. "+ e.getMessage());
		}
		return con;
	}

	public void createAccount(Account acct){
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = getConnection();
			
		    // Use the Connection to create a Statement object
		    stmt = con.prepareStatement(INSERT_USERS_STMT);
		    stmt.setString(1,acct.getAccount());
			stmt.setString(2,acct.getPassword());
		    stmt.executeUpdate();
			for(String role:acct.getRoles()){
				stmt = con.prepareStatement(INSERT_USER_ROLES_STMT);
				stmt.setString(1,acct.getAccount());
				stmt.setString(2,role);
				stmt.executeUpdate();
			}
		   
		} catch (SQLException e) {
		    throw new RuntimeException("A database error occured. "+ e.getMessage());
		} finally {
		    if(stmt != null){
				try{
					stmt.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close(); 
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	public boolean isDuplicated(String account) {
		
		Connection con         = null;
		PreparedStatement stmt = null;
		ResultSet rs           = null;
		Account  acct          = null;
		int num_of_rows = 0;
		boolean result = false;
		
		try {
			con = getConnection();
			
		    // Use the Connection to create a Statement object
		    stmt = con.prepareStatement(RETRIEVE_USERS_STMT);
			stmt.setString(1,account);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				num_of_rows++;
		    }
			
			if( num_of_rows != 0 ){
				result = true;
			} 
		   
			return result;
		} catch (SQLException e) {
		    throw new RuntimeException("A database error occured. "+ e.getMessage());
		} finally {
			if(rs != null){
				try{
					rs.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
		    if(stmt != null){
				try{
					stmt.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close(); 
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public void updateAccount(Account acct){
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = getConnection();
			
		    // Use the Connection to create a PreparedStatement object
		    stmt = con.prepareStatement(UPDATE_USERS_STMT);
		    stmt.setString(1,acct.getPassword());
			stmt.setString(2,acct.getAccount());
		    stmt.executeUpdate();
			
			stmt = con.prepareStatement(DELETE_USER_ROLES_STMT);
			stmt.setString(1,acct.getAccount());
		    stmt.executeUpdate();
			
			for(String role:acct.getRoles()){
				stmt = con.prepareStatement(INSERT_USER_ROLES_STMT);
				stmt.setString(1,acct.getAccount());
				stmt.setString(2,role);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
		    throw new RuntimeException("A database error occured. "+ e.getMessage());
		} finally {
		    if(stmt != null){
				try{
					stmt.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close(); 
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public void deleteAccount(String account){
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = getConnection();
			
		    // Use the Connection to create a Statement object
		    stmt = con.prepareStatement(DELETE_USERS_STMT);
			stmt.setString(1,account);
		    stmt.executeUpdate();
		   
			stmt = con.prepareStatement(DELETE_USER_ROLES_STMT);
			stmt.setString(1,account);
		    stmt.executeUpdate();
		   
		} catch (SQLException e) {
		    throw new RuntimeException("A database error occured. "+ e.getMessage());
		} finally {
		    if(stmt != null){
				try{
					stmt.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close(); 
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	public ArrayList<Account> retrieveAllAccounts(){
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt= null;
		ResultSet rs   = null;
		ResultSet prs  = null;
		ArrayList<Account> accounts = new ArrayList<Account>();
		Account acct = null;
		int num_of_rows = 0;
		
		
		try {
			con = getConnection();
			
		    // Use the Connection to create a Statement object
		    stmt = con.createStatement();
			
			rs = stmt.executeQuery(RETRIEVE_ALL_USERS_STMT);
			
			while (rs.next()) {
				acct = new Account();
				acct.setAccount(rs.getString("user_name").trim());
				acct.setPassword(rs.getString("user_pass").trim());
				
				pstmt = con.prepareStatement(RETRIEVE_USER_ROLES_STMT);
				pstmt.setString(1,acct.getAccount());
				prs = pstmt.executeQuery();
				while(prs.next()){
					acct.addRole(prs.getString("role_name").trim());
				}
				accounts.add(acct);
		    }
			
			return accounts;
		   
		} catch (SQLException e) {
		    throw new RuntimeException("A database error occured. "+ e.getMessage());
		} finally {
			if(rs != null){
				try{
					rs.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(prs != null){
				try{
					prs.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
		    if(stmt != null){
				try{
					stmt.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close(); 
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	public Account getAccount(String account) throws ObjectNotFoundException{
		
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement pstmt= null;
		ResultSet rs   = null;
		ResultSet prs  = null;
		Account  acct = null;
		int num_of_rows = 0;
		
		try {
			con = getConnection();
			
		    // Use the Connection to create a Statement object
		    stmt = con.prepareStatement(RETRIEVE_USERS_STMT);
			stmt.setString(1,account);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				num_of_rows++;
				if(num_of_rows > 1){
					throw new SQLException("Too many rows for "+account+" were returned.");
				}
				acct = new Account();
				acct.setAccount(rs.getString("user_name"));
				acct.setPassword(rs.getString("user_pass"));
				
				pstmt = con.prepareStatement(RETRIEVE_USER_ROLES_STMT);
				pstmt.setString(1,acct.getAccount());
				prs = pstmt.executeQuery();
				while(prs.next()){
					acct.addRole(prs.getString("role_name").trim());
				}
		    }
			
			if( acct != null ){
				return acct;
			} else {
				throw new ObjectNotFoundException("The Account does not exist!");
			}
		   
		} catch (SQLException e) {
		    throw new RuntimeException("A database error occured. "+ e.getMessage());
		} finally {
			if(rs != null){
				try{
					rs.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(prs != null){
				try{
					prs.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
		    if(stmt != null){
				try{
					stmt.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null){
				try{
					pstmt.close(); 
				}catch(SQLException e){
					e.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close(); 
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
  
	
}
