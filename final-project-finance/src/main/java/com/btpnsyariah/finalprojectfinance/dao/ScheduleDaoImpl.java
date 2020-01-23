package com.btpnsyariah.finalprojectfinance.dao;

import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
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
  public FinancingSchedule findByScheduleId(String scheduleId) {
    return (FinancingSchedule)this.getSessionFactory().createQuery("from FinancingSchedule where id=:scheduleId")
        .setParameter("scheduleId", scheduleId).uniqueResult();
  }

  @Override
  public List<FinancingSchedule> scheduleReport(String accountId) {
    return (List<FinancingSchedule>)this.getSessionFactory().createQuery("from FinancingSchedule where accountId=:accountId")
        .setParameter("accountId", accountId).list();
  }

  @Override
  public void payment(FinancingSchedule financingSchedule, String scheduleId) {
    String uniqueId = UUID.randomUUID().toString();

    FinancingSchedule schedule = (FinancingSchedule) this.getSessionFactory()
        .createQuery("from FinancingSchedule where id=:paymentId")
        .setParameter("paymentId", scheduleId).uniqueResult();
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
