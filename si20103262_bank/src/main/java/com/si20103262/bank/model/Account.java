package com.si20103262.bank.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author si20103262
 *
 */
@XmlRootElement
public class Account {

	@Getter @Setter private long accountNumber;
	
	@Getter @Setter private String accountHolder;
	
	@Getter @Setter private Double balance;
	
	@Getter @Setter private String accountType;

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountHolder=" + accountHolder + ", balance=" + balance
				+ ", accountType=" + accountType + "]";
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	

	
}
