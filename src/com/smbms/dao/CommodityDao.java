package com.smbms.dao;

import com.smbms.entity.Commodity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityDao {


   //ͬ����֤
    public Commodity CheckNameisTrue(String name){

        Commodity commodity=null;
      String  sql = "select * from commodity where commodityName=?";

        ResultSet rs = DBUtils.executeQuery(sql, new Object[]{name});
        try {
            if (rs.next()) {
                 commodity = new Commodity();
            }
            return  commodity;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs);
        }
        return  null;

    }

    //������Ʒ
    public int CommodityAdd(Commodity comm){

        String  sql = "insert into commodity (commodityName, commodityPrices, inventory, money)value (?,?,?,?)";

       return DBUtils.execute(sql, new Object[]{comm.getCommodityName(),comm.getCommodityPrices(),comm.getInventory(),0});
    }


    //ɾ����Ʒ
    public int CommodityDel(int id){

        String  sql = "delete from commodity where commodityId = ?";

        return DBUtils.execute(sql, new Object[]{id});
    }
    //ģ����ѯ
    public List<Commodity> bySelectName(String name){

        List<Commodity> list = new ArrayList<Commodity>();
        String  sql = "select * from admin where commodityName like ?";

        ResultSet rs = DBUtils.executeQuery(sql, new Object[]{"%"+name+"%"});
        try {
            if (rs.next()) {
                Commodity commodity = new Commodity();
                commodity.setCommodityId(rs.getInt("commodityId"));
                commodity.setCommodityName(rs.getString("commodityName"));
                commodity.setCommodityPrices(rs.getString("commodityPrices"));
                commodity.setMoney(rs.getInt("money"));
                commodity.setInventory(rs.getInt("inventory"));
                list.add(commodity);
            }
            return  list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs);
        }
        return  null;

    }


}
