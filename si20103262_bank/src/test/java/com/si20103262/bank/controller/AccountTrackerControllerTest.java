package com.si20103262.bank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.si20103262.bank.exception.AccountNotFoundException;
import com.si20103262.bank.model.Account;
import com.si20103262.bank.model.TransferBalanceRequest;
import com.si20103262.bank.service.AccountService;
import com.si20103262.bank.testutils.AccountTestUtil;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountTrackerControllerTest {

	@Autowired
	@InjectMocks
	AccountTrackerController accountTrackerController;

	@Mock
	private AccountService customerAccountService;

	@Test
	public void testCreateCustomerAccount() {
		Account account = AccountTestUtil.createAccount();
		when(customerAccountService.createAccount(account)).thenReturn(account);
		assertEquals(HttpStatus.CREATED, accountTrackerController.createCustomerAccount(account).getStatusCode());
	}

	@Test
	public void testUpdateCustomerAccount() {
		Account account = AccountTestUtil.createAccount();
		when(customerAccountService.updateAccount(account)).thenReturn(account);
		assertEquals(HttpStatus.OK, accountTrackerController.updateCustomerAccount(account).getStatusCode());
	}

	@Test
	public void testDeleteCustomerAccount() {
		accountTrackerController.deleteCustomerAccount(12L);
		assertTrue(true);
	}

	@Test
	public void testDeleteCustomerAccounts() {
		accountTrackerController.deleteCustomerAccounts();
		assertTrue(true);
	}

	@Test
	public void testGetAllCustomerAccounts() {
		when(customerAccountService.getAllCustomers()).thenReturn(Arrays.asList(AccountTestUtil.createAccount()));
		assertEquals(HttpStatus.OK, accountTrackerController.getAllCustomerAccounts().getStatusCode());
	}

	@Test
	public void testGetCustomerAccountById() {
		when(customerAccountService.getCustomerById(12L)).thenReturn(AccountTestUtil.createAccount());
		assertEquals(HttpStatus.OK, accountTrackerController.getCustomerAccountById(12L).getStatusCode());
	}

	@Test
	public void testGetCustomerAccountByAny() {

		Optional<String> accountHolder = Optional.of("SUMAN");
		Optional<String> accountType = Optional.of("SAVING");

		when(customerAccountService.getCustomersByAny(accountHolder, accountType)).thenReturn(Arrays.asList(AccountTestUtil.createAccount()));
		assertEquals(HttpStatus.FOUND, accountTrackerController.getCustomerAccountByAny(accountHolder, accountType).getStatusCode());
	}

	@Test
	public void testTransferFunds() {
		TransferBalanceRequest transferBalanceRequest = new TransferBalanceRequest();
		when(customerAccountService.fundsTransfer(transferBalanceRequest)).thenReturn(transferBalanceRequest);
		assertEquals(HttpStatus.FOUND, accountTrackerController.transferFunds(transferBalanceRequest).getStatusCode());
	}

	@Test(expected = AccountNotFoundException.class)
	public void testGetCustomerAccountByAny_exception() {

		Optional<String> accountHolder = Optional.of("SUMAN");
		Optional<String> accountType = Optional.of("SAVING");

		when(customerAccountService.getCustomersByAny(accountHolder, accountType)).thenReturn(Arrays.asList());
		assertEquals(HttpStatus.NOT_FOUND,accountTrackerController.getCustomerAccountByAny(accountHolder, accountType).getStatusCode());
	}
}
