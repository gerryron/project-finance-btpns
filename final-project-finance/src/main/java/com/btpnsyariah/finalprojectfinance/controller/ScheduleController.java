package com.btpnsyariah.finalprojectfinance.controller;

import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import com.btpnsyariah.finalprojectfinance.service.AccountService;
import com.btpnsyariah.finalprojectfinance.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/financingSchedule"})
public class ScheduleController {

  @Autowired
  private ScheduleService scheduleService;

  @PutMapping(value="/payment/{trx_id}",headers = "Accept= application/json")
  public ResponseEntity<ResponseDao> payment(@PathVariable String trx_id) {
    FinancingSchedule scheduleData = scheduleService.findByTrxId(trx_id);
    if (scheduleData == null) {
      ResponseDao responseDao = new ResponseDao(404,"NOT_FOUND","PaymentId tidak ditemukan", scheduleData);
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .contentType(MediaType.APPLICATION_JSON)
          .body(responseDao);
    }else {
      if (!scheduleData.isPaid()) {
        scheduleService.payment(trx_id);
        FinancingSchedule scheduleReport = scheduleService.findByTrxId(trx_id);
        ResponseDao responseDao = new ResponseDao(202, "ACCEPTED", "Pembayaran sukses dilakukan", scheduleReport);
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(responseDao);
      } else {
        ResponseDao responseDao = new ResponseDao(409,"ALREADY_EXIST","Tagihan sudah dibayar", scheduleData);
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .contentType(MediaType.APPLICATION_JSON)
            .body(responseDao);
      }
    }
  }

  @GetMapping(value = "/{accountId}")
  public ResponseEntity<ResponseDao> scheduleReport(@PathVariable String accountId){
    List<FinancingSchedule> financingSchedules = scheduleService.scheduleReport(accountId);
    ResponseDao responseDao = new ResponseDao(200, "OK", "Schedule ditemukan", financingSchedules);
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
  }
}
