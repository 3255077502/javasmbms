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
//            //ִ�е�¼����
//            e.setFlag(dologin(e.getUsername(), e.getPassword()));
//        }
//        if (e.getFlag() == 4) {
//            //ִ�в�ѯ��Ʒ����
//            Goods g = new Goods();
//            g = findGoodsById(e.getGoodsid());
//            e.setGoodsid(g.getGoodsId());
//            e.setGoodsname(g.getGoodsName());
//            e.setGoodsprice(g.getGoodsPrice());
//            e.setGoodscount(g.getGoodsCount());
//        }
//        if (e.getFlag() == 5) {
//            //ִ��������Ʒ����
//            GoodsDao gd = new GoodsDao();
//            e.setResult(gd.add(e));
//        }
//        if (e.getFlag() == 6) {
//            //ִ��������Ʒ����
//            GoodsDao gd = new GoodsDao();
//            e.setResult(gd.del(e));
//        }
//        if (e.getFlag() == 7) {
//            //ִ��������Ʒ����
//            GoodsDao gd = new GoodsDao();
//            e.setResult(gd.update(e));
//        }
//        if (e.getFlag() == 10) {
//            //ִ��������Ʒ����
//            AdminDao ud = new AdminDao();
//
//            e.setResult(ud.add(e));
//        }
//        if (e.getFlag() == 11) {
//            //ִ��������Ʒ����
//            AdminDao ud = new AdminDao();
//
//            e.setResult(ud.del(e));
//        }
//        if (e.getFlag() == 12) {
//            //ִ��������Ʒ����
//            AdminDao ud = new AdminDao();
//
//            e.setResult(ud.updateUser(e));
//        }
//        if (e.getFlag() == 13) {
//            //ִ��������Ʒ����
//            AdminDao ud = new AdminDao();
//            e = ud.findUser(e.getUsername());
//        }
//
//
//        return e;
//    }


//    //��ѯ��Ʒ����
//    private Goods findGoodsById(String goodsid) {
//        Goods g = new Goods();
//        GoodsDao gd = new GoodsDao();
//        g = gd.FindById(goodsid);
//        return g;
//    }
//
//    /***
//     * �˵�
//     */
//    public void menu() {
//        System.out.println("��ǰȨ��");
//
//    }

    /**
     * ��¼����
     */
    public void show() {
        System.out.println("=============================");
        System.out.println("\t\t��������ϵͳ\t\t");
        System.out.println("=============================");
        System.out.println("1.����Ա��¼ 2.�˳�");
        System.out.print("��ѡ��:");
        int choose = input.nextInt();
        switch (choose) {
            case 1:
                login();
                break;
            case 2:
                System.out.println("ллʹ��!");
                System.exit(0);
                break;
        }

    }

    /**
     * ��¼��֤
     */

    public void login() {
        System.out.print("�������û���:");
        String adminName = input.next();
        System.out.print("����������:");
        String adminPwd = input.next();
        //��װ
        Admin u = new Admin();
        u.setPassword(adminName);
        u.setUsername(adminName);
        //���ݿ���֤
        if (new AdminDao().doLogin(u) != null) {
            //��ʾ�˺��ǶԵ� ��ת����Ա�����˵�
            Menu(new AdminDao().doLogin(u));
        } else {
            System.out.println("�˺Ż��������!");
        }

    }


    /**
     * ����Ա����
     */

    public void Menu(Admin admin) {
        System.out.println("=============================");
        System.out.println("\t\t����Ա����\t\t");
        System.out.println("=============================");
        System.out.println("��ǰ����ԱȨ��:" + (admin.getFlag() == 1 ? "��������Ա" : "��ͨ����Ա"));
        int choose = 0;
        if (admin.getFlag() == 1) {
            System.out.println("1.�����û� 2.������Ʒ 3.�˳�");
            System.out.print("��ѡ��:");
            choose = input.nextInt();
            switch (choose) {
                case 1:
                    user();
                    break;
                case 2:
                    goods();
                    break;
                case 3:
                    System.out.println("ллʹ��!");
                    System.exit(0);
                    break;
            }
        } else {
            System.out.println("1.�����û�  3.�˳�");
            System.out.println("��ѡ��:");
            choose = input.nextInt();
            switch (choose) {
                case 1:
                    user();
                    break;
                case 2:
                    System.out.println("ллʹ��!");
                    System.exit(0);
                    break;
            }
        }


    }

    /**
     * �����û�
     */

    public void user() {
        int choose = 0;
        do {
            System.out.println("1.�����û� 2.��ѯ�û� 3.�޸��û� 4.ɾ���û� 5.������ҳ");
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
     * ������Ʒ
     */

    public void goods() {
        int choose = 0;
        do {
            System.out.println("1.������Ʒ 2.������Ʒ���Ʋ�ѯ 3��ѯȫ����Ʒ. 4.�޸���Ʒ 5.ɾ����Ʒ 6.������ҳ");
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
                    System.out.println("ллʹ��!");
                    System.exit(0);
                    break;

            }
        } while (choose != 6);

    }

    private void updategoods() {
        System.out.println("=============================");
        System.out.println("\t����Ա����(--�޸���Ʒ)\t\t");
        System.out.println("=============================");
        System.out.println("������Ҫ�޸ĵ���Ʒ���:");
        int id = input.nextInt();
        Commodity commodity = new CommodityDao().byidCommdity(id);
        if (commodity == null) {
            System.out.println("û����Ҫ����Ʒ���id!");
            return;
        }
        System.out.println("������Ҫ�޸���Ʒ����:");
        String Name = input.next();
        System.out.println("������Ҫ�޸���Ʒ�۸�:");
        String price = input.next();
        System.out.println("������Ҫ�޸���Ʒ���:");
        int inventory = input.nextInt();
        //ͬ���ݳ�
        commodity.setCommodityName(Name);
        commodity.setCommodityPrices(price);
        commodity.setInventory(inventory);
        int i = new CommodityDao().updatecommdity(commodity);
        if (i >= 0) {
            System.out.println("�޸���Ʒ�ɹ�!");
        } else{
        System.out.println("�����ظ��������£�");
    }

}

    private void delgoods() {
        System.out.println("=============================");
        System.out.println("\t����Ա����(--ɾ����Ʒ)\t\t");
        System.out.println("=============================");
        System.out.print("��������Ʒ���:");
        int id = input.nextInt();
        int i = new CommodityDao().CommodityDel(id);
        if (i > 0) {
            System.out.println("ɾ���ɹ�!");
        } else {
            System.out.println("ɾ��ʧ��!");
        }
    }

    private void selectgoods2() {
        System.out.println("=============================");
        System.out.println("\t����Ա����(--��ѯȫ����Ʒ)\t\t");
        System.out.println("=============================");
        List<Commodity> list = new CommodityDao().bySelectName();
        System.out.println("��Ʒ���\t��Ʒ����\t��Ʒ�۸�\t��Ʒ���\t�����ܶ�");
        for (Commodity item : list) {
            System.out.println(item.toString());
        }
    }

    private void selectgoods() {
        System.out.println("=============================");
        System.out.println("\t\t����Ա����(--��ѯ��Ʒ)\t\t");
        System.out.println("=============================");
        System.out.println("��������Ʒ����:");
        String Name = input.next();
        List<Commodity> list = new CommodityDao().bySelectName(Name);
        System.out.println("��Ʒ���\t��Ʒ����\t��Ʒ�۸�\t��Ʒ���\t�����ܶ�");
        for (Commodity item : list) {
            System.out.println(item.toString());
        }
    }


    private void goodsAdd() {
        System.out.println("=============================");
        System.out.println("\t\t����Ա����(--������Ʒ)\t\t");
        System.out.println("=============================");
        System.out.println("��������Ʒ����:");
        String Name = input.next();
        System.out.println("��������Ʒ�۸�:");
        String price = input.next();
        System.out.println("��������Ʒ���:");
        int inventory = input.nextInt();
        //ͬ���ݳ�
        if (new CommodityDao().CheckNameisTrue(Name) == null) {
            Commodity cs = new Commodity();
            cs.setCommodityName(Name);
            cs.setCommodityPrices(price);
            cs.setInventory(inventory);
            int i = new CommodityDao().CommodityAdd(cs);
            if (i >= 0) {
                System.out.println("������Ʒ�ɹ�!");
            }
            ;
        } else {
            System.out.println("�����ظ��������£�");
        }
    }
}
