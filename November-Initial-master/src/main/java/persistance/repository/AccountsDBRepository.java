package persistance.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;


import javax.enterprise.inject.Default;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import persistance.domain.Accounts;
import util.JSONUtil;

@Default
@Transactional(SUPPORTS)
@RequestScoped
public class AccountsDBRepository implements AccountRepository {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	public String getAllAccounts(){
		TypedQuery<Accounts> query = em.createQuery("SELECT a FROM Accounts a", Accounts.class);
		return JSONUtil.getJSONForObject(query.getResultList());
	}
	
	public Accounts findAccount(int accountNumber){
		TypedQuery<Accounts> query = em.createQuery("SELECT a FROM Accounts a WHERE a.accountNumber='" + accountNumber + "'", Accounts.class);
		return query.getSingleResult();
	}
	
	@Transactional(REQUIRED)
	public boolean createAccount(Accounts newAccount) {
		em.persist(newAccount);
		return em.find(Accounts.class,newAccount.getAccountNumber())!= null ? true : false;
	}
	
	@Transactional(REQUIRED)
	public boolean updateAccount(Accounts newAccount, int idToChange) {
		Accounts oldAccount = em.find(Accounts.class, idToChange);
		oldAccount.setFirstName(newAccount.getFirstName());
		oldAccount.setSecondName(newAccount.getSecondName());
		em.merge(oldAccount);
		return em.find(Accounts.class,oldAccount.getAccountNumber())!= null ? true : false;
	}
	
	@Transactional(REQUIRED)
	public boolean deleteAccount(int accountNumber) {
		em.remove(em.find(Accounts.class, accountNumber));
		return em.find(Accounts.class,em.contains(em.find(Accounts.class, accountNumber)))== null ? true : false;
	}
}

