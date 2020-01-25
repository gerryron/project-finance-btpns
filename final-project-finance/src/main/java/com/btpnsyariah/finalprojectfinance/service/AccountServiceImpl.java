package com.btpnsyariah.finalprojectfinance.service;

import com.btpnsyariah.finalprojectfinance.dao.AccountDao;
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

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private ScheduleService scheduleService;

  @Autowired
  private ScheduleDao scheduleDao;
  private MyGenerator generator = new MyGenerator();

  @Override
  public ResponseEntity<ResponseDao> registration (FinancingAccount financingAccount) {
    financingAccount.setDueDate(generator.addMonth(financingAccount.getDisbursementDate(), 12));
    financingAccount.setAccountNo(UUID.randomUUID().toString());
    accountDao.createAccount(financingAccount);
    scheduleService.generateSchedule(financingAccount);
    ResponseDao responseDao = new ResponseDao(201,"CREATED","Akun selesai dibuat", financingAccount);
    return ResponseEntity.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
  }

  @Override
  public List<FinancingAccount> accountList() {
    return accountDao.accountList();
  }

  @Override
  public ResponseEntity<ResponseDao> findByAccountNo(String accountNo) {
    FinancingAccount financingAccount = accountDao.findByAccountNo(accountNo);
    List<FinancingSchedule> scheduleList = scheduleDao.scheduleReport(financingAccount.getAccountNo());
    financingAccount.setScheduleList(scheduleList);
    ResponseDao responseDao = new ResponseDao(200,"OK","account ditemukan", financingAccount);
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseDao);
  }

}
