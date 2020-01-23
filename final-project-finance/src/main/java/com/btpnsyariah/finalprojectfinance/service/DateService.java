package com.btpnsyariah.finalprojectfinance.service;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateService {

  public DateService() {
  }

  public Date addMonth(Date currentDate, int amount){
    LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    localDateTime = localDateTime.plusMonths(amount);

    Date currentDatePlusMonth = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return currentDatePlusMonth;
  }

}
