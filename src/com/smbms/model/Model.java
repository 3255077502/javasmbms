package com.smbms.model;

import com.smbms.dao.CommodityDao;
import com.smbms.dao.DBUtils;
import com.smbms.dao.UserDao;
import com.smbms.entity.Admin;
import com.smbms.dao.AdminDao;
import com.smbms.entity.Commodity;
import com.smbms.entity.User;

import java.util.List;
import java.util.Scanner;

public class Model {
    Scanner input = new Scanner(System.in);

    Admin admin = null;

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
            admin = new AdminDao().doLogin(u);
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
            System.out.println("1.管理用户 2.管理商品 3.管理管理员 4.退出");
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
                    manger();
                    break;
                case 4:
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
     * 操作管理员
     */
    private void manger() {
        int choose = 0;
        do {
            System.out.println("1.新增管理员 2.查询管理员 3.修改密码 4.修改管理员 5.删除管理员  6.返回主页");
            System.out.print("请选择:");
            choose = input.nextInt();
            switch (choose) {
                case 1:
                    addAdmin();
                    break;
                case 2:
                    selectAdmin();
                    break;
                case 3:
                    updateAdminPwd();
                    break;
                case 4:
                    updateAdmin();
                    break;
                case 5:
                    delAdmin();
                    break;
                case 6:
                    Menu(admin);
                    break;

            }
        } while (choose != 6);
    }

    private void updateAdmin() {
        System.out.println("=============================");
        System.out.println("\t管理员操作(--修改管理员)\t\t");
        System.out.println("=============================");
        System.out.print("请输入你修改管理员编号:");
        Admin admin = new AdminDao().doLogin(input.nextInt());
        if (admin==null){
            System.out.println("找不到你要修改的管理员!");
            return;
        }
        System.out.print("请输入要修改的管理员账户：");
        String adminname =input.next();
        System.out.print("请输入要修改的管理员密码：");
        String adminpwd =input.next();
        //调用新增
        Admin admin1= new Admin();
        admin1.setUsername(adminname);
        admin1.setPassword(adminpwd);
        if (new AdminDao().add(admin1)==1){
            System.out.println("新增成功!");
        }else {
            System.out.println("新增失败!");
        }
    }

    private void delAdmin() {
        System.out.println("=============================");
        System.out.println("\t管理员操作(--删除管理员)\t\t");
        System.out.println("=============================");
        System.out.print("请输入你删除管理员编号:");
        int id = input.nextInt();
        if (new AdminDao().delte(id)==1){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
    }

    //新增管理员
    private void addAdmin() {
        System.out.println("=============================");
        System.out.println("\t管理员操作(--新增管理员)\t\t");
        System.out.println("=============================");
        System.out.print("请输入管理员账户：");
        String adminname =input.next();
        System.out.print("请输入管理员密码：");
        String adminpwd =input.next();
        //调用新增
        Admin admin1= new Admin();
        admin1.setUsername(adminname);
        admin1.setPassword(adminpwd);
        if (new AdminDao().add(admin1)==1){
            System.out.println("新增成功!");
        }else {
            System.out.println("新增失败!");
        }
    }
    //修改管理员
    private void updateAdminPwd() {
        System.out.println("=============================");
        System.out.println("\t管理员操作(--修改管理员密码)\t\t");
        System.out.println("=============================");
        System.out.print("请输入你的旧密码:");
        String oldpwd = input.next();
        if (!admin.getPassword().equals(oldpwd)) {
            System.out.println("旧密码错误，请重新");
        }
        System.out.print("请输入新密码");
        String newpwd = input.next();
        if (new AdminDao().update(newpwd) > 0) {
            System.out.println("修改成功！");
            admin = null;
            show();
        } else {
            System.out.println("修改失败！");

        }
    }
    //
    private void selectAdmin() {
        System.out.println("=============================");
        System.out.println("\t管理员操作(--查询管理员)\t\t");
        System.out.println("=============================");
        //调用查询方法
        System.out.println("管理员编号\t管理员账户\t管理员密码\t管理员权限");
        List<Admin> admins = new AdminDao().byidSelect();
        System.out.println(admins.size());
        for (Admin item: admins
             ) {
            item.toString();
        }
    }

    /**
     * 操作用户
     */

    public void user() {
        int choose = 0;
        do {
            System.out.println("1.新增用户 2.根据姓名模糊查询 3.查询所有用户 4.修改用户 5.删除用户 6.返回主页");
            System.out.print("请选择:");
            choose = input.nextInt();
            switch (choose) {
                case 1:
                    userAdd();
                    break;
                case 2:
                    selectUser();
                    break;
                case 3:
                    selectUserAll();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deluser();
                    break;
                case 6:
                    Menu(admin);
                    break;

            }
        } while (choose != 6);

    }

    //删除
    private void deluser() {
        System.out.println("=============================");
        System.out.println("\t管理员操作(--删除用户)\t\t");
        System.out.println("=============================");
        System.out.print("请输入要删除的用户id:");
        int id = input.nextInt();
        int i = new UserDao().UserDel(id);
        if (i < 0) {
            System.out.println("删除失败 !");
            return;
        } else {
            System.out.println("删除成功 !");

        }
    }

    //修改用户
    private void updateUser() {
        System.out.println("=============================");
        System.out.println("\t管理员操作(--修改用户)\t\t");
        System.out.println("=============================");
        System.out.print("请输入要修改的用户id:");
        int id = input.nextInt();
        User user = new UserDao().byidselectUser(id);
        if (user == null) {
            System.out.println("没有你要的用户id");
            return;
        }
        System.out.println("请输入要修改的用户名:");
        String Name = input.next();
        System.out.println("请输入要修改的余额:");
        String price = input.next();
        int i = new UserDao().updateUser(user);
        if (i >= 0) {
            System.out.println("修改用户成功!");
        } else {
            System.out.println("名字重复，请重新！");
        }
    }

    //查询所有
    private void selectUserAll() {
        System.out.println("=============================");
        System.out.println("\t管理员操作(--查询全部用户)\t\t");
        System.out.println("=============================");
        List<User> list = new UserDao().bySelectNameAll();
        System.out.println("卡号\t用户名\t余额\t消费总额");
        for (User item : list) {
            System.out.println(item.toString());
        }
    }

    //根据姓名模糊查询
    private void selectUser() {
        System.out.println("=============================");
        System.out.println("\t\t管理员操作(--查询用户)\t\t");
        System.out.println("=============================");
        System.out.print("请输入用户名:");
        String Name = input.next();
        List<User> list = new UserDao().bySelectName(Name);
        System.out.println("卡号\t用户名\t余额\t消费总额");
        for (User item : list) {
            System.out.println(item.toString());
        }
    }

    //新增用户
    private void userAdd() {
        System.out.println("=============================");
        System.out.println("\t\t管理员操作(--新增用户)\t\t");
        System.out.println("=============================");
        System.out.print("请输入用户名:");
        String Name = input.next();
        System.out.print("请输入余额:");
        int sum = input.nextInt();
        User user = new User();
        user.setUsername(Name);
        user.setSum(sum);
        UserDao userDao = new UserDao();
        int i = userDao.UserAdd(user);
        if (i > 0) {
            System.out.println("新增成功！");
        } else {
            System.out.println("新增失败！");
        }
    }

    /**
     * 操作商品
     */

    public void goods() {
        int choose = 0;
        do {
            System.out.println("1.新增商品 2.根据商品名称查询 3查询全部商品. 4.修改商品 5.删除商品 6.返回主页");
            System.out.print("请选择:");
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
                    Menu(admin);
                    break;

            }
        } while (choose != 6);

    }

    /**
     * 修改商品
     */
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
        System.out.print("请输入要修改商品名称:");
        String Name = input.next();
        System.out.print("请输入要修改商品价格:");
        String price = input.next();
        System.out.print("请输入要修改商品库存:");
        int inventory = input.nextInt();
        //同名演出
        commodity.setCommodityName(Name);
        commodity.setCommodityPrices(price);
        commodity.setInventory(inventory);
        int i = new CommodityDao().updatecommdity(commodity);
        if (i >= 0) {
            System.out.println("修改商品成功!");
        } else {
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
