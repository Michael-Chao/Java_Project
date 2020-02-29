package com.structure.queue;

import javax.sound.midi.Track;
import java.security.PrivateKey;
import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
    //测试一把
    //初始化一个队列
    ArrayQueue queue =  new ArrayQueue(3);
    boolean loop = true;
    Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("s(show):代表显示队列");
            System.out.println("e(exit):代表退出队列");
            System.out.println("a(add):代表添加数据");
            System.out.println("g(get):代表从队列中取出数据");
            System.out.println("h(head):代表显示头数据");
            char key = scanner.next().charAt(0);
            switch (key){
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
//使用数组模拟队列-编写一个ArrayQueue的类
class ArrayQueue{
    private int Maxsize;//表示数组的最大个数
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxsize){
        Maxsize = arrMaxsize;
        arr = new int[Maxsize];
        front = -1;
        rear = -1;
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == Maxsize - 1;
    }

    //判断队列是否为空
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
        rear++;
        arr[rear] = n;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否空
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列是空的，没有数据");
            return;
        }

        for (int i=0; i<arr.length; i++){
            System.out.printf("arr[%d] = %d\ns", i, arr[i]);
        }
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列是空的，没有数据");
        }
        return arr[front + 1];
    }
}
