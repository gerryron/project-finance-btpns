package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.dao.ScheduleDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;


@Service
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired
  private ScheduleDao scheduleDao;
  private DateService dateService = new DateService();

  @Override
  public void payment(FinancingSchedule financingSchedule, String scheduleId) {
    scheduleDao.payment(financingSchedule, scheduleId);
  }

  @Override
  public List<FinancingSchedule> scheduleReport(String accountId) {
    return scheduleDao.scheduleReport(accountId);
  }

  @Override
  public FinancingSchedule findByScheduleId(String scheduleId) {
    return scheduleDao.findByScheduleId(scheduleId);
  }

  @Override
  public void generateSchedule(FinancingAccount financingAccount){
    double margin= financingAccount.getPlafon()*financingAccount.getMargin();
    Date firstDate = financingAccount.getDisbursementDate();

    for(int i=0; i< financingAccount.getTenor(); i++) {
      Date getSchedule = dateService.addMonth(firstDate, i+1);

      FinancingSchedule fs = new FinancingSchedule();
      fs.setAccountId(financingAccount.getId());
      fs.setPrincipal(financingAccount.getPlafon()/financingAccount.getTenor());
      fs.setProfitShare((financingAccount.getPlafon() + margin)/ financingAccount.getTenor());
      fs.setInstallmentNo(i+1);
      fs.setScheduleDate(getSchedule);

      scheduleDao.createSchedule(fs);
    }
  }
}
