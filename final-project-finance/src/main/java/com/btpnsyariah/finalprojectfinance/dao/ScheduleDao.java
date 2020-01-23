package com.btpnsyariah.finalprojectfinance.dao;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;

import java.util.List;

public interface ScheduleDao {

  void createSchedule (FinancingSchedule financingSchedule);
  void payment(String trx_id);
  FinancingSchedule findByTrxId(String trx_id);
  List<FinancingSchedule> scheduleReport(String accountId);
}
