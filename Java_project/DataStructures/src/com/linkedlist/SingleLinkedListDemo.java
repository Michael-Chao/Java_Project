package com.linkedlist;

import java.util.List;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");
        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        singleLinkedList.addByOrder(node1);
        singleLinkedList.addByOrder(node4);
        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node2);

        //显示一把
        System.out.println("最初的链表:");
        singleLinkedList.list();

        //测试修改节点的代码
        HeroNode heroNode2 = new HeroNode(2, "卢卢", "麒麟玉" );
        singleLinkedList.update(heroNode2);

        System.out.println();
        System.out.println("修改后的链表：");
        singleLinkedList.list();
    }
}

//定义SingleLinkedList,管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //添加列表到单向链表
    //思路:当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        //遍历列表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将这个节点的next指向新的节点
        temp.next = heroNode;
    }

    //第二种添加方法，根据排名将英雄添加到指定位置
    public void addByOrder(HeroNode heroNode){
        //因为头结点不能动，所以我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，所以我们找的temp是位于添加位置的前一个节点，否则插不进去
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){//说明指针已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no){
                break;
            }
            else if (temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.println("编号已存在");
        }
        else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改（这个改了相当于添加）
    //说明
    //1.根据newHeroNode的no来修改
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == newHeroNode.no){
                flag = true;
                break;
            }
           temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            System.out.printf("没有找到编号%d的节点", newHeroNode.no);
        }
    }

    //显示链表（遍历）
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断链表是否到最后
            if (temp == null) {
                break;
            }
            System.out.println(temp);//打印出来
            temp = temp.next;//将temp后移
        }
    }
}

//定义HeroNode对象，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    //为了显示方法，我们重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' + '}';
    }
}