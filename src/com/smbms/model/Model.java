package com.smbms.model;

import com.smbms.dao.CommodityDao;
import com.smbms.dao.UserDao;
import com.smbms.entity.Admin;
import com.smbms.dao.AdminDao;
import com.smbms.entity.Commodity;
import com.smbms.entity.User;
import com.sun.xml.internal.ws.wsdl.writer.document.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model {
    Scanner input = new Scanner(System.in);

//    public Entity service(Entity e) {
//        // TODO Auto-generated method stub
//        if (e.getFlag() == 0) {
//            //执行登录方法
//            e.setFlag(dologin(e.getUsername(), e.getPassword()));
//        }
//        if (e.getFlag() == 4) {
//            //执行查询商品方法
//            Goods g = new Goods();
//            g = findGoodsById(e.getGoodsid());
//            e.setGoodsid(g.getGoodsId());
//            e.setGoodsname(g.getGoodsName());
//            e.setGoodsprice(g.getGoodsPrice());
//            e.setGoodscount(g.getGoodsCount());
//        }
//        if (e.getFlag() == 5) {
//            //执行新增商品方法
//            GoodsDao gd = new GoodsDao();
//            e.setResult(gd.add(e));
//        }
//        if (e.getFlag() == 6) {
//            //执行新增商品方法
//            GoodsDao gd = new GoodsDao();
//            e.setResult(gd.del(e));
//        }
//        if (e.getFlag() == 7) {
//            //执行新增商品方法
//            GoodsDao gd = new GoodsDao();
//            e.setResult(gd.update(e));
//        }
//        if (e.getFlag() == 10) {
//            //执行新增商品方法
//            AdminDao ud = new AdminDao();
//
//            e.setResult(ud.add(e));
//        }
//        if (e.getFlag() == 11) {
//            //执行新增商品方法
//            AdminDao ud = new AdminDao();
//
//            e.setResult(ud.del(e));
//        }
//        if (e.getFlag() == 12) {
//            //执行新增商品方法
//            AdminDao ud = new AdminDao();
//
//            e.setResult(ud.updateUser(e));
//        }
//        if (e.getFlag() == 13) {
//            //执行新增商品方法
//            AdminDao ud = new AdminDao();
//            e = ud.findUser(e.getUsername());
//        }
//
//
//        return e;
//    }


//    //查询商品方法
//    private Goods findGoodsById(String goodsid) {
//        Goods g = new Goods();
//        GoodsDao gd = new GoodsDao();
//        g = gd.FindById(goodsid);
//        return g;
//    }
//
//    /***
//     * 菜单
//     */
//    public void menu() {
//        System.out.println("当前权限");
//
//    }

    /**
     * 登录界面
     */
    public void show() {
        System.out.println("=============================");
        System.out.println("\t\t超市收银系统\t\t");
        System.out.println("=============================");
        System.out.println("1.管理员登录 2.退出");
        System.out.print("请选择:");
        int choose = input.nextInt();
        switch (choose) {
            case 1:
                login();
                break;
            case 2:
                System.out.println("谢谢使用!");
                System.exit(0);
                break;
        }

    }

    /**
     * 登录验证
     */

    public void login() {
        System.out.print("请输入用户名:");
        String adminName = input.next();
        System.out.print("请输入密码:");
        String adminPwd = input.next();
        //封装
        Admin u = new Admin();
        u.setPassword(adminName);
        u.setUsername(adminName);
        //数据库验证
        if (new AdminDao().doLogin(u) !=null) {
            //表示账号是对的 跳转管理员操作菜单
            Menu(new AdminDao().doLogin(u));
        } else {
            System.out.println("账号货密码错误!");
        }

    }


    /**
     * 管理员功能
     */

    public void Menu(Admin admin) {
        System.out.println("=============================");
        System.out.println("\t\t管理员操作\t\t");
        System.out.println("=============================");
        System.out.println("当前管理员权限:" + (admin.getFlag() == 1 ? "超级管理员" : "普通管理员") );
        int choose = 0;
        if (admin.getFlag() == 1) {
            System.out.println("1.管理用户 2.管理商品 3.退出");
            System.out.print("请选择:");
            choose = input.nextInt();
            switch (choose) {
                case 1:
                    user();
                    break;
                case 2:
                    goods();
                    break;
                case 3:
                    System.out.println("谢谢使用!");
                    System.exit(0);
                    break;
            }
        } else {
            System.out.println("1.管理用户  3.退出");
            System.out.println("请选择:");
            choose = input.nextInt();
            switch (choose) {
                case 1:
                    user();
                    break;
                case 2:
                    System.out.println("谢谢使用!");
                    System.exit(0);
                    break;
            }
        }


    }

    /**
     * 操作用户
     */

    public  void  user(){
        int choose =0;
        do {
            System.out.println("1.新增用户 2.根据姓名模糊查询 3.查询全部用户 4.修改用户 5.删除用户 6.返回主页");
            choose=input.nextInt();
            switch (choose){
                case 1:
                    userAdd();
                    break;
                case 2:
                    userNameById();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    userDelete();
                    break;
                case 6:
                    Menu(null);
                    break;

            }
        }while (choose!=5);

    }

    //删除用户
    private void userDelete() {
        System.out.println("=============================");
        System.out.println("\t\t管理员操作(--查询商品)\t\t");
        System.out.println("=============================");
        System.out.println("请输入序号:");
        int id = input.nextInt();
        int i = new UserDao().UserDel(id);
        if (i > 0){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
    }

    //根据姓名模糊查询
    private void userNameById() {
        System.out.println("=============================");
        System.out.println("\t\t管理员操作(--查询商品)\t\t");
        System.out.println("=============================");
        System.out.println("请输入姓名关键字:");
        String like_name = input.next();
        UserDao UserDao = new UserDao();
        List<User> users = new ArrayList<>();
        users =UserDao.bySelectName(like_name);
        if (users.size()==0){
            System.out.println("序号"+"\t"+"用户姓名"+"\t"+"消费金额"+"\t"+"余额");
            for (User user : users) {
                System.out.println(user.getUserid()+"\t"+user.getUsername()+"\t"+user.getConsumption()+"\t"+user.getSum());
            }
        }else{
            System.out.println("没有找到相似的用户数据!");
        }

    }

    //新增用户
    private void userAdd() {
        System.out.println("=============================");
        System.out.println("\t\t管理员操作(--查询商品)\t\t");
        System.out.println("=============================");
        System.out.println("请输入用户名:");
        String Name = input.next();
        System.out.println("请输入金额:");
        int sum = input.nextInt();
        User user = new User();
        user.setUsername(Name);
        user.setSum(sum);
        UserDao userDao = new UserDao();
        int userAdd = userDao.UserAdd(user);
        if (userAdd > 0){
            System.out.println("新增成功！");
        }else {
            System.out.println("新增失败！");
        }
    }

    /**
     * 操作商品
     */

    public  void  goods(){
        int choose =0;
        do {
            System.out.println("1.新增商品 2.查询商品 3.修改商品 4.删除商品 5.返回主页");
            choose=input.nextInt();
            switch (choose){
                case 1:
                    goodsAdd();
                    break;
                case 2:
                    selectgoods();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("谢谢使用!");
                    System.exit(0);
                    break;

            }
        }while (choose!=5);

    }

    private void selectgoods() {
        System.out.println("=============================");
        System.out.println("\t\t管理员操作(--查询商品)\t\t");
        System.out.println("=============================");
        System.out.println("请输入商品名称:");
        String Name =input.next();
        List<Commodity> list = new CommodityDao().bySelectName(Name);
        System.out.println("商品序号\t商品名称\t商品价格\t商品库存\t销售总额");
        for (Commodity item:list) {
            System.out.println(item.toString());
        }
    }


    private void goodsAdd() {
        System.out.println("=============================");
        System.out.println("\t\t管理员操作(--新增商品)\t\t");
        System.out.println("=============================");
        System.out.println("请输入商品名称:");
        String Name =input.next();
        System.out.println("请输入商品价格:");
        String price =input.next();
        System.out.println("请输入商品库存:");
        int inventory =input.nextInt();
        //同名演出
        if ( new CommodityDao().CheckNameisTrue(Name)==null){
            Commodity cs = new Commodity();
            cs.setCommodityName(Name);
            cs.setCommodityPrices(price);
            cs.setInventory(inventory);
            int i = new CommodityDao().CommodityAdd(cs);
            if (i>=0){
                System.out.println("新增商品成功!");
            };
        }else {
            System.out.println("名字重复，请重新！");
        }

    }
}
