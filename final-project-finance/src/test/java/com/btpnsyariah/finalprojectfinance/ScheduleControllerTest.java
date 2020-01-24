package com.btpnsyariah.finalprojectfinance;

import com.btpnsyariah.finalprojectfinance.controller.ScheduleController;
import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.dao.ScheduleDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScheduleControllerTest {

  @InjectMocks
  ScheduleController scheduleController;

  @Mock
  ScheduleService scheduleService;

  @Mock
  ScheduleDao scheduleDao;

  FinancingSchedule dummySchedule = new FinancingSchedule();

  @Test
  public void paymentIfPaidIsFalse(){
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    dummySchedule.setAccountId("1100");
    dummySchedule.setTrxId("123456");
    // dummySchedule.setPaid(false);  <- Default
    when(scheduleService.findByTrxId(dummySchedule.getTrxId())).thenReturn(dummySchedule);

    ResponseEntity<ResponseDao> paymentResponseEntity = scheduleController.payment("123456");
    assertThat(paymentResponseEntity.getStatusCodeValue()).isEqualTo(202);
  }

  @Test
  public void paymentIfPaidisTrue(){
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    dummySchedule.setTrxId("123456");
    dummySchedule.setPaid(true);
    when(scheduleService.findByTrxId(dummySchedule.getTrxId())).thenReturn(dummySchedule);

    ResponseEntity<ResponseDao> paymentResponseEntity = scheduleController.payment("123456");
    assertThat(paymentResponseEntity.getStatusCodeValue()).isEqualTo(409);
  }

  @Test
  public void paymentIfPaymentIdIsNotExist(){
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    dummySchedule.setTrxId("123456");
    ResponseEntity<ResponseDao> paymentResponseEntity = scheduleController.payment("12345");
    assertThat(paymentResponseEntity.getStatusCodeValue()).isEqualTo(404);
  }

}
