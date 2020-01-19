package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.dao.ScheduleDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;


@Service
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired
  private ScheduleDao scheduleDao;
  private DateService dateService = new DateService();

  @Override
  public void payment(FinancingSchedule financingSchedule, int paymentId) {
    scheduleDao.payment(financingSchedule, paymentId);
  }

  @Override
  public void generateSchedule(FinancingAccount financingAccount){
    double margin= financingAccount.getPlafon()*financingAccount.getMargin();
    Date firstDate = financingAccount.getDisbursementDate();

    for(int i=0; i< financingAccount.getTenor(); i++) {
      Date addMonth = dateService.addMonth(firstDate, i);

      FinancingSchedule fs = new FinancingSchedule();
      fs.setAccountId(financingAccount.getId());
      fs.setPrincipal(financingAccount.getPlafon()/financingAccount.getTenor());
      fs.setProfitShare((financingAccount.getPlafon() + margin)/ financingAccount.getTenor());
      fs.setInstallmentNo(i+1);
      fs.setScheduleDate(addMonth);

      scheduleDao.createSchedule(fs);
    }
  }
}
