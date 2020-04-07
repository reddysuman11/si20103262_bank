package com.si20103262.bank.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.si20103262.bank.exception.AccountNotFoundException;
import com.si20103262.bank.model.Account;
import com.si20103262.bank.model.TransferBalanceRequest;
import com.si20103262.bank.service.AccountService;
import com.si20103262.bank.util.CommonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="customerAccountTracker", description="Customer Account tracking")
@RequestMapping("/customerAccountTracker")
@RestController
public class AccountTrackerController {

	private static final Logger LOGGER=LoggerFactory.getLogger(AccountTrackerController.class);
	@Autowired
	private AccountService customerAccountService;
		
	/**
	 * 
	 * @param account
	 * @return
	 */
	@ApiOperation(value = "Create Customer Account")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Created Customer Account"),
	        @ApiResponse(code = 401, message = "You are not authorized to Create Customer Account"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping(path = "/createCustomerAccount")
	public ResponseEntity<Account> createCustomerAccount(@RequestBody Account account) {
		LOGGER.info("craeteCustomerAccount() of AccountTrackerController :: {}", account);
		return new ResponseEntity<>(customerAccountService.createAccount(account), HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param account
	 * @return
	 */
	@ApiOperation(value = "Update Customer Account")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Updated Customer Account"),
	        @ApiResponse(code = 401, message = "You are not authorized to Update Customer Account"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PutMapping(path = "/updateCustomerAccount")
	public ResponseEntity<Account> updateCustomerAccount(@RequestBody Account account) {

		LOGGER.info("updateCustomerAccount() of AccountTrackerController :: {}", account);
		
		return new ResponseEntity<>(customerAccountService.updateAccount(account), HttpStatus.OK);
	}

	
	/**
	 * 
	 * @param customer
	 * @return
	 */
	@ApiOperation(value = "Delete Customer Account")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Delete Customer Account"),
	        @ApiResponse(code = 401, message = "You are not authorized to Delete Customer Account"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@DeleteMapping(path = "/deleteCustomerAccount/{id}")
	public void deleteCustomerAccount(@PathVariable Long id) {

		LOGGER.info("deleteCustomerAccount() of AccountTrackerController :: {}", id);
		
		customerAccountService.deleteCustomerById(id);
	}
	
	
	/**
	 * 
	 * @param customer
	 * @return
	 */
	@ApiOperation(value = "Delete All Customer Accounts")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Deleted All Customers Accounts"),
	        @ApiResponse(code = 401, message = "You are not authorized to Delete Customer Account"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@DeleteMapping(path = "/deleteCustomerAccounts")
	public void deleteCustomerAccounts() {

		LOGGER.info("deleteCustomerAccounts() of AccountTrackerController");
		
		customerAccountService.deleteAllCustomers();
	}
	
	
	/**
	 * 
	 * @return
	 */
	@ApiOperation(value = "get All Customer Account details")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully get All Customer Account details"),
	        @ApiResponse(code = 401, message = "You are not authorized to Get All Customer Account details"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(path = "/getAllCustomerAccounts")
	public ResponseEntity<List<Account>> getAllCustomerAccounts() {

		LOGGER.info("getAllCustomerAccounts() of AccountTrackerController");
		
		return new ResponseEntity<>(customerAccountService.getAllCustomers(), HttpStatus.OK);
	}

	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "get Customer Account details by Using Id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully get Customer Account details"),
	        @ApiResponse(code = 401, message = "You are not authorized to Get Customer Account details"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(path = "/getCustomerAccount/{id}")
	public ResponseEntity<Account> getCustomerAccountById(@PathVariable Long id) {

		LOGGER.info("getCustomerAccountById() of AccountTrackerController :: {}", id);
		
		return new ResponseEntity<>(customerAccountService.getCustomerById(id), HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param id
	 * @param accountHolder
	 * @param balance
	 * @param accountType
	 * @return
	 */
	@ApiOperation(value = "get Customer Account details by Using AccountNUmber or AccountHolder or AccountType")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully get Customer Account details"),
	        @ApiResponse(code = 401, message = "You are not authorized to Get Customer Account details"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(path = {"/getCustomerAccountsByAny", "/getCustomerAccountsByAny/{name}", "/getCustomerAccountsByAny/{type}"})
	public ResponseEntity<List<Account>> getCustomerAccountByAny(
			@RequestParam(name = "name", required = false) Optional<String> accountHolder,
			@RequestParam(name = "type", required = false) Optional<String> accountType) {

		LOGGER.info("getCustomerAccountByAny() of AccountTrackerController");
		
		
		List<Account> list =  customerAccountService.getCustomersByAny(accountHolder, accountType);
		
		if (CommonUtil.isListEmpty(list)) {
			throw new AccountNotFoundException("No Account Found ...");
		}
		
		return new ResponseEntity<>(list, HttpStatus.FOUND);
	}
	
	@ApiOperation(value = "Transfer money from one account to another account")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully transfered the money"),
	        @ApiResponse(code = 401, message = "You are not authorized to transfer money"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PutMapping(path = "/transferFunds")
	public ResponseEntity<TransferBalanceRequest> transferFunds(TransferBalanceRequest transferBalanceRequest) {

		LOGGER.info("transferFunds() of AccountTrackerController :: {}", transferBalanceRequest);
			
		return new ResponseEntity<>(customerAccountService.fundsTransfer(transferBalanceRequest), HttpStatus.FOUND);
	}
	
	
}
