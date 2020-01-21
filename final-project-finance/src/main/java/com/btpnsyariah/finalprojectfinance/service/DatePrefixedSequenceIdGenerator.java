package com.btpnsyariah.finalprojectfinance.service;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Properties;

public class DatePrefixedSequenceIdGenerator extends SequenceStyleGenerator {
  public static final String DATE_FORMAT_PARAMETER = "dateFormat";
  public static final String DATE_FORMAT_DEFAULT = "%ty%tm";

  public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
  public static final String NUMBER_FORMAT_DEFAULT = "%03d";

  public static final String SECRET_NUMBER_SEPARATOR_PARAMETER = "secretNumberSeparator";
  public static final String SECRET_NUMBER_SEPARATOR_DEFAULT = "";

  public static final String ACCOUNT_NUMBER_SEPARATOR_PARAMETER = "accountNumberSeparator";
  public static final String ACCOUNT_NUMBER_SEPARATOR_DEFAULT = "";

  private String format;

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
    return String.format(format, LocalDate.now(), super.generate(session,object));
  }

  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
    super.configure(LongType.INSTANCE, params, serviceRegistry);
    String dateFormat = ConfigurationHelper.getString(DATE_FORMAT_PARAMETER, params, DATE_FORMAT_DEFAULT)
        .replace("%", "%1$");
    String numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT)
        .replace("%","%2$");
    String secretNumberSeparator = ConfigurationHelper.getString(SECRET_NUMBER_SEPARATOR_PARAMETER, params, SECRET_NUMBER_SEPARATOR_DEFAULT);
    String accountNumberSeparator = ConfigurationHelper.getString(ACCOUNT_NUMBER_SEPARATOR_PARAMETER, params, ACCOUNT_NUMBER_SEPARATOR_DEFAULT);

    this.format = accountNumberSeparator+dateFormat+secretNumberSeparator+numberFormat;
  }
}