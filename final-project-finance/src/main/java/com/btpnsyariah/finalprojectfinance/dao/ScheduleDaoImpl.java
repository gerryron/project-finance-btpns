package com.btpnsyariah.finalprojectfinance.dao;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class ScheduleDaoImpl implements ScheduleDao{

  @Autowired
  private SessionFactory sessionFactory;

  private Session getSessionFactory(){
    return this.sessionFactory.getCurrentSession();
  }

  @Override
  public FinancingSchedule findByTrxId(String trx_id) {
    return (FinancingSchedule)this.getSessionFactory().createQuery("from FinancingSchedule where trxId=:trx_id")
        .setParameter("trx_id", trx_id).uniqueResult();
  }

  @Override
  public List<FinancingSchedule> scheduleReport(String accountId) {
    return (List<FinancingSchedule>)this.getSessionFactory().createQuery("from FinancingSchedule where accountId=:accountId")
        .setParameter("accountId", accountId).list();
  }

  @Override
  public void payment(String trx_id) {
    FinancingSchedule schedule = (FinancingSchedule)this.getSessionFactory()
        .createQuery("from FinancingSchedule where trxId=:trx_id")
        .setParameter("trx_id", trx_id).uniqueResult();
    schedule.setPaymentDate(new Date());
    schedule.setPaid(true);
    if(schedule.getPaymentDate().before(schedule.getScheduleDate())){
      schedule.setPaymentStatement("ontime");
    }else {
      schedule.setPaymentStatement("late");
    }
    schedule.setPaymentStatement(schedule.getPaymentStatement());
    this.getSessionFactory().saveOrUpdate(schedule);
  }

  @Override
  public void createSchedule(FinancingSchedule financingSchedule) {
    this.getSessionFactory().save(financingSchedule);
  }
}
