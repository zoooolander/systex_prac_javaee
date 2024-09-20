package com.systex.model;

import java.util.ArrayList;

public class RegistrationService{
    
        public ArrayList<Customer> retrieveAllCustomers(){
            CustomerService cs = new CustomerService();
            return cs.retrieveAllCustomers();
        }

	public void register(Customer cust, Account acct){
		CustomerService cs = new CustomerService();
		AccountService as = new AccountService();
		cs.createCustomer(cust);
		as.createAccount(acct);
	}
	public void update(Customer cust, Account acct){
		CustomerService cs = new CustomerService();
		AccountService as = new AccountService();
		cs.updateCustomer(cust);
		as.updateAccount(acct);
	}
        
        public void delete(String account) throws ObjectNotFoundException{
                Object[] oa = this.retrieve(account);
                int cid = ((Customer)oa[1]).getCid();
		CustomerService cs = new CustomerService();
		AccountService as = new AccountService();
                cs.deleteCustomer(cid);
                as.deleteAccount(account);		
	}
        
        
	
	public Object[] retrieve(String account) throws ObjectNotFoundException{
		Object[] oa = new Object[2];
		try{
			CustomerService cs = new CustomerService();
			AccountService as = new AccountService();
			oa[0] = as.getAccount(account);
			oa[1] = cs.getCustomerByAccount(account);
		}catch(ObjectNotFoundException e){
			throw new ObjectNotFoundException("This Customer does not exists.......");
		}
		return oa;
	}
	
	
	public boolean isDuplicated(String account){
		return (new AccountService()).isDuplicated(account);
	}

}