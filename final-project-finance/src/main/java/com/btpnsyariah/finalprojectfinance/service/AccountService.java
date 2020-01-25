package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface AccountService {

  ResponseEntity<ResponseDao> registration(FinancingAccount financingAccount);
  ResponseEntity<ResponseDao> findByAccountNo(String accountNo);
  List<FinancingAccount> accountList();

}
