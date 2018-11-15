package business.service;

import persistance.domain.Accounts;

public interface AccountService {

	public Accounts findAccount(int accountNumber);
	
	public String createAccount(String account);
	
	public boolean updateAccount(int id, String account);
	
	public boolean deleteAccount(int id);

	public String findAccounts();
}
