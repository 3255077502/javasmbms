package com.smbms.dao;

import com.smbms.entity.Commodity;
import com.smbms.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //�����û�
    public int UserAdd(User user){
        String sql = "insert into user (username, consumption, sum)value (?,?,?)";
        int count = DBUtils.execute(sql,new Object[]{user.getUsername(),0,user.getSum()});
        return count;
    }
    //ɾ���û�
    public int UserDel(int id){
        String sql = "delete from user where userid = ?";
        return DBUtils.execute(sql,new Object[]{id});
    }
    //��ѯ����
    public List<User> bySelectNameAll(){
        List<User> list = new ArrayList<>();
        String sql = "select * from user";
        ResultSet rs = DBUtils.executeQuery(sql,null);
        try {
            while (rs.next()){
                User user = new User();
                user.setUserid(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                user.setSum(rs.getInt("sum"));
                list.add(user);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtils.close(rs);
        }
        return null;
    }
    //����id��ѯ����
    public User byidselectUser(int id ){
        String sql = "select * from user where userid =?";
        User user=null;
        ResultSet rs = DBUtils.executeQuery(sql,new Object[]{id});
        try {
            while (rs.next()){
                 user = new User();
                user.setUserid(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                user.setSum(rs.getInt("sum"));
            }
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtils.close(rs);
        }
        return null;
    }
    //ģ����ѯ
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
    //�޸��û�
    public int updateUser(User user){
        String sql = "update user set username=?,sum=? where userid=?";
        return DBUtils.execute(sql,new Object[]{user.getUsername(),user.getSum(),user.getUserid()});
    }
}
