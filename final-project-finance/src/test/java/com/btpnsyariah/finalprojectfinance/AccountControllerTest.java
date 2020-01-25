//package com.btpnsyariah.finalprojectfinance;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//import com.btpnsyariah.finalprojectfinance.controller.AccountController;
//import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
//import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
//import com.btpnsyariah.finalprojectfinance.entitty.FinancingSchedule;
//import com.btpnsyariah.finalprojectfinance.service.AccountService;
//import com.btpnsyariah.finalprojectfinance.service.ScheduleService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//@ExtendWith(MockitoExtension.class)
//public class AccountControllerTest {
//
//  @InjectMocks
//  AccountController accountController;
//
//  @Mock
//  AccountService accountService;
//
//  @Mock
//  ScheduleService scheduleService;
//
//  FinancingAccount dummyAccount = new FinancingAccount();
//
//  @Test
//  public void registrationTest(){
//    dummyAccount.setCustomerId("12334");
//    dummyAccount.setPlafon(new BigDecimal("10000000"));
//    dummyAccount.setDisbursementDate(new Date());
//
//    MockHttpServletRequest request = new MockHttpServletRequest();
//    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//    ResponseEntity<ResponseDao> registration = accountController.createFinancingAccount(dummyAccount);
//    assertThat(registration.getStatusCodeValue()).isEqualTo(201);
//  }
//
//  @Test
//  public void findByAccountIdTest(){
//    dummyAccount.setAccountNo("11223344");
//    when(accountService.findByAccountNo(dummyAccount.getAccountNo())).thenReturn(dummyAccount);
//
//    MockHttpServletRequest request = new MockHttpServletRequest();
//    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//    ResponseEntity<ResponseDao> findByAccountId = accountController.findByAccountNo("11223344");
//    assertThat(findByAccountId.getStatusCodeValue()).isEqualTo(200);
//  }
//
//  @Test
//  public void accountList(){
//    MockHttpServletRequest request = new MockHttpServletRequest();
//    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//    ResponseEntity<ResponseDao> accountList = accountController.accountList();
//    assertThat(accountList.getStatusCodeValue()).isEqualTo(200);
//  }
//
//}
