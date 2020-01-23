package com.btpnsyariah.finalprojectfinance.controller;

import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.service.AccountService;
import com.btpnsyariah.finalprojectfinance.generator.MyGenerator;
import com.btpnsyariah.finalprojectfinance.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = {"/financingAccount"})
public class AccountController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private ScheduleService scheduleService;

  private MyGenerator generator = new MyGenerator();


  @PostMapping(value = "/registration")
  public ResponseEntity<ResponseDao> createFinancingAccount(@RequestBody FinancingAccount financingAccount){
    financingAccount.setDueDate(generator.addMonth(financingAccount.getDisbursementDate(), 12));
    financingAccount.setAccountNo(UUID.randomUUID().toString());
    accountService.createAccount(financingAccount);
    scheduleService.generateSchedule(financingAccount);
//    List<FinancingSchedule> financingScheduleList=scheduleService.scheduleReport(financingAccount.getAccountNo());
//    financingAccount.setFinancingScheduleList(financingScheduleList);
    ResponseDao responseDao = new ResponseDao(201,"CREATED","Akun selesai dibuat", financingAccount);
    return ResponseEntity.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
  }

  @GetMapping(value = "/{accountNo}", headers = "Accept=application/json")
  public ResponseEntity<ResponseDao> findByAccountId(@PathVariable(value = "accountNo") String accountNo){
    FinancingAccount financingAccount = accountService.findByAccountNo(accountNo);
    ResponseDao responseDao = new ResponseDao(200,"OK","account ditemukan", financingAccount);
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
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
