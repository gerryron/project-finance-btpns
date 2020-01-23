package com.btpnsyariah.finalprojectfinance.dao;

public class ResponseDao <T extends Object>{

  private int code;
  private String status;
  private String message;
  private T data;

  public ResponseDao() {
  }

  public ResponseDao(int code, String status, String message, T data) {
    this.code = code;
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
