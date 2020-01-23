package com.btpnsyariah.finalprojectfinance.entitty;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Entity
@Table(name = "Financing_Account")
public class FinancingAccount {

  private long id;
  private String accountNo;
  private String customerId;
  private BigDecimal plafon;
  private int tenor = 12;
  private BigDecimal margin = new BigDecimal("0.3");
  private Date disbursementDate;
  private Date dueDate;
//  private List<FinancingSchedule> financingScheduleList;

  public FinancingAccount() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(name = "account_no")
  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  @Column(name = "customer_id")
  @NotNull(message = "Customer id wajib diisi")
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public BigDecimal getPlafon() {
    return plafon;
  }

  public void setPlafon(BigDecimal plafon) {
    this.plafon = plafon;
  }

  public int getTenor() {
    return tenor;
  }

  public void setTenor(int tenor) {
    this.tenor = tenor;
  }

  public BigDecimal getMargin() {
    return margin;
  }

  public void setMargin(BigDecimal margin) {
    this.margin = margin;
  }

  @Column(name = "disbursement_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @FutureOrPresent(message = "Masukkan tanggal yang valid")
  public Date getDisbursementDate() {
    return disbursementDate;
  }

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public void setDisbursementDate(Date disbursementDate) {
    this.disbursementDate = disbursementDate;
  }

  @Column(name = "due_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

//  public void setFinancingScheduleList(List<FinancingSchedule> financingScheduleList) {
//    this.financingScheduleList = financingScheduleList;
//  }
}
