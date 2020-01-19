package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;

import java.util.Collection;
import java.util.List;

public interface AccountService {

  public void createAccount(FinancingAccount financingAccount);
  public Collection<FinancingAccount> getCustomerSchedule(int customerId);
}
