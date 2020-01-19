package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;

public interface ScheduleService {

  public void generateSchedule(FinancingAccount financingAccount);
  public void payment(FinancingSchedule financingSchedule, int paymentId);
}
