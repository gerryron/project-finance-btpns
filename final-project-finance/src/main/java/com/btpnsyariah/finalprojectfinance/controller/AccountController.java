package com.btpnsyariah.finalprojectfinance.controller;

import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import com.btpnsyariah.finalprojectfinance.service.AccountService;
import com.btpnsyariah.finalprojectfinance.service.DateService;
import com.btpnsyariah.finalprojectfinance.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = {"/financingAccount"})
public class AccountController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private ScheduleService scheduleService;

  private DateService dateService = new DateService();


  @PostMapping(value = "/registration")
  public ResponseEntity<ResponseDao> createFinancingAccount(@RequestBody FinancingAccount financingAccount){
    financingAccount.setDueDate(dateService.addMonth(financingAccount.getDisbursementDate(), 12));
    accountService.createAccount(financingAccount);
    scheduleService.generateSchedule(financingAccount);
    ResponseDao responseDao = new ResponseDao(200,"OK","OK",financingAccount);
    return ResponseEntity.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
  }

  @GetMapping(value = "/{accountId}", headers = "Accept=application/json")
  public ResponseEntity<ResponseDao> findByAccountId(@PathVariable(value = "accountId") String accountId){
    FinancingAccount financingAccount = accountService.findByAccountId(accountId);
    ResponseDao responseDao = new ResponseDao();
    responseDao.setData(financingAccount);
    responseDao.setCode(200);
    responseDao.setStatus("OK");
    responseDao.setMessage("Account Ditemukan");
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
  }

  @GetMapping(value = "/list", headers = "Accept=application/json")
  public ResponseEntity<ResponseDao> accountList(){
    List<FinancingAccount> accounts = accountService.accountList();
    ResponseDao responseDao = new ResponseDao();
    responseDao.setData(accounts);
    responseDao.setCode(200);
    responseDao.setStatus("OK");
    responseDao.setMessage("List Akun Peminjaman");
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
  }


}
