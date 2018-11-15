package persistance.repository;



import java.util.HashMap;

import javax.enterprise.inject.Alternative;
import javax.faces.bean.SessionScoped;


import persistance.domain.Accounts;

@Alternative
@SessionScoped
public class AccountsMapRepository implements AccountRepository{
	HashMap<Integer,Accounts> accountsList = new HashMap<Integer,Accounts>();
	
	public Accounts findAccount(int accountNumber) {
		if(accountsList.containsKey(accountNumber)) {
			return accountsList.get(accountNumber);
		}
		return null;
	}


	public boolean createAccount(Accounts newAccount) {
		if(accountsList.containsKey(newAccount.getAccountNumber())) {
			return false;
		}else {
			accountsList.put(newAccount.getAccountNumber(), newAccount);
			return true;
		}
	}


	public boolean updateAccount(Accounts updateAccount,int idToChange) {
		if(accountsList.containsKey(idToChange)) {
			Accounts oldAccount = accountsList.get(idToChange);
			oldAccount.setFirstName(updateAccount.getFirstName());
			oldAccount.setSecondName(updateAccount.getSecondName());
			accountsList.remove(idToChange);
			accountsList.put(idToChange, oldAccount);
			return true;
		}
		return false;
	}


	public boolean deleteAccount(int accountNumber) {
		if(accountsList.containsKey(accountNumber)) {
			accountsList.remove(accountNumber);
			return true;
		}else {
			return false;
		}
	}


	@Override
	public String getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

}
