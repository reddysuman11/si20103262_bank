package com.si20103262.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author si20103262
 *
 */

@Entity(name = "TBL_CUSTOMER")
@ToString
public class AccountEntity {

	@Id
	@GeneratedValue
	@Column(name = "ACCOUNT_NUMBER")
	private long accountNumber;
	
	@Column(name = "ACCOUNT_HOLDER")
	private String accountHolder;

	@Column(name = "BALANCE")
	private Double balance;

	@Column(name = "ACCOUNT_TYPE")
	private String accountType;

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
