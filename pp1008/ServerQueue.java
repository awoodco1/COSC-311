// Name: Adam "Nix" Woodcock
// COSC 311, Fall 2019
// Project: pp1008 - Programming Project #1
// URL: 

package pp1008;

import java.util.Random;

public class ServerQueue {
	Customer head, tail;
	Random gen;
	int arrivals, maxService;
	
	public ServerQueue() {
		head = null;
		tail = null;
		gen = new Random(97);
		arrivals = 2;
		maxService = 12;
		
	}
	
	public void push(Customer cust) {
		if(head == null) {
			tail = head = cust;
		}
		else {
			cust.next = head;
			head = cust;
		}
	}
	
	public Customer pop() {
		if(tail == null) {
			return null;
		}
		Customer cust = head;
		while(cust.next != tail || cust.next != null) {
			cust = cust.next;
		}
		tail = cust;
		Customer popCust = cust.next;
		tail.next = null;
		return popCust;
	}
	
	public void generateCustomers() {
		double L = Math.exp(-arrivals);
		int k = 0;
		double p = 1.0;
		do {
			p = p * gen.nextDouble();
			k++;
		} while (p > L);
		for(int i = 0; i < k; i++) {
			Customer cust = new Customer(gen.nextInt(maxService));
			this.push(cust);
		}
	}
	
	public void waitCustomers() {
		if(tail != null) {
			for(Customer cust = head; cust.next != null; cust = cust.next) {
				cust.waitTime();
			}
		}
	}
	
	public int size() {
		int size = 0;
		for(Customer cust = head; cust.next != null; cust = cust.next) {
			size++;
		}
		return size;
	}
	
	public static void main() {
		ServerQueue waitingCusts = new ServerQueue();
		ServerQueue servedCusts = new ServerQueue();
		Server[] servers = new Server[4];
		
		for(int i = 0; i < servers.length; i++) {
			servers[i] = new Server(waitingCusts);
		}
		
		for(int i = 0; i < 20; i++) {
			waitingCusts.waitCustomers();
			waitingCusts.generateCustomers();
			for(int j = 0; j < servers.length; j++) {
				if(servers[j].getCust() == null) {
					servers[j].newCust();
				}
				else {
					servers[j].serveCust();
					if(servers[j].getServiceTime() >= servers[j].getCust().getServiceTime()) {
						servedCusts.push(servers[j].getCust());
						servers[j].newCust();
					}
				}
			}
		}
	}

}
