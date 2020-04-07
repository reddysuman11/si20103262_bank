package com.si20103262.bank.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.si20103262.bank.entity.AccountEntity;
import com.si20103262.bank.model.Account;
import com.si20103262.bank.model.TransferBalanceRequest;
import com.si20103262.bank.repository.AccountTrackerRepository;
import com.si20103262.bank.testutils.AccountTestUtil;
import com.si20103262.bank.util.AccountUtil;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	@Autowired
	@Mock
	private AccountTrackerRepository customerAccountTrackerRepository;

	@Autowired
	@InjectMocks
	AccountServiceImpl accountService;

	@Test
	public void testCreateAccount() {
		Account account = AccountTestUtil.createAccount();
		assertNull(accountService.createAccount(account));
	}

	@Test
	public void testGetAllCustomers() {

		List<AccountEntity> list = new ArrayList<>();
		list.add(AccountUtil.modelToEntity(AccountTestUtil.createAccount()));

		when(customerAccountTrackerRepository.findAll()).thenReturn((list));
		assertNotNull(accountService.getAllCustomers());
	}

	@Test
	public void testGetCustomerById() {
		when(customerAccountTrackerRepository.findById(123L)).thenReturn(Optional.of(AccountUtil.modelToEntity(AccountTestUtil.createAccount())));
		assertEquals(123L, accountService.getCustomerById(123L).getAccountNumber());
	}

	@Test
	public void testGetCustomerByAccountHolder() {

		List<AccountEntity> list = new ArrayList<>();
		list.add(AccountUtil.modelToEntity(AccountTestUtil.createAccount()));

		when(customerAccountTrackerRepository.findByAccountHolder("suman")).thenReturn(list);
		assertEquals(1, accountService.getCustomerByAccountHolder("suman").size());
	}

	@Test
	public void testGetCustomerByAccountType() {

		List<AccountEntity> list = new ArrayList<>();
		list.add(AccountUtil.modelToEntity(AccountTestUtil.createAccount()));

		when(customerAccountTrackerRepository.findByAccountType("SAVING")).thenReturn(list);
		assertEquals(1, accountService.getCustomerByAccountType("SAVING").size());

	}

	@Test
	public void testGetCustomersByAny_getCustomerByAccountType() {

		Optional<String> accountType = Optional.of("SAVING");
		Optional<String> accountHolder = Optional.empty();

		List<AccountEntity> list = new ArrayList<>();
		list.add(AccountUtil.modelToEntity(AccountTestUtil.createAccount()));

		when(customerAccountTrackerRepository.findByAccountType("SAVING")).thenReturn(list);
		assertEquals(1, accountService.getCustomersByAny(accountHolder, accountType).size());

	}

	@Test
	public void testGetCustomersByAny_GetCustomerByAccountHolder() {

		Optional<String> accountHolder = Optional.of("suman");
		Optional<String> accountType = Optional.empty();

		List<AccountEntity> list = new ArrayList<>();
		list.add(AccountUtil.modelToEntity(AccountTestUtil.createAccount()));

		when(customerAccountTrackerRepository.findByAccountHolder("suman")).thenReturn(list);
		assertEquals(1, accountService.getCustomersByAny(accountHolder, accountType).size());

	}

	@Test
	public void testUpdateAccount() {
		Account account = AccountTestUtil.createAccount();
		assertNull(accountService.updateAccount(account));
	}

	@Test
	public void testDeleteAllCustomers() {
		accountService.deleteAllCustomers();
		assertTrue(true);
	}

	@Test
	public void testDeleteCustomerById() {
		when(customerAccountTrackerRepository.findById(123L)).thenReturn(Optional.of(AccountUtil.modelToEntity(AccountTestUtil.createAccount())));
		accountService.deleteCustomerById(123L);
		assertTrue(true);
	}

	@Test
	public void testFundsTransfer() {

		TransferBalanceRequest transferBalanceRequest = new TransferBalanceRequest();
		transferBalanceRequest.setAmount(100.00);
		transferBalanceRequest.setFromAccountNumber(2L);
		transferBalanceRequest.setToAccountNumber(1L);

		Account accountFrom = AccountTestUtil.createAccount();
		Account accountTo = AccountTestUtil.createAccount();
		accountFrom.setAccountNumber(2L);
		accountTo.setAccountNumber(1L);

		when(customerAccountTrackerRepository.findById(1L)).thenReturn(Optional.of(AccountUtil.modelToEntity(accountTo)));
		when(customerAccountTrackerRepository.findById(2L)).thenReturn(Optional.of(AccountUtil.modelToEntity(accountFrom)));

		assertEquals(transferBalanceRequest.getAmount(),accountService.fundsTransfer(transferBalanceRequest).getAmount());
	}
}
