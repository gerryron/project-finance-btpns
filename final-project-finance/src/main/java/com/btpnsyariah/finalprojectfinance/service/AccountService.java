package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;

import java.util.Collection;

public interface AccountService {

  public void createAccount(FinancingAccount financingAccount);

  public Collection<FinancingAccount> getAccountSchedule(String accountId);

}
