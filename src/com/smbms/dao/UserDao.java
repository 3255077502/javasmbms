package com.smbms.dao;

import com.smbms.entity.User;

public class UserDao {
    public int UserAdd(User user){
        String sql = "insert into user (username, consumption, sum)value (?,?,?)";
        int count = DBUtils.execute(sql,new Object[]{user.getUsername(),0,user.getSum()});
        return count;
    }
}
