package com.systex.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.LinkedList;

import com.systex.model.Customer;
import com.systex.model.CustomerService;

/**
 * Servlet implementation class createCustomer
 */
public class createCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		switch(action) {
		case "cc1":
			processCC1(request, response);
		case "cc2":
			processCC2(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void processCC1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.setCharacterEncoding("UTF-8");
		RequestDispatcher view;
		LinkedList<String> errorMsgs = new LinkedList<>();
		request.setAttribute("errors", errorMsgs);

		// 1. retrieve form data
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String address = request.getParameter("address");
		
		// 2. convert form data
		// N/A

		// 3. validate data
		if (name == null || name.trim().isEmpty()) {
			errorMsgs.add("name is required");
		}
		if (email == null || email.trim().isEmpty()) {
			errorMsgs.add("email is required");
		}
		if (telephone == null || telephone.trim().isEmpty()) {
			errorMsgs.add("telephone is required");
		}
		if (address == null || address.trim().isEmpty()) {
			errorMsgs.add("address is required");
		}
		
		if (!errorMsgs.isEmpty()) {
			view = request.getRequestDispatcher("createCustomer1.jsp");
			view.forward(request, response);
			return;// 把控制權還給container
		}
		
		// 4. invoke business logic
		try {
			Customer customer = new Customer();
			customer.setName(name);
			customer.setEmail(email);
			customer.setTelephone(telephone);
			customer.setAddress(address);
						
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);//Session-Scope
			view = request.getRequestDispatcher("createCustomer2.jsp");
			view.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			view = request.getRequestDispatcher("createCustomer1.jsp");
			view.forward(request, response);
			
		}
		
		// 5. select next view
	}
	
	protected void processCC2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.setCharacterEncoding("UTF-8");
		RequestDispatcher view;
		LinkedList<String> errorMsgs = new LinkedList<>();
		request.setAttribute("errors", errorMsgs);

		// 1. retrieve form data
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String[] habits = request.getParameterValues("habit");

		// 2. convert form data
		// N/A

		// 3. validate data
		if (birth == null || birth.trim().isEmpty()) {
			errorMsgs.add("please select your birthdate");
		}
		if (gender == null) {
			errorMsgs.add("please select your gender");
		}
		if (habits == null) {
			errorMsgs.add("please choose one habit at lease");
		}

		if (!errorMsgs.isEmpty()) {
			view = request.getRequestDispatcher("createCustomer.jsp");
			view.forward(request, response);
			return;// 把控制權還給container
		}
		
		// 4. invoke business logic
		try {
			HttpSession session = request.getSession();
			Customer customer = (Customer) session.getAttribute("customer");
			customer.setBirth(birth);
			customer.setGender(gender);
			customer.setHabits(habits);
			customer.setAccount("");
			
			CustomerService customerService = new CustomerService();
			customerService.createCustomer(customer);
			
			request.setAttribute("customer", customer);
			view = request.getRequestDispatcher("createSuccessful.jsp");
			view.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			view = request.getRequestDispatcher("createCustomer2.jsp");
			view.forward(request, response);
		
		}
		
		// 5. select next view
	}
}
