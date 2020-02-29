package com.structure.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        //初始化一个队列
        CircleArray queue = new CircleArray(4);//设置的是4，但是队列的有效数据最大的是3
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("s(show):代表显示队列");
            System.out.println("e(exit):代表退出队列");
            System.out.println("a(add):代表添加数据");
            System.out.println("g(get):代表从队列中取出数据");
            System.out.println("h(head):代表显示头数据");
            char key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输出一个数：");
                    int n = scanner.nextInt();
                    queue.addQueue(n);
                    break;
                case 'g':
                    try {
                        int result = queue.getQueue();
                        System.out.printf("队列头的数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}

class CircleArray{
    private int Maxsize;//表示数组的最大个数
    //1.  front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    //front 的初始值 = 0
    private int front;
    //2.  rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    //rear 的初始值 = 0
    private int rear;
    private int[] arr;//该数组用于存放数据，模拟队列

    //队列构造器
    public CircleArray(int arrMaxsize) {
        Maxsize = arrMaxsize;
        arr = new int[Maxsize];
    }
    public boolean isFull(){
        return (rear + 1) % Maxsize == front;
    }
    public boolean isEmpty(){
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % Maxsize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否空
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        int result;
        result = arr[front];
        front = (front + 1) % Maxsize;
        return result;
    }
    //显示队列的所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列是空的，没有数据");
            return;
        }
        for (int i=front; i<rear + size(); i++){
            System.out.printf("arr[%d] = %d\n", i%Maxsize, arr[i%Maxsize]);
        }
    }

    //有效数据个数
    public int size(){
        return (rear + Maxsize - front) % Maxsize;
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列是空的，没有数据");
        }
        return arr[front + 1];
    }
}