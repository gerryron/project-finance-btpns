package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;

import java.util.List;

public interface ScheduleService {

  void generateSchedule(FinancingAccount financingAccount);
  void payment(FinancingSchedule financingSchedule, String scheduleId);
  FinancingSchedule findByScheduleId(String scheduleId);
  List<FinancingSchedule> scheduleReport(String accountId);
}
