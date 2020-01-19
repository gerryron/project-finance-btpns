package com.btpnsyariah.finalprojectfinance.controller;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import com.btpnsyariah.finalprojectfinance.service.AccountService;
import com.btpnsyariah.finalprojectfinance.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = {"/financingSchedule"})
public class ScheduleController {

  @Autowired
  private ScheduleService scheduleService;

  @Autowired
  private AccountService accountService;


  @GetMapping(value = "/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<FinancingAccount> getAccountSchedule (@PathVariable(value = "customerId") int customerId){
    Collection<FinancingAccount> financingAccounts = accountService.getCustomerSchedule(customerId);
    System.out.println(financingAccounts);
    return financingAccounts;
  }

  @PutMapping(value="/payment",headers = "Accept= application/json")
  public ResponseEntity<Void> payment(FinancingSchedule financingSchedule,
                                                   @RequestParam(value = "paymentId",defaultValue = "")int paymentId){
    scheduleService.payment(financingSchedule,paymentId);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }
}
