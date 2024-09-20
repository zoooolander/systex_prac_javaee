package com.systex.model;

public class AccountService{

	public void createAccount(Account acct){
		AccountDAO acctDAO = new AccountDAO();
		acctDAO.createAccount(acct);
	}
	
	public void updateAccount(Account acct){
		AccountDAO acctDAO = new AccountDAO();
		acctDAO.updateAccount(acct);
	}
        
        public void deleteAccount(String account){
		AccountDAO acctDAO = new AccountDAO();
		acctDAO.deleteAccount(account);
	}
	
	public boolean isDuplicated(String account){
		return (new AccountDAO()).isDuplicated(account);
	}
	
	public Account getAccount(String account) throws ObjectNotFoundException{
		AccountDAO acctDAO = new AccountDAO();
		return acctDAO.getAccount(account);
	}

}