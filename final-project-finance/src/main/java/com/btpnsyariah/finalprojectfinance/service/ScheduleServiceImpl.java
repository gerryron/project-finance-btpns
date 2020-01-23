package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.dao.ScheduleDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import com.btpnsyariah.finalprojectfinance.generator.DateGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;


@Service
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired
  private ScheduleDao scheduleDao;
  private DateGenerator dateGenerator = new DateGenerator();

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
    BigDecimal tenor = new BigDecimal(financingAccount.getTenor());
    BigDecimal margin = financingAccount.getPlafon()
        .multiply(financingAccount.getMargin()).setScale(0,RoundingMode.HALF_UP);
    BigDecimal plafonPlusMargin = financingAccount.getPlafon()
        .add(margin).setScale(0,RoundingMode.HALF_UP);
    BigDecimal installments = plafonPlusMargin
        .divide(tenor, 0, RoundingMode.HALF_UP);
    BigDecimal principal = financingAccount.getPlafon()
        .divide(plafonPlusMargin, 10, RoundingMode.HALF_UP)
        .multiply(installments).setScale(0, RoundingMode.HALF_UP);
    BigDecimal profitShare = margin
        .divide(plafonPlusMargin, 10, RoundingMode.HALF_UP)
        .multiply(installments).setScale(0, RoundingMode.HALF_UP);

    Date firstDate = financingAccount.getDisbursementDate();

    for(int gen=1; gen<=financingAccount.getTenor(); gen++) {
      Date getSchedule = dateGenerator.addMonth(firstDate, gen);
      FinancingSchedule fs = new FinancingSchedule();
      fs.setAccountId(financingAccount.getAccountNo());
      fs.setPrincipal(principal);
      fs.setProfitShare(profitShare);
      fs.setInstallmentNo(gen);
      fs.setScheduleDate(getSchedule);
      scheduleDao.createSchedule(fs);
    }
  }
}
