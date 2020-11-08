package com.smbms.dao;
import java.sql.*;

/**
 * Created by Jie on 2016/01/15.
 */
public class DBUtils {
  public  static  Connection conn;
  public  static   PreparedStatement ps = null;
    //数据库连接地址
    public static String URL;
    //用户名
    public static String USERNAME;
    //密码
    public static String PASSWORD;
    //mysql的驱动类
    public static String DRIVER;
    private DBUtils(){
    	
    }
    //使用静态块加载驱动程序
    static{
        URL = "jdbc:mysql://localhost:3306/shop?serverTimezone=UTC";
        USERNAME = "root";
        PASSWORD = "root";
        DRIVER = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //定义一个获取数据库连接的方法
    public static void getConnection(){
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取连接失败");
        }

    }
    /**
     * 关闭数据库连接
     */
    public static void close(ResultSet rs){

        try {
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            if(conn!=null)conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     * 增删改
     * @param
     */
    public static int execute(String sql,Object [] objects){
        getConnection();
        try {
             ps = conn.prepareStatement(sql);
            if (objects !=null) {
                for (int i = 0; i < objects.length; i++) {
                    ps.setObject(i+1,objects[i]);
                }
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    /***
     * 查询
     * @param
     */
    public static ResultSet executeQuery(String sql,Object [] objects){
        getConnection();
        try {
            ps = conn.prepareStatement(sql);
            if (objects !=null) {
                for (int i = 0; i < objects.length; i++) {
                    ps.setObject(i+1,objects[i]);
                }
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
         DBUtils.getConnection();
    }
}
