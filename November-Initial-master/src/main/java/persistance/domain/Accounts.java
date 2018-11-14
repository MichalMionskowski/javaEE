package persistance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Accounts {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer accountNumber;
	public int getAccountNumber() {
		return this.accountNumber;
	}
	public void setAccountNumber(int accountNo) {
		this.accountNumber = accountNo;
	}
	
	@Column(length=50)
	private String firstName;
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(length=50)
	private String secondName;
	public String getSecondName() {
		return this.firstName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
}
