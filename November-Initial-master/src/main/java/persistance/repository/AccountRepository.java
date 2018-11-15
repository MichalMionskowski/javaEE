package persistance.repository;

import persistance.domain.Accounts;

public interface AccountRepository {

	public Accounts findAccount(int accountNumber);
	
	public String createAccount(Accounts newAccount);
	
	public boolean updateAccount(Accounts updateAccount, int idToChange);
	
	public boolean deleteAccount(int accountNumber);

	public String getAllAccounts();
}
