package com.btpnsyariah.finalprojectfinance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.btpnsyariah.finalprojectfinance.controller.AccountController;
import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import com.btpnsyariah.finalprojectfinance.service.AccountService;
import com.btpnsyariah.finalprojectfinance.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

  @InjectMocks
  AccountController accountController;

  @Mock
  AccountService accountService;

  @Mock
  ScheduleService scheduleService;

  FinancingAccount dummyAccount = new FinancingAccount();
  FinancingSchedule dummySchedule = new FinancingSchedule();

  @Test
  public void registrationTest(){
    dummyAccount.setAccountNo(112233);
    dummyAccount.setCustomerId(223344);
    dummyAccount.setPlafon(135000000);
    dummyAccount.setDisbursementDate(new Date());

    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    ResponseEntity<ResponseDao> registration = accountController.createFinancingAccount(dummyAccount);
    assertThat(registration.getStatusCodeValue()).isEqualTo(201);
  }

}
