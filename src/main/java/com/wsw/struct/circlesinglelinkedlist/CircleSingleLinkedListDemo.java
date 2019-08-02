package com.wsw.struct.circlesinglelinkedlist;

/**
 * @program: struct-algorithm
 * @description:
 * @author: Mr.Wang
 * @create: 2019-08-01 18:24
 * 约瑟夫问题
 **/
public class CircleSingleLinkedListDemo {

    public static void main(String[] args) {

        //创建环形链表的对象
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.list();
        System.out.println("-------------------------------");
        circleSingleLinkedList.josephu(1,2,5);
    }
}

//TODO 声明一个环形单向链表
class CircleSingleLinkedList {

    //定义first节点
    private Boy first;//默认为null



    /**
     * TODO 创建一个环形单向链表
     * @param num 小孩人数
     */
    public void add(int num) {
        //循环添加小孩节点
        //创建一个辅助指针,给一个无效值
        Boy currBoy=new Boy(-1);
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {//说明是第一个节点
                first = boy;//将第一个小孩给first
                first.next = first;//构成一个环状
                currBoy = first;
            }else{
                currBoy.next = boy;//添加小孩
                boy.next = first;//指向first，构成环形
                currBoy = boy;//currBoy后移
            }
        }
    }

    //TODO 遍历小孩节点
    public void list(){
        if(first==null){
            System.out.println("没有圈，没有小孩");
            return;
        }
        //定义辅助指针遍历
        Boy temp = first;
        while (true){
            System.out.printf("第"+temp.no+"小孩出圈\n");
            if(temp.next == first){//遍历圈中的小孩(圈并没有任何变化，first的位置没变)
             break;
            }
            temp = temp.next;
        }
    }



    /**
     *  //TODO 小孩出圈顺序
     * @param start 小孩的开始位置
     * @param step  小孩数的步长
     * @param num   总共有几个小孩
     *  思路：
     * 在开始时以first作为参照物
     *  找到出圈小孩的前一个节点
     */
    public void josephu(int start,int step,int num){
        if(start < 0 || start > num){
            System.out.println("输入"+start+"的起始位置不存在，请重新输入");
        }
        //在开始时以first作为参照物
        //TODO 1.所以先找出first的前一个节点temp
        Boy temp = first;
        while (true){
            if(temp.next == first){//找到temp位置
                break;
            }
            temp = temp.next;
        }

        //TODO 2.根据小孩开始的位置,以first为参照，将temp移动start-1次
        for (int i = 0; i < start-1; i++) {
            temp = temp.next;
        }


        while(true){
            //TODO 3.开始数数，temp移动的次数为(step-1)
            for (int j = 0; j < step-1 ; j++) {
                temp = temp.next;
            }
            //TODO 4.退出时说明找到了要出圈节点的前一个节点temp
            //将数到的节点出圈
            System.out.println("小孩"+temp.next.no+"出圈了");
            if(temp.next == temp){//圈中只有一个时 就退出
                break;
            }
            //删除出圈的节点
            temp.next = temp.next.next;
        }
        //退出后将留在圈中的小孩打印出来
        System.out.println("留在圈中的小孩为："+temp.no);

    }

}


//TODO 声明一个Boy类作为节点
class Boy {
    public int no;
    public Boy next;//默认为null

    public Boy(int no) {
        this.no = no;
    }
}