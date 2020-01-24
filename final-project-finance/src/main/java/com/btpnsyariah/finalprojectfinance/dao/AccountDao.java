package com.btpnsyariah.finalprojectfinance.dao;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import java.util.List;

public interface AccountDao {

  void createAccount (FinancingAccount financingAccount);
  FinancingAccount findByAccountNo(String accountNo);
  List<FinancingAccount> accountList();

}
