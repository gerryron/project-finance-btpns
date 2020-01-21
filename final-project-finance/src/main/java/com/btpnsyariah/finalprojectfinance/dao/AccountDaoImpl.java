package com.btpnsyariah.finalprojectfinance.dao;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao{

  @Autowired
  private SessionFactory sessionFactory;

  private Session getSessionFactory(){
    return this.sessionFactory.getCurrentSession();
  }

  @Override
  public void createAccount(FinancingAccount financingAccount) {
    this.getSessionFactory().save(financingAccount);
  }

  @Override
  public Collection<FinancingAccount> getAccountSchedule(String accountId) {
    return (Collection<FinancingAccount>)this.getSessionFactory().createQuery("select fa from FinancingAccount fa join fa.schedules sch where sch.financingAccount.id= :ScheduleId").setParameter("ScheduleId", accountId).list();
  }
}