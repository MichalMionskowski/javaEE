package persistance.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.HashMap;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import persistance.domain.Accounts;

@Alternative
@Transactional(SUPPORTS)
public class AccountsMapRepository implements AccountRepository{
	HashMap<Integer,Accounts> accountsList = new HashMap<Integer,Accounts>();
	
	public Accounts findAccount(@NotNull int accountNumber) {
		if(accountsList.containsKey(accountNumber)) {
			return accountsList.get(accountNumber);
		}
		return null;
	}

	@Transactional(REQUIRED)
	public boolean createAccount(@NotNull Accounts newAccount) {
		if(accountsList.containsKey(newAccount.getAccountNumber())) {
			return false;
		}else {
			accountsList.put(newAccount.getAccountNumber(), newAccount);
			return accountsList.containsKey(newAccount.getAccountNumber()) ? true : false;
		}
	}

	@Transactional(REQUIRED)
	public boolean updateAccount(@NotNull Accounts updateAccount,@NotNull int idToChange) {
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

	@Transactional(REQUIRED)
	public boolean deleteAccount(@NotNull int accountNumber) {
		if(accountsList.containsKey(accountNumber)) {
			accountsList.remove(accountNumber);
			return true;
		}else {
			return false;
		}
	}

}
