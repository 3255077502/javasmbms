package com.smbms.entity;


public class Commodity {

  private long commodityId;
  private String commodityName;
  private String commodityPrices;
  private long inventory;
  private long money;

  public Commodity() {
  }

  public long getCommodityId() {
    return commodityId;
  }

  public void setCommodityId(long commodityId) {
    this.commodityId = commodityId;
  }

  public Commodity(long commodityId, String commodityName, String commodityPrices, long inventory, long money) {
    this.commodityId = commodityId;
    this.commodityName = commodityName;
    this.commodityPrices = commodityPrices;
    this.inventory = inventory;
    this.money = money;
  }

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

  @Override
  public String toString() {
    return commodityId +"\t"+ commodityName +"\t" + commodityPrices +"\t" + inventory  +"\t"+ money;
  }
}
