package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;

import java.util.Collection;
import java.util.List;

public interface AccountService {

  void createAccount(FinancingAccount financingAccount);
  FinancingAccount findByAccountNo(String accountNo);
  List<FinancingAccount> accountList();

}
