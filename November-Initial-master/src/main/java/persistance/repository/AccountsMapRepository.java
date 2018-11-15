package persistance.repository;



import java.util.HashMap;

import javax.enterprise.inject.Alternative;
import javax.faces.bean.ApplicationScoped;

import persistance.domain.Accounts;
import util.JSONUtil;

@Alternative
@ApplicationScoped
public class AccountsMapRepository implements AccountRepository{
	static HashMap<Integer,Accounts> accountsList = new HashMap<Integer,Accounts>();
	
	public Accounts findAccount(int accountNumber) {
		if(AccountsMapRepository.accountsList.containsKey(accountNumber)) {
			return AccountsMapRepository.accountsList.get(accountNumber);
		}
		return null;
	}


	public String createAccount(Accounts newAccount) {
		if(AccountsMapRepository.accountsList.containsKey(newAccount.getAccountNumber())) {
			return "false";
		}else {
			AccountsMapRepository.accountsList.put(newAccount.getAccountNumber(), newAccount);
			return JSONUtil.getJSONForObject(AccountsMapRepository.accountsList.values());
		}
	}


	public boolean updateAccount(Accounts updateAccount,int idToChange) {
		if(AccountsMapRepository.accountsList.containsKey(idToChange)) {
			Accounts oldAccount = AccountsMapRepository.accountsList.get(idToChange);
			oldAccount.setFirstName(updateAccount.getFirstName());
			oldAccount.setSecondName(updateAccount.getSecondName());
			AccountsMapRepository.accountsList.remove(idToChange);
			AccountsMapRepository.accountsList.put(idToChange, oldAccount);
			return true;
		}
		return false;
	}


	public boolean deleteAccount(int accountNumber) {
		if(AccountsMapRepository.accountsList.containsKey(accountNumber)) {
			AccountsMapRepository.accountsList.remove(accountNumber);
			return true;
		}else {
			return false;
		}
	}


	
	public String getAllAccounts() {
		return JSONUtil.getJSONForObject(AccountsMapRepository.accountsList.values());
	}

}
