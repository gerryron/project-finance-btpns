package com.btpnsyariah.finalprojectfinance.generator;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateGenerator {

  public DateGenerator() {
  }

  public Date addMonth(Date currentDate, int amount){
    LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    localDateTime = localDateTime.plusMonths(amount);

    Date currentDatePlusMonth = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return currentDatePlusMonth;
  }

}
