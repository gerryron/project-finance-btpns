package com.btpnsyariah.finalprojectfinance.dao;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Repository
@Transactional
public class ScheduleDaoImpl implements ScheduleDao{

  @Autowired
  private SessionFactory sessionFactory;

  private Session getSessionFactory(){
    return this.sessionFactory.getCurrentSession();
  }

  @Override
  public void payment(FinancingSchedule financingSchedule, int paymentId) {
    String uniqueId = UUID.randomUUID().toString();

    FinancingSchedule schedule = (FinancingSchedule) this.getSessionFactory()
        .createQuery("from FinancingSchedule where id=:payId")
        .setParameter("payId", paymentId).uniqueResult();
    schedule.setPaymentDate(new Date());
    schedule.setPaid(true);
    schedule.setTrxId(uniqueId);
    this.getSessionFactory().saveOrUpdate(schedule);
  }

  @Override
  public void createSchedule(FinancingSchedule financingSchedule) {
    this.getSessionFactory().save(financingSchedule);
  }
}
