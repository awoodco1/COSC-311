// Name: Adam "Nix" Woodcock
// COSC 311, Fall 2019
// Project: pp1008 - Programming Project #1
// URL: https://github.com/awoodco1/COSC-311/tree/master/pp1008

package pp1008;

import java.util.Random;

public class ServerQueue {
	public static final int NUMBER_OF_SERVERS = 4;
	public static final int RANDOM_NUM_SEED = 97;
	Customer head, tail;
	Random gen;
	public static final double AVG_ARRIVALS_PER_MINUTE = 2;
	public static final int MAX_SERVICE_TIME = 3;
	
	public ServerQueue() {
		head = null;
		tail = null;
		gen = new Random(RANDOM_NUM_SEED);
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
		while(cust.next != tail && cust.next != null) {
			cust = cust.next;
		}
		tail = cust;
		Customer popCust = cust.next;
		tail.next = null;
		return popCust;
	}
	
	public void generateCustomers() {
		double L = Math.exp(-AVG_ARRIVALS_PER_MINUTE);
		int k = 0;
		double p = 1.0;
		do {
			p = p * gen.nextDouble();
			k++;
		} while (p > L);
		for(int i = 0; i < k; i++) {
			Customer cust = new Customer(gen.nextInt(MAX_SERVICE_TIME) + 1);
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
		if(head != null) {
			for(Customer cust = head; cust != tail && cust != null; cust = cust.next) {
				size++;
			}
		}
		return size;
	}
	
	public Customer getHead() {
		return head;
	}
	
	public static void main(String[] args) {
		ServerQueue waitingCusts = new ServerQueue();
		CustomerList servedCusts = new CustomerList();
		Server[] servers = new Server[NUMBER_OF_SERVERS];
		
		for(int i = 0; i < servers.length; i++) {
			servers[i] = new Server();
		}
		
		for(int i = 0; i < 20; i++) {
			double avgTime = 0;
			int minTime = 0;
			int maxTime = 0;
			int numCusts = 0;
			System.out.println("Tick #: " + i);
			waitingCusts.generateCustomers();
			waitingCusts.waitCustomers();
			for(int j = 0; j < servers.length; j++) {
				if(servers[j].getCust() == null) {
					servers[j].newCust(waitingCusts);
				}
				else {
					servers[j].serveCust();
					if(servers[j].getServiceTime() >= servers[j].getCust().getServiceTime()) {
						servedCusts.addCust(servers[j].getCust());
						servers[j].resetServiceTime();
						servers[j].newCust(waitingCusts);
					}
				}
				if(servers[j].getCust() != null) {
					avgTime += servers[j].getCust().getWaitTime();
					if(minTime == 0 || servers[j].getCust().getWaitTime() < minTime)
						minTime = servers[j].getCust().getWaitTime();
					if(servers[j].getCust().getWaitTime() > maxTime)
						maxTime = servers[j].getCust().getWaitTime();
					numCusts++;
				}
			}
			if(waitingCusts.getHead() != null) {
				for(Customer cust = waitingCusts.getHead(); cust.getNext() != null; cust = cust.getNext()) {
					avgTime += cust.getWaitTime();
					if(minTime == 0 || cust.getWaitTime() < minTime)
						minTime = cust.getWaitTime();
					if(cust.getServiceTime() > maxTime)
						maxTime = cust.getWaitTime();
				}
			}
			if(servedCusts.getHead() != null) {
				for(Customer cust = servedCusts.getHead(); cust.getNext() != null; cust = cust.getNext()) {
					avgTime += cust.getWaitTime();
					if(minTime == 0 || cust.getWaitTime() < minTime)
						minTime = cust.getWaitTime();
					if(cust.getWaitTime() > maxTime)
						maxTime = cust.getWaitTime();
				}
			}
			numCusts += waitingCusts.size() + servedCusts.size();
			avgTime = avgTime / numCusts;
			System.out.println("# Customers in service: " + numCusts);
			System.out.println("# Customers with completed service: " + servedCusts.size());
			System.out.println("# Customers in queue: " + waitingCusts.size());
			System.out.println("Wait time: " + minTime + ", " + avgTime + ", " + maxTime);
		}
	}

}
