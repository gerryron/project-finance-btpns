package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.dao.ScheduleDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import com.btpnsyariah.finalprojectfinance.generator.MyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;


@Service
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired
  private ScheduleDao scheduleDao;
  private MyGenerator myGenerator = new MyGenerator();

  @Override
  public ResponseEntity<ResponseDao> scheduleReport(String accountId) {
    List<FinancingSchedule> financingSchedules = scheduleDao.scheduleReport(accountId);
    ResponseDao responseDao = new ResponseDao(200, "OK", "Schedule ditemukan", financingSchedules);
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
  }

  @Override
  public ResponseEntity<ResponseDao> payment (String trx_id) {
    FinancingSchedule scheduleData = scheduleDao.findByTrxId(trx_id);
    if (scheduleData == null) {
      ResponseDao responseDao = new ResponseDao(404,"NOT_FOUND","PaymentId tidak ditemukan", scheduleData);
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .contentType(MediaType.APPLICATION_JSON)
          .body(responseDao);
    }else {
      if (!scheduleData.isPaid()) {
        scheduleDao.payment(trx_id);
        FinancingSchedule scheduleReport = scheduleDao.findByTrxId(trx_id);
        ResponseDao responseDao = new ResponseDao(202, "ACCEPTED", "Pembayaran sukses dilakukan", scheduleReport);
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(responseDao);
      } else {
        ResponseDao responseDao = new ResponseDao(409, "ALREADY_EXIST", "Tagihan sudah dibayar", scheduleData);
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .contentType(MediaType.APPLICATION_JSON)
            .body(responseDao);
      }
    }
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
      FinancingSchedule fs = new FinancingSchedule();
      Date getSchedule = myGenerator.addMonth(firstDate, gen);
      String surrKey = myGenerator.surrogateKey(financingAccount.getId());

      fs.setTrxId(surrKey);
      fs.setAccountId(financingAccount.getAccountNo());
      fs.setPrincipal(principal);
      fs.setProfitShare(profitShare);
      fs.setInstallmentNo(gen);
      fs.setScheduleDate(getSchedule);
      fs.setFinancingAccount(financingAccount);
      financingAccount.getScheduleList().add(fs);
      scheduleDao.createSchedule(fs);
    }

  }
}
