package com.btpnsyariah.finalprojectfinance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.btpnsyariah.finalprojectfinance.controller.AccountController;
import com.btpnsyariah.finalprojectfinance.dao.ResponseDao;
import com.btpnsyariah.finalprojectfinance.entitty.FinancingAccount;
import com.btpnsyariah.finalprojectfinance.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

  @InjectMocks
  AccountController accountController = new AccountController();

  @Mock
  AccountService accountService;

  @Test
  public void registrationTest(){
    FinancingAccount dummyAccount = new FinancingAccount();
    dummyAccount.setCustomerId("12334");
    dummyAccount.setPlafon(new BigDecimal("10000000"));
    dummyAccount.setDisbursementDate(new Date());
    ResponseDao responseDao = new ResponseDao(201,"CREATED","Akun selesai dibuat",dummyAccount);
    when(accountService.registration(dummyAccount)).thenReturn(ResponseEntity.status(201).body(responseDao));
    assertThat(accountController.createFinancingAccount(dummyAccount).getStatusCodeValue()).isEqualTo(201);
    Assertions.assertEquals(dummyAccount.getPlafon(),new BigDecimal("10000000"));
    Assertions.assertEquals(dummyAccount.getTenor(),12);
  }

  @Test
  public void findByAccountNoTest(){
    FinancingAccount dummyAccount = new FinancingAccount();
    dummyAccount.setAccountNo("11223344");
    ResponseDao responseDao = new ResponseDao(200,"OK","account ditemukan",dummyAccount);
    when(accountService.findByAccountNo(dummyAccount.getAccountNo())).thenReturn(ResponseEntity.status(200).body(responseDao));
    assertThat(accountController.findByAccountNo(dummyAccount.getAccountNo()).getStatusCodeValue()).isEqualTo(200);
    Assertions.assertEquals(dummyAccount.getAccountNo(), "11223344");
  }
//
//  @Test
//  public void accountList(){
//    MockHttpServletRequest request = new MockHttpServletRequest();
//    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//    ResponseEntity<ResponseDao> accountList = accountController.accountList();
//    assertThat(accountList.getStatusCodeValue()).isEqualTo(200);
//  }

}
