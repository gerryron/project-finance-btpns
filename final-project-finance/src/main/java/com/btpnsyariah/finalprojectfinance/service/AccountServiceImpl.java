package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.dao.AccountDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountDao accountDao;

  @Override
  public void createAccount(FinancingAccount financingAccount) {
    accountDao.createAccount(financingAccount);
  }

  @Override
  public Collection<FinancingAccount> getAccountSchedule(String accountId) {
    return accountDao.getAccountSchedule(accountId);
  }
}
