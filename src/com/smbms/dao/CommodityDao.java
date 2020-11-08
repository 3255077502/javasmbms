package com.smbms.dao;

import com.smbms.entity.Commodity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityDao {


    //同名验证
    public Commodity CheckNameisTrue(String name) {

        Commodity commodity = null;
        String sql = "select * from Commodity where commodityName=?";

        ResultSet rs = DBUtils.executeQuery(sql, new Object[]{name});
        try {
            if (rs.next()) {
                commodity = new Commodity();
            }
            return commodity;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
        }
        return null;

    }

    //新增商品
    public int CommodityAdd(Commodity comm) {

        String sql = "insert into commodity values(null,?,?,?,?)";

        return DBUtils.execute(sql, new Object[]{comm.getCommodityName(), comm.getCommodityPrices(), comm.getInventory(), 0});
    }

    //删除商品
    public int CommodityDel(int id) {

        String sql = "delete from commodity where commodityId = ?";

        return DBUtils.execute(sql, new Object[]{id});
    }

    //模糊查询
    public List<Commodity> bySelectName(String name) {

        List<Commodity> list = new ArrayList<Commodity>();
        String sql = "select * from Commodity where commodityName like ?";

        ResultSet rs = DBUtils.executeQuery(sql, new Object[]{"%" + name + "%"});
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
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
        }
        return null;

    }

    //查询全部商品
    public List<Commodity> bySelectName() {

        List<Commodity> list = new ArrayList<Commodity>();
        String sql = "select * from Commodity ";

        ResultSet rs = DBUtils.executeQuery(sql, null);
        try {
           while (rs.next()) {
                Commodity commodity = new Commodity();
                commodity.setCommodityId(rs.getInt("commodityId"));
                commodity.setCommodityName(rs.getString("commodityName"));
                commodity.setCommodityPrices(rs.getString("commodityPrices"));
                commodity.setMoney(rs.getInt("money"));
                commodity.setInventory(rs.getInt("inventory"));
                list.add(commodity);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
        }
        return null;

    }
    //根据id查询
    public Commodity byidCommdity(int id) {

        Commodity commodity = null;
        String sql = "select * from Commodity where commodityId=?";
        ResultSet rs = DBUtils.executeQuery(sql, new Object[]{id});
        try {
            if (rs.next()) {
                 commodity = new Commodity();
                commodity.setCommodityId(rs.getInt("commodityId"));
                commodity.setCommodityName(rs.getString("commodityName"));
                commodity.setCommodityPrices(rs.getString("commodityPrices"));
                commodity.setMoney(rs.getInt("money"));
                commodity.setInventory(rs.getInt("inventory"));
            }
            return commodity;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
        }
        return null;

    }
    //修改
    public int updatecommdity(Commodity commodity) {

        String sql = "update commodity set commodityName=?,commodityPrices=?,inventory=? where commodityId =?";
        return  DBUtils.execute(sql, new Object[]{commodity.getCommodityName(),commodity.getCommodityPrices(),commodity.getInventory(),commodity.getCommodityId()});


    }
}
