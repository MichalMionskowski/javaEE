package business.service;

import javax.inject.Inject;

import persistance.domain.Accounts;
import persistance.repository.AccountRepository;
import util.JSONUtil;

public class AccountsServiceImplementation implements AccountService{

	@Inject
	private AccountRepository repo;
	
	

	@Override
	public Accounts findAccount(int accountNumber) {
		return repo.findAccount(accountNumber);
	}

	@Override
	public String createAccount(String jsonString) {
		Accounts newAccount = JSONUtil.getObjectForJSON(jsonString, Accounts.class);
		if(newAccount.getAccountNumber() != 9999) {
			return  repo.createAccount(JSONUtil.getObjectForJSON(jsonString, Accounts.class)) ?  "{\"message\":\"correct\"}": "{\"message\":\"something went wrong\"}" ;
		}
			return "{\"message\":\"This account is blocked\"}" ;
	}

	@Override
	public boolean updateAccount(int id, String jsonString) {
		return repo.updateAccount(JSONUtil.getObjectForJSON(jsonString, Accounts.class), id);
	}

	@Override
	public boolean deleteAccount(int id) {
		return repo.deleteAccount(id);
	}

	@Override
	public String findAccounts() {
		return repo.getAllAccounts();
	}

}
