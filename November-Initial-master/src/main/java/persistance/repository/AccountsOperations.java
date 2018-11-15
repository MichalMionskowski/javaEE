package persistance.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import persistance.domain.Accounts;

@Transactional(SUPPORTS)
public class AccountsOperations {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	public List<Accounts> getAllAccounts(){
		TypedQuery<Accounts> query = em.createQuery("SELECT a FROM Accounts a", Accounts.class);
		return query.getResultList();
	}
	
	public Accounts findAccount(int accountNumber){
		TypedQuery<Accounts> query = em.createQuery("SELECT a FROM Accounts a WHERE a.accountNumber='" + accountNumber + "'", Accounts.class);
		return query.getSingleResult();
	}
	@Transactional(REQUIRED)
	public void createAnAccount(Accounts newAccount) {
		em.persist(newAccount);
	}
	@Transactional(REQUIRED)
	public void updateAnAccount(Accounts newAccount, int idToChange) {
		Accounts oldAccount = em.find(Accounts.class, idToChange);
		oldAccount.setFirstName(newAccount.getFirstName());
		oldAccount.setSecondName(newAccount.getSecondName());
		em.merge(oldAccount);
	}
	@Transactional(REQUIRED)
	public void deleteAccount(Accounts newAccount) {
		em.remove(newAccount);
	}
}

