package com.btpnsyariah.finalprojectfinance;

import com.btpnsyariah.finalprojectfinance.controller.ScheduleController;
import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
import com.btpnsyariah.finalprojectfinance.service.ScheduleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScheduleControllerTest {

  @InjectMocks
  ScheduleController scheduleController = new ScheduleController();

  @Mock
  ScheduleService scheduleService;

  @Test
  public void paymentIfPaidIsFalse(){
   FinancingSchedule dummySchedule = new FinancingSchedule();
    dummySchedule.setAccountId("1100");
    dummySchedule.setTrxId("123456");
    ResponseDao responseDao = new ResponseDao(202, "ACCEPTED", "Pembayaran sukses dilakukan", dummySchedule);
    when(scheduleService.payment("123456")).thenReturn(ResponseEntity.status(202).body(responseDao));

    assertThat(scheduleController.payment(dummySchedule.getTrxId()).getStatusCodeValue()).isEqualTo(202);
    Assertions.assertEquals(dummySchedule.getTrxId(), "123456");
    Assertions.assertEquals(dummySchedule.isPaid(), false);
  }

  @Test
  public void paymentIfPaidisTrue(){
    FinancingSchedule dummySchedule = new FinancingSchedule();
    dummySchedule.setTrxId("123456");
    dummySchedule.setPaid(true);
    ResponseDao responseDao = new ResponseDao(409, "ALREADY_EXIST", "Angsuran sudah dibayarkan", dummySchedule);
    when(scheduleService.payment(dummySchedule.getTrxId())).thenReturn(ResponseEntity.status(409).body(responseDao));

    assertThat(scheduleController.payment(dummySchedule.getTrxId()).getStatusCodeValue()).isEqualTo(409);
    Assertions.assertEquals(dummySchedule.isPaid(), true);
  }

  @Test
  public void paymentIfPaymentIdIsNotExist(){
    FinancingSchedule dummySchedule = new FinancingSchedule();
    dummySchedule.setTrxId("123456");
    ResponseDao responseDao = new ResponseDao(404, "NOT_FOUND", "Data angsuran tidak ditemukan", dummySchedule);
    System.out.println(responseDao);
    when(scheduleService.payment("12345")).thenReturn(ResponseEntity.status(404).body(responseDao));

    assertThat(scheduleController.payment("12345").getStatusCodeValue()).isEqualTo(404);
  }

  @Test
  public void scheduleReport(){
    FinancingSchedule dummySchedule = new FinancingSchedule();
    dummySchedule.setAccountId("11223344");
    ResponseDao responseDao = new ResponseDao(200, "OK", "Schedule ditemukan", dummySchedule);
    when(scheduleService.scheduleReport(dummySchedule.getAccountId())).thenReturn(ResponseEntity.status(200).body(responseDao));

    assertThat(scheduleController.scheduleReport("11223344").getStatusCodeValue()).isEqualTo(200);
    Assertions.assertEquals(dummySchedule.getAccountId(),"11223344");
  }

}
