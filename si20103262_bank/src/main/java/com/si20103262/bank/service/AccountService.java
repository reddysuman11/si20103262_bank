package com.si20103262.bank.service;

import java.util.List;
import java.util.Optional;

import com.si20103262.bank.model.Account;
import com.si20103262.bank.model.TransferBalanceRequest;

/**
 * 
 * @author si20103262
 *
 */
public interface AccountService {

	public Account craeteAccount(Account customer);
	
	public List<Account> getAllCustomers();
	public Account getCustomerById(Long id);
	public List<Account> getCustomersByAny(Optional<String> accountHolder, Optional<String> accountType);
	public List<Account> getCustomerByAccountHolder(String accountHolder);
	public List<Account> getCustomerByAccountType(String accountType);
	public Account updateAccount(Account customerEntity);
	
	public void deleteAllCustomers();
	public void deleteCustomerById(Long id);
	public TransferBalanceRequest fundsTransfer(TransferBalanceRequest transferBalanceRequest);
}
