package com.si20103262.bank.testutils;

import com.si20103262.bank.model.Account;

public class AccountTestUtil {

	public static Account createAccount () {
		Account account = new Account();
		account.setAccountHolder("suman");
		account.setAccountNumber(123);
		account.setAccountType("SAVING");
		account.setBalance(1000.0);
		
		return account;
	}
	
}
