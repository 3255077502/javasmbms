package com.smbms.entity;


public class User {

  private long userid;
  private String username;
  private long consumption;
  private long sum;


  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public long getConsumption() {
    return consumption;
  }

  public void setConsumption(long consumption) {
    this.consumption = consumption;
  }


  public long getSum() {
    return sum;
  }

  public void setSum(long sum) {
    this.sum = sum;
  }

}
