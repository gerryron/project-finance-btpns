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
  
  @PutMapping(value="/payment/{paymentId}",headers = "Accept= application/json")
  public ResponseEntity<ResponseDao> payment(FinancingSchedule financingSchedule, @PathVariable String paymentId) {
    ResponseDao responseDao = new ResponseDao();
    FinancingSchedule scheduleData = scheduleService.findByScheduleId(paymentId);
    if (scheduleData == null) {
      responseDao.setData(scheduleData);
      responseDao.setCode(404);
      responseDao.setStatus("NOT_FOUND");
      responseDao.setMessage("paymentId tidak ditemukan");
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .contentType(MediaType.APPLICATION_JSON)
          .body(responseDao);
    }else {
      if (scheduleData.isPaid() == false) {
        scheduleService.payment(financingSchedule, paymentId);
        FinancingSchedule scheduleReport = scheduleService.findByScheduleId(paymentId);
        responseDao.setData(scheduleReport);
        responseDao.setCode(202);
        responseDao.setStatus("ACCEPTED");
        responseDao.setMessage("Pembayaran sukses dilakukan");
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(responseDao);
      } else {
        responseDao.setData(scheduleData);
        responseDao.setCode(409);
        responseDao.setStatus("Already_Exists");
        responseDao.setMessage("Tagihan sudah lunas");
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .contentType(MediaType.APPLICATION_JSON)
            .body(responseDao);
      }
    }
  }

  @GetMapping(value = "/list/{accountId}")
  public ResponseEntity<ResponseDao> scheduleReport(@PathVariable String accountId){
    List<FinancingSchedule> financingSchedules = scheduleService.scheduleReport(accountId);
    ResponseDao responseDao = new ResponseDao();
    responseDao.setData(financingSchedules);
    responseDao.setCode(200);
    responseDao.setStatus("OK");
    responseDao.setMessage("OK");
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
  }
}
