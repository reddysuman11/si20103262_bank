package com.si20103262.bank.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.si20103262.bank.entity.AccountEntity;
import com.si20103262.bank.model.Account;

public class AccountUtil {

	public static AccountEntity modelToEntity(Account customer) {
		AccountEntity customerEntity = null;
		
		if (customer != null) {
			customerEntity = new AccountEntity();
			BeanUtils.copyProperties( customer, customerEntity, BeanUtil.getNullPropertiesString(customer));
		}
		
		return customerEntity;
	}
	
	public static Account entityToModel(AccountEntity customerEntity) {
		
		Account customer = null;
		
		if (customerEntity != null) {
			customer = new Account();
			BeanUtils.copyProperties(customerEntity, customer, BeanUtil.getNullPropertiesString(customerEntity));
		}
		
		return customer;
	}

	public static List<AccountEntity> modelToEntity(List<Account> customers) {
		
		if(Boolean.TRUE.equals(CommonUtil.isListEmpty(customers))) {
			return Collections.emptyList();
		}
		
		return customers.stream().filter(CommonUtil::isNotNull).map(AccountUtil::modelToEntity).collect(Collectors.toList());
				
	}
	
	public static List<Account> entityToModel(List<AccountEntity> customerEntities) {
		
		if(Boolean.TRUE.equals(CommonUtil.isListEmpty(customerEntities))) {
			return Collections.emptyList();
		}
		
		return customerEntities.stream().filter(CommonUtil::isNotNull).map(AccountUtil::entityToModel).collect(Collectors.toList());
				
	}

}
