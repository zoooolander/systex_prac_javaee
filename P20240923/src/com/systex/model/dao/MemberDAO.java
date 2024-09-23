package com.systex.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.systex.model.Member;

public class MemberDAO {
	/**
	 * @Description create and register driver object
	 * @return
	 */
	public Connection getConnection() {
		Connection con = null;
		try { // 沒辦法載入的話會丟出exception
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// 2. create connection object
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crmdb", "root", "welcome1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}

	/**
	 * @Description create one member
	 * @param member
	 */
	public void create(Member member) {
		try (
				Connection con = this.getConnection();
				PreparedStatement pstmt = con.prepareStatement("insert into member values(?,?,?,?,?)");
			) {
			pstmt.setInt(1, member.getId());
			pstmt.setString(2, member.getFirstName());
			pstmt.setString(3, member.getLastName());
			pstmt.setString(4, member.getStreet());
			pstmt.setString(5, member.getCity());
			int count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("create member sucessfully");
	}

	/**
	 * @Description update member info
	 * @param member
	 */
	public void update(Member member) {
		try (
				Connection con = this.getConnection();
				PreparedStatement pstmt = con.prepareStatement("update member set FIRSTNAME=?, LASTNAME=?, STREET=?, CITY=? where ID=?");
			) {
			pstmt.setInt(5,member.getId());
			pstmt.setString(1, member.getFirstName());
			pstmt.setString(2, member.getLastName());
			pstmt.setString(3, member.getStreet());
			pstmt.setString(4, member.getCity());

			int count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("update member sucessfully");
	}
	
	
	/**
	 * @Description get one member info
	 * @param id
	 * @return
	 */
	public Member get(int id) {
		Member member = null;
		ResultSet rs = null;
		try (Connection con = this.getConnection();
				// Statement stmt = con.createStatement();
				PreparedStatement pstmt = con.prepareStatement("select * from member where id = ?");
		// ResultSet rs = stmt.executeQuery("select * from member where id = " + id);
		) {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				member = new Member();
				member.setId(rs.getInt("ID"));
				member.setFirstName(rs.getString("FIRSTNAME"));
				member.setLastName(rs.getString("LASTNAME"));
				member.setStreet(rs.getString("STREET"));
				member.setCity(rs.getString("CITY"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member;
	}

	/**
	 * @Deseription get all member info
	 * @return
	 */
	public List<Member> getAll() {
		LinkedList<Member> allMembers = new LinkedList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 2. 建立連線物件
			con = this.getConnection();
			// 3. 建立SQL包裝物
			stmt = con.createStatement();
			// 4. 執行CRUD資料存取邏輯
			rs = stmt.executeQuery("select * from member");
			// 5. 處理回傳結果
			Member member;
			while (rs.next()) {
				member = new Member();
				member.setId(rs.getInt("ID"));
				member.setFirstName(rs.getString("FIRSTNAME"));
				member.setLastName(rs.getString("LASTNAME"));
				member.setStreet(rs.getString("STREET"));
				member.setCity(rs.getString("CITY"));
				allMembers.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 6. 關閉所有連線物件
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allMembers;
	}

	public static void main(String args[]) {
		MemberDAO memberDao = new MemberDAO();
		//System.out.println(memberDao.get(0));
		// create Member
//		Member member = new Member(50, "Michael", "Jordan", "Sall Street Blvd", "New York");
//		memberDao.create(member);
		
		System.out.println("=======================");
		List<Member> allMembers = memberDao.getAll();
		//allMembers.forEach(m -> System.out.println(m));
		Member member = memberDao.get(50);
		member.setFirstName("Stephen");
		member.setLastName("Curry");
		memberDao.update(member);
		System.out.println(memberDao.get(50));

	}
}
