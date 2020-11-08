package com.smbms.entity;


public class Commodity {

  private long commodityId;
  private String commodityName;
  private String commodityPrices;
  private long inventory;
  private long money;


  public long getCommodity() {
    return commodityId;
  }

  public void setCommodity(long commodity) {
    this.commodityId = commodity;
  }


  public String getCommodityName() {
    return commodityName;
  }

  public void setCommodityName(String commodityName) {
    this.commodityName = commodityName;
  }


  public String getCommodityPrices() {
    return commodityPrices;
  }

  public void setCommodityPrices(String commodityPrices) {
    this.commodityPrices = commodityPrices;
  }


  public long getInventory() {
    return inventory;
  }

  public void setInventory(long inventory) {
    this.inventory = inventory;
  }


  public long getMoney() {
    return money;
  }

  public void setMoney(long money) {
    this.money = money;
  }

}
