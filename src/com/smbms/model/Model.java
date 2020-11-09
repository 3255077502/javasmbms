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
            admin = new AdminDao().doLogin(u);
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
            System.out.println("1.�����û� 2.������Ʒ 3.�������Ա 4.�˳�");
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
                    manger();
                    break;
                case 4:
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
     * ��������Ա
     */
    private void manger() {
        int choose = 0;
        do {
            System.out.println("1.��������Ա 2.��ѯ����Ա 3.�޸����� 4.�޸Ĺ���Ա 5.ɾ������Ա  6.������ҳ");
            System.out.print("��ѡ��:");
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
        System.out.println("\t����Ա����(--�޸Ĺ���Ա)\t\t");
        System.out.println("=============================");
        System.out.print("���������޸Ĺ���Ա���:");
        Admin admin = new AdminDao().doLogin(input.nextInt());
        if (admin==null){
            System.out.println("�Ҳ�����Ҫ�޸ĵĹ���Ա!");
            return;
        }
        System.out.print("������Ҫ�޸ĵĹ���Ա�˻���");
        String adminname =input.next();
        System.out.print("������Ҫ�޸ĵĹ���Ա���룺");
        String adminpwd =input.next();
        //��������
        Admin admin1= new Admin();
        admin1.setUsername(adminname);
        admin1.setPassword(adminpwd);
        if (new AdminDao().add(admin1)==1){
            System.out.println("�����ɹ�!");
        }else {
            System.out.println("����ʧ��!");
        }
    }

    private void delAdmin() {
        System.out.println("=============================");
        System.out.println("\t����Ա����(--ɾ������Ա)\t\t");
        System.out.println("=============================");
        System.out.print("��������ɾ������Ա���:");
        int id = input.nextInt();
        if (new AdminDao().delte(id)==1){
            System.out.println("ɾ���ɹ���");
        }else{
            System.out.println("ɾ��ʧ�ܣ�");
        }
    }

    //��������Ա
    private void addAdmin() {
        System.out.println("=============================");
        System.out.println("\t����Ա����(--��������Ա)\t\t");
        System.out.println("=============================");
        System.out.print("���������Ա�˻���");
        String adminname =input.next();
        System.out.print("���������Ա���룺");
        String adminpwd =input.next();
        //��������
        Admin admin1= new Admin();
        admin1.setUsername(adminname);
        admin1.setPassword(adminpwd);
        if (new AdminDao().add(admin1)==1){
            System.out.println("�����ɹ�!");
        }else {
            System.out.println("����ʧ��!");
        }
    }
    //�޸Ĺ���Ա
    private void updateAdminPwd() {
        System.out.println("=============================");
        System.out.println("\t����Ա����(--�޸Ĺ���Ա����)\t\t");
        System.out.println("=============================");
        System.out.print("��������ľ�����:");
        String oldpwd = input.next();
        if (!admin.getPassword().equals(oldpwd)) {
            System.out.println("���������������");
        }
        System.out.print("������������");
        String newpwd = input.next();
        if (new AdminDao().update(newpwd) > 0) {
            System.out.println("�޸ĳɹ���");
            admin = null;
            show();
        } else {
            System.out.println("�޸�ʧ�ܣ�");

        }
    }
    //
    private void selectAdmin() {
        System.out.println("=============================");
        System.out.println("\t����Ա����(--��ѯ����Ա)\t\t");
        System.out.println("=============================");
        //���ò�ѯ����
        System.out.println("����Ա���\t����Ա�˻�\t����Ա����\t����ԱȨ��");
        List<Admin> admins = new AdminDao().byidSelect();
        System.out.println(admins.size());
        for (Admin item: admins
             ) {
            item.toString();
        }
    }

    /**
     * �����û�
     */

    public void user() {
        int choose = 0;
        do {
            System.out.println("1.�����û� 2.��������ģ����ѯ 3.��ѯ�����û� 4.�޸��û� 5.ɾ���û� 6.������ҳ");
            System.out.print("��ѡ��:");
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

    //ɾ��
    private void deluser() {
        System.out.println("=============================");
        System.out.println("\t����Ա����(--ɾ���û�)\t\t");
        System.out.println("=============================");
        System.out.print("������Ҫɾ�����û�id:");
        int id = input.nextInt();
        int i = new UserDao().UserDel(id);
        if (i < 0) {
            System.out.println("ɾ��ʧ�� !");
            return;
        } else {
            System.out.println("ɾ���ɹ� !");

        }
    }

    //�޸��û�
    private void updateUser() {
        System.out.println("=============================");
        System.out.println("\t����Ա����(--�޸��û�)\t\t");
        System.out.println("=============================");
        System.out.print("������Ҫ�޸ĵ��û�id:");
        int id = input.nextInt();
        User user = new UserDao().byidselectUser(id);
        if (user == null) {
            System.out.println("û����Ҫ���û�id");
            return;
        }
        System.out.println("������Ҫ�޸ĵ��û���:");
        String Name = input.next();
        System.out.println("������Ҫ�޸ĵ����:");
        String price = input.next();
        int i = new UserDao().updateUser(user);
        if (i >= 0) {
            System.out.println("�޸��û��ɹ�!");
        } else {
            System.out.println("�����ظ��������£�");
        }
    }

    //��ѯ����
    private void selectUserAll() {
        System.out.println("=============================");
        System.out.println("\t����Ա����(--��ѯȫ���û�)\t\t");
        System.out.println("=============================");
        List<User> list = new UserDao().bySelectNameAll();
        System.out.println("����\t�û���\t���\t�����ܶ�");
        for (User item : list) {
            System.out.println(item.toString());
        }
    }

    //��������ģ����ѯ
    private void selectUser() {
        System.out.println("=============================");
        System.out.println("\t\t����Ա����(--��ѯ�û�)\t\t");
        System.out.println("=============================");
        System.out.print("�������û���:");
        String Name = input.next();
        List<User> list = new UserDao().bySelectName(Name);
        System.out.println("����\t�û���\t���\t�����ܶ�");
        for (User item : list) {
            System.out.println(item.toString());
        }
    }

    //�����û�
    private void userAdd() {
        System.out.println("=============================");
        System.out.println("\t\t����Ա����(--�����û�)\t\t");
        System.out.println("=============================");
        System.out.print("�������û���:");
        String Name = input.next();
        System.out.print("���������:");
        int sum = input.nextInt();
        User user = new User();
        user.setUsername(Name);
        user.setSum(sum);
        UserDao userDao = new UserDao();
        int i = userDao.UserAdd(user);
        if (i > 0) {
            System.out.println("�����ɹ���");
        } else {
            System.out.println("����ʧ�ܣ�");
        }
    }

    /**
     * ������Ʒ
     */

    public void goods() {
        int choose = 0;
        do {
            System.out.println("1.������Ʒ 2.������Ʒ���Ʋ�ѯ 3��ѯȫ����Ʒ. 4.�޸���Ʒ 5.ɾ����Ʒ 6.������ҳ");
            System.out.print("��ѡ��:");
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
     * �޸���Ʒ
     */
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
        System.out.print("������Ҫ�޸���Ʒ����:");
        String Name = input.next();
        System.out.print("������Ҫ�޸���Ʒ�۸�:");
        String price = input.next();
        System.out.print("������Ҫ�޸���Ʒ���:");
        int inventory = input.nextInt();
        //ͬ���ݳ�
        commodity.setCommodityName(Name);
        commodity.setCommodityPrices(price);
        commodity.setInventory(inventory);
        int i = new CommodityDao().updatecommdity(commodity);
        if (i >= 0) {
            System.out.println("�޸���Ʒ�ɹ�!");
        } else {
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
