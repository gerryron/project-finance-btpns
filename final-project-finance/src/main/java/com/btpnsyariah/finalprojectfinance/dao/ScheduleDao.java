package com.btpnsyariah.finalprojectfinance.dao;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;

import java.util.List;

public interface ScheduleDao {

  void createSchedule (FinancingSchedule financingSchedule);
  void payment(FinancingSchedule financingSchedule, String scheduleId);
  FinancingSchedule findByScheduleId(String scheduleId);
  List<FinancingSchedule> scheduleReport(String accountId);
}
