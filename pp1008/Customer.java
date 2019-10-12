// Name: Adam "Nix" Woodcock
// COSC 311, Fall 2019
// Project: pp1008 - Programming Project #1
// URL: 

package pp1008;

public class Customer {
	int wait;
	Server serv;
	Customer next;
	int serviceTime;
	
	public Customer(int time) {
		wait = 0;
		next = null;
		serv = null;
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

}
