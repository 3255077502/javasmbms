package com.smbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smbms.entity.Entity;
import com.smbms.entity.Admin;

/**
 * Created by Jie on 2016/1/15.
 */
public class AdminDao {

	/**
	 * 实现登录方法
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
				//读取结果集里的属性，初始化到新对象中
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

	/**
	 * 新增
	 * @param ad
	 * @return
	 */
	public boolean add(Entity ad) {
		AdminDao ud =new AdminDao();
		Admin u =new Admin();
		u.setUsername(ad.getUsername());
		u.setPassword(ad.getPassword());
		u.setFlag(ad.getRole());
		if(ud.doLogin(u)==null){
			Connection conn = null;
			ResultSet rs = null;
			String sql = "";
			sql = "insert into user (username,password,flag)value(?,?,?)";
			
			try {
			 int num=	DBUtils.execute(sql,new Object[]{ad.getUsername(),ad.getPassword(),ad.getRole()});

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtils.close(rs);
			}
		}else{
			System.out.println("用户已存在！");
			return false;
		}
		
		return true;
	}

	public boolean del(Entity e) {
		// TODO Auto-generated method stub
		AdminDao ud =new AdminDao();
		Admin u =new Admin();
		u.setUsername(e.getUsername());
		u.setPassword(e.getPassword());
		if(ud.doLogin(u)==null){
			String sql = "";
			sql = "delete from user where username= ?";
			try {
				int num= DBUtils.execute(sql,new Object[]{e.getUsername()});
				e.setResult(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				DBUtils.close(null);
			}
		}else{
			System.out.println("用户已存在！");
			return false;
		}
		return true;
	}

	public boolean updateUser(Entity e) {
		AdminDao ud =new AdminDao();
		Admin u =new Admin();
		u.setUsername(e.getUsername());
		u.setPassword(e.getPassword());
		if(ud.doLogin(u)==null){
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "";
			sql = "update user set password=?,flag=? where username=?";
			try {
				int num=	DBUtils.execute(sql,new Object[]{e.getPassword(),e.getFlag(),e.getUsername()});
			} catch (Exception e3) {
				e3.printStackTrace();
			} finally {
				DBUtils.close(rs);
			}
		}else{
			System.out.println("修改失败！");
			return false;
		}
		
		return true;
	}

	public Entity findUser(String username) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Entity e = null;
		String sql = "";
		sql = "select * from user where username=?";
		
		try {
		rs =DBUtils.executeQuery(sql,new Object[]{username});
			if (rs.next()) {
				e=new Entity();
				//读取结果集里的属性，初始化到新对象中
				e.setUsername(rs.getString("username"));
				e.setPassword(rs.getString("password"));
				e.setRole(rs.getInt("flag"));
			}
		} catch (SQLException e4) {
			e4.printStackTrace();
		} finally {
			DBUtils.close(rs);
		}
		return e;
	}
	
}