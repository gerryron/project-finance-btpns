package com.btpnsyariah.finalprojectfinance.controller;

import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.service.AccountService;
import com.btpnsyariah.finalprojectfinance.service.DateService;
import com.btpnsyariah.finalprojectfinance.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = {"/financingAccount"})
public class AccountController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private ScheduleService scheduleService;

  private DateService dateService = new DateService();


  @PostMapping(value = "/registration", headers = "Accept= application/json")
  public ResponseEntity<ResponseDao> createFinancingAccount(FinancingAccount financingAccount){
    financingAccount.setDueDate(dateService.addOneYears(financingAccount.getDisbursementDate(), 1));
    accountService.createAccount(financingAccount);
    scheduleService.generateSchedule(financingAccount);
    ResponseDao responseDao = new ResponseDao();
    responseDao.setData(financingAccount);
    responseDao.setCode(201);
    responseDao.setStatus("CREATED");
    responseDao.setMessage("Registrasi akun sukses dibuat !");
    return ResponseEntity.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
  }
}
