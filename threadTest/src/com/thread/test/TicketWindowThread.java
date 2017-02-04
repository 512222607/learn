package com.thread.test;

public class TicketWindowThread extends Thread{

	private Ticket ticket;
	private String name;

	public TicketWindowThread(String name) {
		this.name = name;
	}
	
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public void run() {
		synchronized(ticket) {
			while(ticket.getCount() > 0){			
				ticket.notifyAll();
				sale();
				try {
					ticket.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			ticket.notifyAll();
		}
	}
	
	private void sale(){
		ticket.setCount(ticket.getCount()-1);
		System.out.println(name + "卖了1张票，剩余票数为："+ticket.getCount());
	}
}
