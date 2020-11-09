package com.smbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFlag(rs.getInt("flag"));

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
	 * 根据id找到管理员
	 * @param lu
	 * @return
	 */
	public Admin doLogin(int id) {
		ResultSet rs = null;
		Admin u = null;
		String sql = "";
		sql = "select * from admin where id=?";
		try {
			rs = DBUtils.executeQuery(sql, new Object[]{id});
			if (rs.next()) {
				u = new Admin();
				//读取结果集里的属性，初始化到新对象中
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFlag(rs.getInt("flag"));
				return  u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
		}
		return null;
	}

	/**
	 * 根据id查询
	 */
	public List<Admin> byidSelect( ){
		List<Admin> objects = new ArrayList<Admin>();
		String sql =" select * from `admin` ";
		ResultSet rs = DBUtils.executeQuery(sql, null);
		try {
			while (rs.next()){
			 Admin	u = new Admin();
				//读取结果集里的属性，初始化到新对象中
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFlag(rs.getInt("flag"));
				objects.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
		}
		return objects;
	}

	/**
	 * 修改密码
	 */
	public  int update(String pwd){
		String sql ="UPDATE admin SET `password` = ? WHERE id=?";
		return  DBUtils.execute(sql,new Object[]{pwd});

	}

	/**
	 * 新增个普通管理员
	 * @param admin
	 * @return
	 */
	public int add(Admin admin){
		String sql="INSERT INTO `admin` VALUES(null,?,?,0)";
		return DBUtils.execute(sql,new Object[]{admin.getUsername(),admin.getPassword()});
	}
	/**
	 *删除管理员
	 */
	public int delte(int id){
		String sql="DELETE FROM `admin`  WHERE  id=?";
		return DBUtils.execute(sql,new Object[]{id});
	}

	/**
	 * 修改管理员
	 * @param args
	 */
	public int update(Admin admin){
		String sql="UPDATE admin set username=?,`password`=? WHERE id=?";
		return DBUtils.execute(sql,new Object[]{admin.getUsername(),admin.getPassword(),admin.getId()});
	}
	public static void main(String[] args) {
		System.out.println(new AdminDao().byidSelect());
	}
}