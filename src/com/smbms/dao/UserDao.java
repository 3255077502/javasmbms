package com.smbms.dao;

import com.smbms.entity.Commodity;
import com.smbms.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //新增用户
    public int UserAdd(User user){
        String sql = "insert into user (username, consumption, sum)value (?,?,?)";
        int count = DBUtils.execute(sql,new Object[]{user.getUsername(),0,user.getSum()});
        return count;
    }
    //删除用户
    public int UserDel(int id){
        String sql = "delete from user where userid = ?";
        return DBUtils.execute(sql,new Object[]{id});
    }
    //模糊查询
    public List<User> bySelectName(String name){
        List<User> list = new ArrayList<>();
        String  sql = "select * from user where username like ?";

        ResultSet rs = DBUtils.executeQuery(sql, new Object[]{"%"+name+"%"});
        try {
            while (rs.next()) {
                User user = new User();
                user.setUserid(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                user.setConsumption(rs.getInt("consumption"));
                user.setSum(rs.getInt("sum"));
                list.add(user);
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
