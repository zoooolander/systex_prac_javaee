package com.systex.model;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import java.io.*;
import java.util.*;

public class CustomerDAO {

    private static final String INSERT_STMT =
            "INSERT INTO Customer(name,email,telephone,address,gender,habit,birth,account) VALUES(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_STMT =
            "UPDATE Customer SET name=?,email=?,telephone=?,address=?,gender=?,habit=?,birth=? where id = ?";
    private static final String DELETE_STMT =
            "DELETE FROM Customer where id=?";
    private static final String RETRIEVE_ALL_STMT =
            "SELECT * FROM Customer";
    private static final String RETRIEVE_STMT =
            "SELECT * FROM Customer  WHERE id=?";
    private static final String RETRIEVE_BY_ACCOUNT_STMT =
            "SELECT * FROM Customer  WHERE account=?";

    public static Connection getConnection() {
        DataSource ds = null;
        Connection con = null;
        try {
            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JDNI Context could not found");
            }
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/crmDB");
            if (ds == null) {
                throw new RuntimeException("DataSource could not found");
            }
            con = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("A database error occured. " + e.getMessage());
        } catch (NamingException e) {
            throw new RuntimeException("A JNDI error occured. " + e.getMessage());
        }
        return con;
    }

    public static Connection getConnection2() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/crm";
            String id = "root";
            String pw = "root";
            con = DriverManager.getConnection(url, id, pw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public void createCustomer(Customer cust) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();

            // Use the Connection to create a Statement object
            stmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, cust.getName());
            stmt.setString(2, cust.getEmail());
            stmt.setString(3, cust.getTelephone());
            stmt.setString(4, cust.getAddress());
            stmt.setString(5, cust.getGender());
            String[] habits = cust.getHabits();
            String habitString = "";
            int len = habits.length;
            for (int i = 0; i < len; i++) {
                habitString += habits[i];
                if (i < len - 1) {
                    habitString += ",";
                }
            }
            stmt.setString(6, habitString);
            stmt.setString(7, cust.getBirth());
            stmt.setString(8, cust.getAccount());

            // Execute query using Statement, receive the ResultSet
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            //rs.next();
            if (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int colCount = rsmd.getColumnCount();
                do {
                    for (int i = 1; i <= colCount; i++) {
                        String key = rs.getString(i);
                        System.out.println("key " + i + "is " + key);
                    }
                } while (rs.next());
                
            } else {
                System.out.println("There are no generated keys.");
            }

            //System.out.println(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("A database error occured. " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    public void updateCustomer(Customer cust) {

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();

            // Use the Connection to create a Statement object
            stmt = con.prepareStatement(UPDATE_STMT);

            stmt.setString(1, cust.getName());
            stmt.setString(2, cust.getEmail());
            stmt.setString(3, cust.getTelephone());
            stmt.setString(4, cust.getAddress());
            stmt.setString(5, cust.getGender());
            String[] habits = cust.getHabits();
            String habitString = "";
            int len = habits.length;
            for (int i = 0; i < len; i++) {
                habitString += habits[i];
                if (i < len - 1) {
                    habitString += ",";
                }
            }
            stmt.setString(6, habitString);
            stmt.setString(7, cust.getBirth());
            stmt.setInt(8, cust.getCid());


            // Execute query using Statement, receive the ResultSet
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("A database error occured. " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public void deleteCustomer(int cid) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Customer cust = null;
        int num_of_rows = 0;

        try {
            con = getConnection();

            // Use the Connection to create a Statement object
            stmt = con.prepareStatement(DELETE_STMT);
            stmt.setInt(1, cid);

            int count = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("A database error occured. " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public ArrayList<Customer> retrieveAllCustomers() {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Customer cust = null;

        try {
            con = getConnection();

            // Use the Connection to create a Statement object
            stmt = con.createStatement();

            rs = stmt.executeQuery(RETRIEVE_ALL_STMT);

            while (rs.next()) {
                cust = new Customer();
                cust.setCid(rs.getInt("ID"));
                cust.setName(rs.getString("name").trim());
                cust.setEmail(rs.getString("email").trim());
                cust.setTelephone(rs.getString("telephone").trim());
                cust.setAddress(rs.getString("address").trim());
                cust.setGender(rs.getString("gender").trim());
                String[] habits = (rs.getString("habit").trim()).split(",");
                cust.setHabits(habits);
                cust.setBirth(rs.getString("birth").trim());
                cust.setAccount(rs.getString("account").trim());
                customers.add(cust);
            }

            return customers;

        } catch (SQLException e) {
            throw new RuntimeException("A database error occured. " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public Customer getCustomer(int cid) throws ObjectNotFoundException {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Customer cust = null;
        int num_of_rows = 0;

        try {
            con = getConnection();

            // Use the Connection to create a Statement object
            stmt = con.prepareStatement(RETRIEVE_STMT);
            stmt.setInt(1, cid);

            rs = stmt.executeQuery();

            while (rs.next()) {
                num_of_rows++;
                if (num_of_rows > 1) {
                    throw new SQLException("Too many rows were returned.");
                }
                cust = new Customer();
                cust.setCid(rs.getInt("ID"));
                cust.setName(rs.getString("name").trim());
                cust.setEmail(rs.getString("email").trim());
                cust.setTelephone(rs.getString("telephone").trim());
                cust.setAddress(rs.getString("address").trim());
                cust.setGender(rs.getString("gender").trim());
                String[] habits = (rs.getString("habit").trim()).split(",");
                cust.setHabits(habits);
                cust.setBirth(rs.getString("birth").trim());
                cust.setAccount(rs.getString("account").trim());
            }

            if (cust != null) {
                return cust;
            } else {
                throw new ObjectNotFoundException("The Customer does not exist!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("A database error occured. " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public Customer getCustomerByAccount(String account) throws ObjectNotFoundException {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Customer cust = null;
        int num_of_rows = 0;

        try {
            con = getConnection();

            // Use the Connection to create a Statement object
            stmt = con.prepareStatement(RETRIEVE_BY_ACCOUNT_STMT);
            stmt.setString(1, account);

            rs = stmt.executeQuery();

            while (rs.next()) {
                num_of_rows++;
                if (num_of_rows > 1) {
                    throw new SQLException("Too many rows were returned.");
                }
                cust = new Customer();
                cust.setCid(rs.getInt("ID"));
                cust.setName(rs.getString("name").trim());
                cust.setEmail(rs.getString("email").trim());
                cust.setTelephone(rs.getString("telephone").trim());
                cust.setAddress(rs.getString("address").trim());
                cust.setGender(rs.getString("gender").trim());
                String[] habits = (rs.getString("habit").trim()).split(",");
                cust.setHabits(habits);
                cust.setBirth(rs.getString("birth").trim());
                cust.setAccount(rs.getString("account").trim());
            }

            if (cust != null) {
                return cust;
            } else {
                throw new ObjectNotFoundException("The Customer does not exist!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("A database error occured. " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public static void main(String[] args) {
        Customer cust = new Customer();
        cust.setName("Sparrow");
        cust.setEmail("sparrow@uuu.com.tw");
        cust.setTelephone("25149191");
        cust.setAddress("Taipei 101");
        cust.setBirth("1988-8-8");
        cust.setGender("male");
        cust.setHabits(new String[]{"music", "shopping"});
        cust.setAccount("");
        CustomerDAO cdao = new CustomerDAO();
        //cdao.createCustomer(cust);
        cdao.deleteCustomer(1);

    }
}
