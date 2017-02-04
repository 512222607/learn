package com.thread.test;

public class Window {

	public static void main(String[] args) {		
		Ticket t = new Ticket();
		t.setCount(5);
		TicketWindowThread t1 = new TicketWindowThread("窗口1");
		t1.setTicket(t);
		TicketWindowThread t2 = new TicketWindowThread("窗口2");
		t2.setTicket(t);
		TicketWindowThread t3 = new TicketWindowThread("窗口3");
		t3.setTicket(t);
		
		t2.start();
		t1.start();
		t3.start();
	}
	
}
