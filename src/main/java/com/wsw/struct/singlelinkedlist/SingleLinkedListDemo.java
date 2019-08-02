package com.wsw.struct.singlelinkedlist;

/**
 * @program: struct-algorithm
 * @description:
 * @author: Mr.Wang
 * @create: 2019-08-01 14:54
 **/
@SuppressWarnings("all")
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        //创建一个单向链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.list();

        HeroNode heroNode1 = new HeroNode(2,"宋江","及时雨");
        HeroNode heroNode2 = new HeroNode(1,"吴用","智多星");
        HeroNode heroNode3 = new HeroNode(5,"林冲","豹子头");
        HeroNode heroNode4 = new HeroNode(4,"武松","行者");
        HeroNode heroNode5 = new HeroNode(3,"鲁智深","花和尚");

        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode5);

        System.out.println("链表的信息为：");
        singleLinkedList.list();

        System.out.println();
       /* System.out.println("修改后链表的信息为：");
        singleLinkedList.update(new HeroNode(1,"李逵","黑旋风"));
        singleLinkedList.list();*/

        System.out.println("删除后的链表信息");
        singleLinkedList.delete(1);
        singleLinkedList.delete(5);
        singleLinkedList.list();

    }

}


//TODO 声明一个单向链表
@SuppressWarnings("all")
class SingleLinkedList{
    //定义一个头节点
    private HeroNode head;//不存发数据

    public SingleLinkedList() {
       head = new HeroNode(-1,"","");
    }


    //TODO 创建一个添加节点的方法
    //按顺序插入
    public void addByOrder(HeroNode heroNode){

        //定义辅助指针
        HeroNode temp = head;
        //标识要添加的节点是否已经存在
        boolean flag = false;
        while (true){
            if(temp.next == null){//遍历到了最后
                break;
            }
            if(temp.next.no > heroNode.no){//找到要添加的位置
                break;
            }else if(temp.next.no == heroNode.no){//要添加的编号已存在
                flag = true;
                break;
            }
            temp = temp.next; //后移
        }
        //出循环进行判断
        if(flag){//说明要添加的节点已存在
            System.out.printf("要添加的编号 %d 已存在",heroNode.no);
        }else {//找到要添加的位置
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //TODO 根据HeroNode的 no修改内容
    public  void update(HeroNode heroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //定义辅助指针
        HeroNode temp  = head.next;
        //标识是否找到
        boolean flag = false;
        while(true){
            if(temp.no == heroNode.no){//说明已经找到了
                flag = true;
                break;
            }
            if(temp.next == null){//说明遍历到了最后
                break;
            }
            temp = temp.next;//后移
        }
        //退出循环，进行处理
        if(flag){
            //说明找到
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else{
            System.out.printf("不存在编号 %d 的英雄",heroNode.no);
        }

    }


    //TODO 根据no删除英雄
    //思路：
    //我们要找到要删除节点的上一个节点
    public void delete(int no){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //定义辅助指针-要删除节点的上一个节点
        HeroNode temp = head;
        //标识是否已经找到了
        boolean flag = false;
        while(true){
            if(temp.next.no == no){//说明已经找到了
                flag = true;
                break;
            }
            if(temp.next.next == null){//遍历到了最后
                break;
            }
            temp = temp.next;//后移
        }
        //退出循环，进行处理
        if (flag) {//说明找到
            temp.next = temp.next.next;
        }else{
            System.out.printf("没有找到要删除的编号 %d ",no);
        }
    }

    //TODO 遍历链表
    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //定义一个辅助指针
        HeroNode temp = head.next;
        while(true){
            System.out.printf("\nno=%d, name=%s, nickname=%s",temp.no,temp.name,temp.nickname);
            if(temp.next==null){
                //遍历到了最后
                break;
            }
            temp = temp.next;
        }
    }
}


//TODO 声明一个节点类
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//默认为null

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
}