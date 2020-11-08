package com.smbms.dao;
import java.sql.*;

/**
 * Created by Jie on 2016/01/15.
 */
public class DBUtils {
  public  static  Connection conn;
  public  static   PreparedStatement ps = null;
    //���ݿ����ӵ�ַ
    public static String URL;
    //�û���
    public static String USERNAME;
    //����
    public static String PASSWORD;
    //mysql��������
    public static String DRIVER;
    private DBUtils(){
    	
    }
    //ʹ�þ�̬�������������
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
    //����һ����ȡ���ݿ����ӵķ���
    public static void getConnection(){
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("��ȡ����ʧ��");
        }

    }
    /**
     * �ر����ݿ�����
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
     * ��ɾ��
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
     * ��ѯ
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
