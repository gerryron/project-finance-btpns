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


  @GetMapping(value = "/cekSchedule/{accountId}",produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<FinancingAccount> AccountSchedule (@PathVariable(value = "accountId") String accountId){
    Collection<FinancingAccount> financingAccounts = accountService.getAccountSchedule(accountId);
    System.out.println(financingAccounts);
    return financingAccounts;
  }

  @PutMapping(value="/payment",headers = "Accept= application/json")
  public ResponseEntity<FinancingSchedule> payment(FinancingSchedule financingSchedule,
                                                   @RequestParam(value = "scheduleId",defaultValue = "")String scheduleId){

    FinancingSchedule scheduleReport = scheduleService.findByScheduleId(scheduleId);
    if (scheduleReport.isPaid()==false){
      scheduleService.payment(financingSchedule,scheduleId);
      FinancingSchedule scheduleData = scheduleService.findByScheduleId(scheduleId);
      if(financingSchedule.equals(null)) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(scheduleData);
      }else {
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(scheduleData);
      }
    }
    return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
  }
}
