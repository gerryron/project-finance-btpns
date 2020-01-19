package com.btpnsyariah.finalprojectfinance.entitty;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Financing_Schedule")
public class FinancingSchedule {

  private int id;
  private String trxId;
  private int accountId;
  private int installmentNo;
  private double principal;
  private double profitShare;
  private Date scheduleDate;
  private Date paymentDate;
  private boolean paid = false;
  private FinancingAccount financingAccount;

  public FinancingSchedule() {
  }

  @Id
  @GenericGenerator(name = "gen", strategy = "increment")
  @GeneratedValue
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "trx_id")
  public String getTrxId() {
    return trxId;
  }

  public void setTrxId(String trxId) {
    this.trxId = trxId;
  }

  @Column(name = "account_id")
  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  @Column(name = "installment_no")
  public int getInstallmentNo() {
    return installmentNo;
  }

  public void setInstallmentNo(int installmentNo) {
    this.installmentNo = installmentNo;
  }

  public double getPrincipal() {
    return principal;
  }

  public void setPrincipal(double principal) {
    this.principal = principal;
  }

  @Column(name = "profit_share")
  public double getProfitShare() {
    return profitShare;
  }

  public void setProfitShare(double profitShare) {
    this.profitShare = profitShare;
  }

  @Column(name = "schedule_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public Date getScheduleDate() {
    return scheduleDate;
  }

  public void setScheduleDate(Date scheduleDate) {
    this.scheduleDate = scheduleDate;
  }

  @Column(name = "payment_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public Date getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }

  public boolean isPaid() {
    return paid;
  }

  public void setPaid(boolean paid) {
    this.paid = paid;
  }

  @ManyToOne
  @JoinColumn(name = "accountSchedule_Id")
  public FinancingAccount getFinancingAccount() {
    return financingAccount;
  }

  public void setFinancingAccount(FinancingAccount financingAccount) {
    this.financingAccount = financingAccount;
  }
}
