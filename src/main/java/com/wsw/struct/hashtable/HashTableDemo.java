package com.wsw.struct.hashtable;

import java.util.Scanner;

/**
 * @program: struct-algorithm
 * @description:
 * @author: Mr.Wang
 * @create: 2019-08-01 11:20
 **/
public class HashTableDemo {

    public static void main(String[] args) {

        //创建hashtable
        HashTable hashTable = new HashTable(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加员工");
            System.out.println("list:显示员工");
            System.out.println("find:找员工");
            System.out.println("delete:删除员工");
            System.out.println("exit:退出循环");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入name");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.addToHashTable(emp);
                    break;
                case "list":
                    hashTable.listFromHashTable();
                    break;
                case "find":
                    System.out.println("请输入编号id");
                    id = scanner.nextInt();
                    hashTable.findByIdHashTable(id);
                    break;
                case "delete":
                    System.out.println("请输入要删除编号的id");
                    id = scanner.nextInt();
                    hashTable.deleteByIdHashTable(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }

        }
    }
}

//TODO 声明一个哈希表，HashTable - > (数组 + 链表) | (数组+二叉树)
class HashTable {

    //将链表添加到数组中
    private EmpLinkedlist[] empLinkedlistArray;
    private int size;

    //构造器
    public HashTable(int size) {
        this.size = size;
        empLinkedlistArray = new EmpLinkedlist[size];
        //一定要注意，在初始化时候应该要给每一条链表初始化
        for (int i = 0; i < size; i++) {
            empLinkedlistArray[i] = new EmpLinkedlist();
        }
    }

    //TODO 往数组中添加员工-实质是由数组往链表中添加元素
    public void addToHashTable(Emp emp) {
        int linkedListNo = hashFun(emp.id);
        empLinkedlistArray[linkedListNo].add(emp);
    }

    //TODO 遍历数组->实质就是去遍历链表
    public void listFromHashTable() {
        for (int i = 0; i < size; i++) {
            empLinkedlistArray[i].list(i);
        }
    }

    //TODO 根据id查找数组
    public void findByIdHashTable(int id) {
        int linkedListNo = hashFun(id);
        Emp byId = empLinkedlistArray[linkedListNo].findById(id);
        if (byId != null) {
            System.out.println("编号" + id + "已找到");
        } else {
            System.out.println("编号" + id + "没有找到");
        }
    }
    //TODO  根据id进行删除雇员
    public void deleteByIdHashTable(int id){
        int linkedListNo = hashFun(id);
        empLinkedlistArray[linkedListNo].deleteById(id);
    }



    //TODO 需要一个散列函数来获取到对应的那条链表
    public int hashFun(int id) {
        return (id % size);
    }
}


//TODO 声明一个链表，LinkedList
class EmpLinkedlist {
    //头节点作为第一个Emp，要存数据
    private Emp head;

    //TODO add->添加雇员->按顺序加入
    public void add(Emp emp) {

        if (head == null) {
            //将头节点存第一个Emp
            head = emp;
            return;
        }
        //与头节点进行单独判断
        if (head.id > emp.id) {//说明要添加的雇员在头节点之前
            emp.next = head;
            head = emp;
            return;
        } else if (head.id == emp.id) {
            System.out.println("要填的雇员的编号" + emp.id + "已存在");
            return;
        }

        //定义一个辅助指针进行遍历
        Emp curEmp = head;
        //标识要添加的雇员是否已经存在
        boolean flag = false;
        while (true) {
            if (curEmp.next == null) {//说明遍历到了最后一个节点
                break;
            }
            if (curEmp.next.id > emp.id) {//说明找到了要添加的位置
                break;
            } else if (curEmp.next.id == emp.id) {//说明要添加的员工已存在
                flag = true;
                break;
            }
            curEmp = curEmp.next;//后移
        }
        //退出while，开始添加员工
        if (flag) {
            System.out.println("要添加得雇员编号" + emp.id + "已存在");
        } else {
            //添加员工
            emp.next = curEmp.next;
            curEmp.next = emp;
        }
    }

    //TODO list->遍历链表，打印出雇员
    public void list(int i) {
        if (head == null) {
            System.out.println("链表" + (i + 1) + "为空");
            return;
        }
        //定义一个辅助指针
        System.out.print("第" + (i + 1) + "条链表的信息为 ");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> i=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {//遍历到了最后一个节点
                break;
            }
            curEmp = curEmp.next;//后移
        }
        System.out.println();
    }

    //TODO 根据id查找雇员
    //找到返回Emp,没有找到返回null
    public Emp findById(int id) {
        if (head == null) {
            return null;
        }
        //定义辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {//到最后节点
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//后移
        }
        return curEmp;
    }

    //TODO 根据id删除雇员
    public void deleteById(int id){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        //需要单独将head节点拿出来处理
        if(head.id == id ){//说明head节点我们要删除的雇员
            head = head.next;
            System.out.println("编号为"+id+"的已经删除了");
            return;
        }
        //定义一个辅助指针 ,找到要删除节点的前一个节点
        Emp curEmp = head;
        //标识一个是否找到
        boolean flag = false;
        while (true){
            if(curEmp.next == null){//说明遍历到了最后一个节点
                break;
            }
            if(curEmp.next.id == id){//说明找到
                flag = true;
                break;
            }
            curEmp= curEmp.next;//后移
        }
        //退出循环
        if(flag){//找到
            curEmp.next = curEmp.next.next;
            System.out.println("编号为"+id+"的雇员已删除");
        }else{
            System.out.println("编号为"+id+"的雇员不存在");
        }

    }


}

//TODO 声明一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;//默认为null

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}