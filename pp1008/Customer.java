// Name: Adam "Nix" Woodcock
// COSC 311, Fall 2019
// Project: pp1008 - Programming Project #1
// URL: https://github.com/awoodco1/COSC-311/tree/master/pp1008

package pp1008;

public class Customer {
	int wait;
	Customer next;
	int serviceTime;
	
	public Customer(int time) {
		wait = 0;
		next = null;
		serviceTime = time;
	}
	
	public void waitTime() {
		if(serv == null) {
			wait++;
		}
	}
	public int getServiceTime() {
		return serviceTime;
	}
	
	public Customer getNext() {
		return next;
	}
	
	public int getWaitTime() {
		return wait;
	}

}
