package com.btpnsyariah.finalprojectfinance.entitty;

import com.btpnsyariah.finalprojectfinance.service.DatePrefixedSequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Financing_Account")
public class FinancingAccount {

  private String id;
  private int accountNo;
  private int customerId;
  private double plafon;
  private int tenor = 12;
  private double margin = 0.3;
  private Date disbursementDate;
  private Date dueDate;

  public FinancingAccount() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq")
  @GenericGenerator(
      name = "acc_seq",
      strategy = "com.btpnsyariah.finalprojectfinance.service.DatePrefixedSequenceIdGenerator",
      parameters = {
          @Parameter(name = DatePrefixedSequenceIdGenerator.ACCOUNT_NUMBER_SEPARATOR_PARAMETER, value = "9"),
          @Parameter(name = DatePrefixedSequenceIdGenerator.NUMBER_FORMAT_DEFAULT, value = "%03d"),
          @Parameter(name = DatePrefixedSequenceIdGenerator.SECRET_NUMBER_SEPARATOR_PARAMETER, value = "1140")
      })
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column(name = "account_no")
  public int getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(int accountNo) {
    this.accountNo = accountNo;
  }

  @Column(name = "customer_id")
  @NotNull(message = "Customer id wajib diisi")
  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public double getPlafon() {
    return plafon;
  }

  public void setPlafon(double plafon) {
    this.plafon = plafon;
  }

  public int getTenor() {
    return tenor;
  }

  public void setTenor(int tenor) {
    this.tenor = tenor;
  }

  public double getMargin() {
    return margin;
  }

  public void setMargin(double margin) {
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

}
