package com.btpnsyariah.finalprojectfinance.generator;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class MyGenerator {

  public MyGenerator() {
  }

  public Date addMonth(Date currentDate, int amount){
    LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    localDateTime = localDateTime.plusMonths(amount).plusHours(17);

    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public String surrogateKey(long id){
    final String alphabet = "0123456789ABCDEF";
    final int N = alphabet.length();

    Random r = new Random();
    String randomChar = "";
    for (int i = 0; i < 6; i++) {
      randomChar += String.valueOf(alphabet.charAt(r.nextInt(N)));
    }
    return String.format("%tY%tm%04d"+"@"+randomChar , new Date(), new Date(), id);
  }
}
