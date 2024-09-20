package com.systex.model;

import java.util.ArrayList;

public class CustomerService{
    
        public ArrayList<Customer> retrieveAllCustomers(){
		CustomerDAO custDAO = new CustomerDAO();
		return custDAO.retrieveAllCustomers();
	}

	public void createCustomer(Customer cust){
		CustomerDAO custDAO = new CustomerDAO();
		custDAO.createCustomer(cust);
	}
	
	public void updateCustomer(Customer cust){
		CustomerDAO custDAO = new CustomerDAO();
		custDAO.updateCustomer(cust);
	}
        public void deleteCustomer(int cid){
		CustomerDAO custDAO = new CustomerDAO();
		custDAO.deleteCustomer(cid);
	}
	
	public Customer getCustomerByAccount(String account)throws ObjectNotFoundException{
		CustomerDAO custDAO = new CustomerDAO();
		return custDAO.getCustomerByAccount(account);
	}

}