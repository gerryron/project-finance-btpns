package com.btpnsyariah.finalprojectfinance.dao;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;

public interface ScheduleDao {

  void createSchedule (FinancingSchedule financingSchedule);
  void payment(FinancingSchedule financingSchedule, int paymentId);
}
