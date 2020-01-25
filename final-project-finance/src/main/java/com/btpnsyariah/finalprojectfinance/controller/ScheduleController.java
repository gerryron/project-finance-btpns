package com.btpnsyariah.finalprojectfinance.controller;

import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = {"/financing_schedule"})
public class ScheduleController {

  @Autowired
  private ScheduleService scheduleService;

  @PutMapping(value="/payment/{trx_id}",headers = "Accept= application/json")
  public ResponseEntity<ResponseDao> payment(@PathVariable String trx_id) {
    return scheduleService.payment(trx_id);
  }

  @GetMapping(value = "/{accountId}")
  public ResponseEntity<ResponseDao> scheduleReport(@PathVariable String accountId){
    return scheduleService.scheduleReport(accountId);
  }
}
