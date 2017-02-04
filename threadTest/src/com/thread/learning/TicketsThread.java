package com.thread.learning;

class MyThread extends Thread{
	
	private int ticketsCont = 5;//一共有五张火车票
	
	private String name;//窗口，也就是线程的名字。
	
	public MyThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		while(ticketsCont > 0){
			ticketsCont--;//如果有票，就卖掉一张
			System.out.println(name + "卖了1张票，剩余票数为："+ticketsCont);
		}
	}
}

public class TicketsThread extends Thread{

	public static void main(String[] args) {
		//创建三个线程，模拟三个窗口售票
		MyThread t1 = new MyThread("窗口1");
		MyThread t2 = new MyThread("窗口2");
		MyThread t3 = new MyThread("窗口3");
		
		//启动三个线程，也就是窗口，开始卖票
		t1.start();
		t2.start();
		t3.start();
	}
}
