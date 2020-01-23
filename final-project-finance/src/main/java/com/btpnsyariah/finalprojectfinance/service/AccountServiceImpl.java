package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.dao.AccountDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountDao accountDao;

  @Override
  public void createAccount(FinancingAccount financingAccount) {
    accountDao.createAccount(financingAccount);
  }

  @Override
  public List<FinancingAccount> accountList() {
    return accountDao.accountList();
  }

  @Override
  public FinancingAccount findByAccountId(String accountId) {
    return accountDao.findByAccountId(accountId);
  }

}
