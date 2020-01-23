package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;

import java.util.List;

public interface ScheduleService {

  void generateSchedule(FinancingAccount financingAccount);
  void payment(String trx_id);
  FinancingSchedule findByTrxId(String trx_id);
  List<FinancingSchedule> scheduleReport(String accountId);
}
