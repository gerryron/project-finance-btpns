package com.btpnsyariah.finalprojectfinance.dao;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;

import java.util.Collection;
import java.util.List;

public interface AccountDao {

  void createAccount (FinancingAccount financingAccount);
  Collection<FinancingAccount> getAccountSchedule (String accountId);
}
