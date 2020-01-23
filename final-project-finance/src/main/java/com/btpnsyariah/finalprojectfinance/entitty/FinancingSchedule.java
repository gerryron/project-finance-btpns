package com.btpnsyariah.finalprojectfinance.entitty;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Financing_Schedule")
public class FinancingSchedule {

  private long id;
  private String trxId;
  private String accountId;
  private int installmentNo;
  private BigDecimal principal;
  private BigDecimal profitShare;
  private Date scheduleDate;
  private Date paymentDate;
  private boolean paid = false;

  public FinancingSchedule() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long getId() {
    return id;
  }

  public void setId(long id) {
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
  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  @Column(name = "installment_no")
  public int getInstallmentNo() {
    return installmentNo;
  }

  public void setInstallmentNo(int installmentNo) {
    this.installmentNo = installmentNo;
  }

  public BigDecimal getPrincipal() {
    return principal;
  }

  public void setPrincipal(BigDecimal principal) {
    this.principal = principal;
  }

  @Column(name = "profit_share")
  public BigDecimal getProfitShare() {
    return profitShare;
  }

  public void setProfitShare(BigDecimal profitShare) {
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

}
