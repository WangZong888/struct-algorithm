package com.wsw.struct.doublelinkedlist;

/**
 * @program: struct-algorithm
 * @description:
 * @author: Mr.Wang
 * @create: 2019-08-01 16:12
 **/
@SuppressWarnings("all")
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.list();


        HeroNode2 heroNode1 = new HeroNode2(2, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(1, "吴用", "智多星");
        HeroNode2 heroNode3 = new HeroNode2(3, "林冲", "豹子头");
        HeroNode2 heroNode4 = new HeroNode2(4, "武松", "行者");
        HeroNode2 heroNode5 = new HeroNode2(5, "鲁智深", "花和尚");

        doubleLinkedList.addByOrder(heroNode1);
        doubleLinkedList.addByOrder(heroNode2);
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.addByOrder(heroNode4);
        doubleLinkedList.addByOrder(heroNode5);

       /* System.out.println("输出的信息为");
        doubleLinkedList.list();
        System.out.println();
        System.out.println("修改后的信息为");
        doubleLinkedList.update(new HeroNode2(6, "李逵", "黑旋风"));
        doubleLinkedList.list();*/

        System.out.println();
        System.out.println("删除后的信息为");
        doubleLinkedList.delete(6);
        doubleLinkedList.list();




    }
}

//TODO 声明一个双向链表 DoubleLinkedList
@SuppressWarnings("all")
class DoubleLinkedList {

    //定义一个头节点
    private HeroNode2 head;//不存放数据

    public DoubleLinkedList() {
        head = new HeroNode2(-1, "", "");
    }

    //TODO 添加一个头节点
    //按照顺序添加
    public void addByOrder(HeroNode2 heroNode2) {

        //定义一个辅助指针
        HeroNode2 temp = head;
        //标识要添加的英雄是否已经存在了
        boolean flag = false;
        while (true) {
            if (temp.next == null) {//遍历到了最后
                break;
            }
            if (temp.next.no > heroNode2.no) {//找到要添加的位置
                break;
            } else if (temp.next.no == heroNode2.no) {//已存在
                flag = true;
                break;
            }
            temp = temp.next;//后移
        }
        //退出循环
        if (flag) {//要添加已存在
            System.out.printf("要添加的%d已存在了", heroNode2.no);
        } else {//找到
            heroNode2.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = heroNode2;
            }
            temp.next = heroNode2;
            heroNode2.pre = temp;
        }
    }

    //TODO 遍历双向链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //定义辅助指针
        HeroNode2 temp = head.next;
        while (true) {
            System.out.printf("\n no=%d, name=%s, nickname=%s", temp.no, temp.name, temp.nickname);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }

    //TODO 修改双向链表
    //根据HeroNode2的编号no修改
    public void update(HeroNode2 heroNode2) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //定义一个辅助指针
        HeroNode2 temp = head.next;
        //标识是否找到了
        boolean flag = false;
        while (true) {
            if (temp.no == heroNode2.no) {//说明找到了
                flag = true;
                break;
            }
            if (temp.next == null) {//当前节点是最后节点
                break;
            }
            temp = temp.next;//后移
        }
        //退出循环，处理
        if (flag) {//找到了
            temp.name = heroNode2.name;
            temp.nickname = heroNode2.nickname;
        } else {
            System.out.printf("没有找到要修改的编号%d 的英雄", heroNode2.no);
        }
    }

    //TODO 删除双向链表
    //根据no进行删除
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //定义一个辅助指针
        HeroNode2 temp = head.next;
        //标识是否找到要删除的节点
        boolean flag = false;
        while (true) {
            if (temp.no == no) {
                //找到了
                flag = true;
                break;
            }
            if (temp.next == null) {//遍历到了最后
                break;
            }
            temp = temp.next;
        }
        //退出循环
        if(flag){//找到了要删除的节点
            temp.pre.next = temp.next;
            if(temp.next != null){//防止空指针异常
                temp.next.pre = temp.pre;
            }

        }else{
            System.out.printf("没有找到编号 %d 删除的英雄",no);
        }
    }

}


//TODO 声明一个HeroNode类（节点）
class HeroNode2 {

    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//默认为null
    public HeroNode2 pre;//默认为null

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
}
