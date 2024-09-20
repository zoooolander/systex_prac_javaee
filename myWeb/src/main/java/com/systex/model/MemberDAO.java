package com.systex.model;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import java.io.*;
import java.util.*;
public class MemberDAO {
  
	public ArrayList<Member> getAllMembers(){
		DataSource ds = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Member> members = new ArrayList<Member>();
		Member m = null;
		
		try {
			InitialContext ic = new InitialContext();	
			ds = (DataSource)ic.lookup("java:comp/env/jdbc/crmDB");
			con = ds.getConnection();
			

		    // Use the Connection to create a Statement object
		    stmt = con.createStatement ();

		    // Execute query using Statement, receive the ResultSet
		    String qry ="SELECT * FROM MEMBER c;";
		    rs = stmt.executeQuery(qry);
		    System.out.println("EXECUTED QUERY ---> " + qry);

		    // Print the results, row by row
		    System.out.println("\nPROCESSING RESULTS:\n");
		    
			while (rs.next()) {
				m = new Member();
				m.setId(rs.getInt("ID"));
				m.setFirstName(rs.getString("FIRSTNAME").trim());
				m.setLastName(rs.getString("LASTNAME").trim());
				m.setStreet(rs.getString("STREET").trim());
				m.setCity(rs.getString("CITY").trim());
				members.add(m);
		    }
		    rs.close();
		    stmt.close();
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return members;
	}
  
	public static void main (String args[]) {
		PrintStream out = System.out;
		MemberDAO mdao = new MemberDAO();
		ArrayList<Member> members = mdao.getAllMembers();
		for(Member m : members){
			out.print(m.getId()+"\t");
			out.print(m.getFirstName()+"\t");
			out.print(m.getLastName()+"\t");
			out.print(m.getStreet()+"\t");
			out.print(m.getCity()+"\t");
			out.println();
		}
		
	}
}
