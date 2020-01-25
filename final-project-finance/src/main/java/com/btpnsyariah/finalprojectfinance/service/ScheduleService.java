package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScheduleService {

  void generateSchedule(FinancingAccount financingAccount);
  ResponseEntity<ResponseDao> payment (String trx_id);
  ResponseEntity<ResponseDao> scheduleReport(String accountId);
}
