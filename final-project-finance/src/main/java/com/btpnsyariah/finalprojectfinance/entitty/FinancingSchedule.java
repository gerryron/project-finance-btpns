package com.btpnsyariah.finalprojectfinance.entitty;

import com.btpnsyariah.finalprojectfinance.service.DatePrefixedSequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Financing_Schedule")
public class FinancingSchedule {

  private String id;
  private String trxId;
  private String accountId;
  private int installmentNo;
  private double principal;
  private double profitShare;
  private Date scheduleDate;
  private Date paymentDate;
  private boolean paid = false;

  public FinancingSchedule() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_seq")
  @GenericGenerator(
      name = "schedule_seq",
      strategy = "com.btpnsyariah.finalprojectfinance.service.DatePrefixedSequenceIdGenerator",
      parameters = {
          @Parameter(name = DatePrefixedSequenceIdGenerator.NUMBER_FORMAT_DEFAULT, value = "%04d"),
          @Parameter(name = DatePrefixedSequenceIdGenerator.SECRET_NUMBER_SEPARATOR_PARAMETER, value = "1140")
      }
  )
  public String getId() {
    return id;
  }

  public void setId(String id) {
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

}
