package com.btpnsyariah.finalprojectfinance.dao;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao{

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<FinancingAccount> accountList() {
    return this.getSessionFactory().createQuery("from FinancingAccount").list();
  }

  private Session getSessionFactory(){
    return this.sessionFactory.getCurrentSession();
  }

  @Override
  public void createAccount(FinancingAccount financingAccount) {
    this.getSessionFactory().save(financingAccount);
  }

  @Override
  public FinancingAccount findByAccountId(String accountId) {
    return (FinancingAccount)this.getSessionFactory().createQuery("from FinancingAccount where id=:accountId")
        .setParameter("accountId", accountId).uniqueResult();
  }

}