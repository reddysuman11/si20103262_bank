package com.si20103262.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.si20103262.bank.entity.AccountEntity;

@Repository
public interface AccountTrackerRepository extends CrudRepository<AccountEntity, Long> {

	@Query("SELECT a from com.si20103262.bank.entity.AccountEntity a where a.accountHolder=:accountHolder")
	public List<AccountEntity> findByAccountHolder(@Param("accountHolder")String accountHolder);
	

	@Query("SELECT a from com.si20103262.bank.entity.AccountEntity a where a.accountType=:accountType")
	public List<AccountEntity> findByAccountType(@Param("accountType") String accountType);
	
}