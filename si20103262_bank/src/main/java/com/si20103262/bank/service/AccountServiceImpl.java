package com.si20103262.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si20103262.bank.entity.AccountEntity;
import com.si20103262.bank.exception.AccountNotFoundException;
import com.si20103262.bank.exception.InvalidAccountNumberException;
import com.si20103262.bank.model.Account;
import com.si20103262.bank.model.TransferBalanceRequest;
import com.si20103262.bank.repository.AccountTrackerRepository;
import com.si20103262.bank.util.AccountUtil;
import com.si20103262.bank.util.CommonUtil;

/**
 * 
 * @author si20103262
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER=LoggerFactory.getLogger(AccountServiceImpl.class);
	private static final String LOGGER_RESPONSE = "Response :: {}";
	
	@Autowired
	private AccountTrackerRepository customerAccountTrackerRepository;
		
	@Override
	public Account craeteAccount(Account customer) {
		
		LOGGER.info("craeteCustomerAccount() of CustomerAccountServiceImpl :: {} ", customer);
		
		return AccountUtil.entityToModel(customerAccountTrackerRepository.save(AccountUtil.modelToEntity(customer)));
	}
	
	
	@Override	
	public List<Account> getAllCustomers() {

		LOGGER.info("getAllCustomers() of CustomerAccountServiceImpl");
		
		List<Account> list = new ArrayList<>();
		customerAccountTrackerRepository.findAll().forEach(customer -> {
			list.add(AccountUtil.entityToModel(customer));
		});
		
		LOGGER.info(LOGGER_RESPONSE, list);

		return list;

	}
	
	
	@Override
	public Account getCustomerById(Long id) {
		
		LOGGER.info("getCustomerById() of CustomerAccountServiceImpl ID :: {} ", id);
		
		if (id == null) {
			return null;
		}
		
		Account customer = null;
		Optional<AccountEntity> custEntity = customerAccountTrackerRepository.findById(id);
		
		if(custEntity.isPresent()) {
			customer = AccountUtil.entityToModel(custEntity.get());
		} else {
			throw new AccountNotFoundException("No Account found with Id :: "+id);
		}
		
		LOGGER.info(LOGGER_RESPONSE, custEntity);
		
		return customer;
	}
	
	
	@Override
	public List<Account> getCustomerByAccountHolder(String accountHolder) {
		
		LOGGER.info("getCustomerByAccountHolder() of CustomerAccountServiceImpl accountHolder :: {}", accountHolder);
		
		List<AccountEntity> ce = customerAccountTrackerRepository.findByAccountHolder(accountHolder);
		
	
		if(Boolean.TRUE.equals(CommonUtil.isListEmpty(ce))) {
			throw new AccountNotFoundException("No Account found with this accountHolder :: " + accountHolder);
		}
		
		LOGGER.info(LOGGER_RESPONSE, ce);
		
		return AccountUtil.entityToModel(ce);
	}
	
	@Override
	public List<Account> getCustomerByAccountType(String accountType) {

		LOGGER.info("getCustomerByAccountType() of CustomerAccountServiceImpl accountType :: {} ", accountType);
		
		List<AccountEntity> ce = customerAccountTrackerRepository.findByAccountType(accountType);

		if(Boolean.TRUE.equals(CommonUtil.isListEmpty(ce))) {
			throw new AccountNotFoundException("No Account found with this accountHolder");
		}


		LOGGER.info(LOGGER_RESPONSE, ce);
		
		return AccountUtil.entityToModel(ce);
	}
	
	public List<Account> getCustomersByAny(Optional<String> accountHolder, Optional<String> accountType) {

		LOGGER.info("getCustomersByAny() of CustomerAccountServiceImpl");

		if (accountType.isPresent()) {
			return this.getCustomerByAccountType(accountType.get());
		}
		
		if (accountHolder.isPresent()) {
			return this.getCustomerByAccountHolder(accountHolder.get());
		}
		
		throw new AccountNotFoundException("No Account found");
	}
		
	@Override
	public Account updateAccount(Account account) {

		LOGGER.info("updateAccount() of CustomerAccountServiceImpl account :: {}", account);

		return AccountUtil.entityToModel(customerAccountTrackerRepository.save(AccountUtil.modelToEntity(account)));
	}
	
	@Override
	public void deleteAllCustomers() {

		LOGGER.info("deleteAllCustomers() of CustomerAccountServiceImpl");

		customerAccountTrackerRepository.deleteAll();
	}
	
	@Override
	public void deleteCustomerById(Long id) {

		LOGGER.info("deleteCustomerById() of CustomerAccountServiceImpl id :: {}", id);

		getCustomerById(id);
		customerAccountTrackerRepository.deleteById(id);
	}
	

	@Override
	public TransferBalanceRequest fundsTransfer(TransferBalanceRequest transferBalanceRequest) {
	
		LOGGER.info("fundsTransfer() of CustomerAccountServiceImpl transferBalanceRequest :: {}", transferBalanceRequest);

		Long fromAct = transferBalanceRequest.getFromAccountNumber();
		Long toAct = transferBalanceRequest.getToAccountNumber();
		
		if (fromAct == null ) {
			throw new InvalidAccountNumberException("Invalid/null fromAccount number");
		}
		
		if (toAct == null ) {
			throw new InvalidAccountNumberException("Invalid/null ToAccount number");
		}
		
		Optional<AccountEntity> fromAccountEntity =  customerAccountTrackerRepository.findById(fromAct);
		Optional<AccountEntity> toAccountEntity =  customerAccountTrackerRepository.findById(toAct);
		
		if (!toAccountEntity.isPresent()) {
			throw new InvalidAccountNumberException("Invalid/null toAccount number");
		}
		
		if (!fromAccountEntity.isPresent() || fromAccountEntity.get().getBalance() == 0) {
			throw new InvalidAccountNumberException("Invalid/null fromAccount number");
		}
		
		Double fb = fromAccountEntity.get().getBalance();
		Double transferedAmt = transferBalanceRequest.getAmount();
		
		if (transferedAmt > fb) {
			throw new InvalidAccountNumberException("Insufficient funds");
		}
		
		fromAccountEntity.get().setBalance(fb-transferedAmt);
		customerAccountTrackerRepository.save(fromAccountEntity.get());
		
		toAccountEntity.get().setBalance(toAccountEntity.get().getBalance()+transferedAmt);
		customerAccountTrackerRepository.save(toAccountEntity.get());
		
		return transferBalanceRequest;
	}
}
