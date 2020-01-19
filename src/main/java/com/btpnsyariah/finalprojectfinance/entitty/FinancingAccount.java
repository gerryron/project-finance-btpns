package com.btpnsyariah.finalprojectfinance.entitty;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "Financing_Account")
public class FinancingAccount {

  private int id;
  private int accountNo;
  private int customerId;
  private double plafon;
  private int tenor = 12;
  private double margin = 0.3;
  private Date disbursementDate;
  private Date dueDate;

  private Collection<FinancingSchedule> schedules;

  public FinancingAccount() {
  }


  @Id
  @GenericGenerator(name = "gen",strategy = "increment")
  @GeneratedValue
  public int getId() {
    return id;
  }

  public void setId(int id) {
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
  public Date getDisbursementDate() {
    return disbursementDate;
  }

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

  @OneToMany(mappedBy = "financingAccount", cascade = CascadeType.ALL)
  public Collection<FinancingSchedule> getSchedules() {
    return schedules;
  }

  public void setSchedules(Collection<FinancingSchedule> schedules) {
    this.schedules = schedules;
  }
}
