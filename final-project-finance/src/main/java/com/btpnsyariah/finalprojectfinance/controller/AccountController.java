package com.btpnsyariah.finalprojectfinance.controller;

import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = {"/financing_account"})
public class AccountController {

  @Autowired
  private AccountService accountService;

  @PostMapping(value = "/registration")
  public ResponseEntity<ResponseDao> createFinancingAccount(@RequestBody FinancingAccount financingAccount){
    return accountService.registration(financingAccount);
  }

  @GetMapping(value = "/{accountNo}", headers = "Accept=application/json")
  public ResponseEntity<ResponseDao> findByAccountNo(@PathVariable(value = "accountNo") String accountNo){
    return accountService.findByAccountNo(accountNo);
  }

  @GetMapping(value = "/list", headers = "Accept=application/json")
  public ResponseEntity<ResponseDao> accountList(){
    List<FinancingAccount> accounts = accountService.accountList();
    ResponseDao responseDao = new ResponseDao(200, "OK","List akun peminjaman", accounts);
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
  }


}
