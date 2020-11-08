package com.smbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smbms.entity.Admin;

/**
 * Created by Jie on 2016/1/15.
 */
public class AdminDao {

	/**
	 * ʵ�ֵ�¼����
	 */

	public Admin doLogin(Admin lu) {
		ResultSet rs = null;
		Admin u = null;
		String sql = "";
		sql = "select * from admin where username=?";
		try {
			rs = DBUtils.executeQuery(sql, new Object[]{lu.getUsername()});
			if (rs.next()) {
				u = new Admin();
				//��ȡ�����������ԣ���ʼ�����¶�����
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFlag(rs.getInt("flag"));
				System.out.println(rs.getInt("flag"));
				
				if(u.getUsername().equals(lu.getUsername())&&u.getPassword().equals(lu.getPassword())){
					return u;
				}
				
			}else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
		}
		return null;
	}
}