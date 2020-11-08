package com.smbms.model;

import com.smbms.dao.CommodityDao;
import com.smbms.entity.Admin;
import com.smbms.dao.AdminDao;
import com.smbms.entity.Commodity;

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
        if (new AdminDao().doLogin(u) != null) {
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
        System.out.println("当前管理员权限:" + (admin.getFlag() == 1 ? "超级管理员" : "普通管理员"));
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

    public void user() {
        int choose = 0;
        do {
            System.out.println("1.新增用户 2.查询用户 3.修改用户 4.删除用户 5.返回主页");
            choose = input.nextInt();
            switch (choose) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    Menu(null);
                    break;

            }
        } while (choose != 5);

    }

    /**
     * 操作商品
     */

    public void goods() {
        int choose = 0;
        do {
            System.out.println("1.新增商品 2.根据商品名称查询 3查询全部商品. 4.修改商品 5.删除商品 6.返回主页");
            choose = input.nextInt();
            switch (choose) {
                case 1:
                    goodsAdd();
                    break;
                case 2:
                    selectgoods();
                    break;
                case 3:
                    selectgoods2();
                    break;
                case 4:
                    updategoods();
                    break;
                case 5:
                    delgoods();
                    break;
                case 6:
                    System.out.println("谢谢使用!");
                    System.exit(0);
                    break;

            }
        } while (choose != 6);

    }

    private void updategoods() {
        System.out.println("=============================");
        System.out.println("\t管理员操作(--修改商品)\t\t");
        System.out.println("=============================");
        System.out.println("请输入要修改的商品序号:");
        int id = input.nextInt();
        Commodity commodity = new CommodityDao().byidCommdity(id);
        if (commodity == null) {
            System.out.println("没有你要的商品序号id!");
            return;
        }
        System.out.println("请输入要修改商品名称:");
        String Name = input.next();
        System.out.println("请输入要修改商品价格:");
        String price = input.next();
        System.out.println("请输入要修改商品库存:");
        int inventory = input.nextInt();
        //同名演出
        commodity.setCommodityName(Name);
        commodity.setCommodityPrices(price);
        commodity.setInventory(inventory);
        int i = new CommodityDao().updatecommdity(commodity);
        if (i >= 0) {
            System.out.println("修改商品成功!");
        } else{
        System.out.println("名字重复，请重新！");
    }

}

    private void delgoods() {
        System.out.println("=============================");
        System.out.println("\t管理员操作(--删除商品)\t\t");
        System.out.println("=============================");
        System.out.print("请输入商品序号:");
        int id = input.nextInt();
        int i = new CommodityDao().CommodityDel(id);
        if (i > 0) {
            System.out.println("删除成功!");
        } else {
            System.out.println("删除失败!");
        }
    }

    private void selectgoods2() {
        System.out.println("=============================");
        System.out.println("\t管理员操作(--查询全部商品)\t\t");
        System.out.println("=============================");
        List<Commodity> list = new CommodityDao().bySelectName();
        System.out.println("商品序号\t商品名称\t商品价格\t商品库存\t销售总额");
        for (Commodity item : list) {
            System.out.println(item.toString());
        }
    }

    private void selectgoods() {
        System.out.println("=============================");
        System.out.println("\t\t管理员操作(--查询商品)\t\t");
        System.out.println("=============================");
        System.out.println("请输入商品名称:");
        String Name = input.next();
        List<Commodity> list = new CommodityDao().bySelectName(Name);
        System.out.println("商品序号\t商品名称\t商品价格\t商品库存\t销售总额");
        for (Commodity item : list) {
            System.out.println(item.toString());
        }
    }


    private void goodsAdd() {
        System.out.println("=============================");
        System.out.println("\t\t管理员操作(--新增商品)\t\t");
        System.out.println("=============================");
        System.out.println("请输入商品名称:");
        String Name = input.next();
        System.out.println("请输入商品价格:");
        String price = input.next();
        System.out.println("请输入商品库存:");
        int inventory = input.nextInt();
        //同名演出
        if (new CommodityDao().CheckNameisTrue(Name) == null) {
            Commodity cs = new Commodity();
            cs.setCommodityName(Name);
            cs.setCommodityPrices(price);
            cs.setInventory(inventory);
            int i = new CommodityDao().CommodityAdd(cs);
            if (i >= 0) {
                System.out.println("新增商品成功!");
            }
            ;
        } else {
            System.out.println("名字重复，请重新！");
        }
    }
}
