package com.thread.learning;

class MyThread2 implements Runnable{

	private int ticketsCont = 5;//一共有五张火车票
	
	@Override
	public void run() {
		while(ticketsCont > 0){
			ticketsCont--;//如果有票，就卖掉一张
			System.out.println(Thread.currentThread().getName() + "卖了1张票，剩余票数为："+ticketsCont);
		}
	}
	
}

public class TicketsRunnable {

	public static void main(String[] args) {
		MyThread2 m = new MyThread2();
		//创建三个线程 模拟三个窗口售票
		Thread t1 = new Thread(m, "窗口1");
		Thread t2 = new Thread(m, "窗口2");
		Thread t3 = new Thread(m, "窗口3");
		
		//启动三个线程，既三个线程开始卖票
		t1.start();
		t2.start();
		t3.start();
	}
}
