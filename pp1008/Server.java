// Name: Adam "Nix" Woodcock
// COSC 311, Fall 2019
// Project: pp1008 - Programming Project #1
// URL: https://github.com/awoodco1/COSC-311/tree/master/pp1008

package pp1008;

public class Server {
	
	Customer cust;
	int serviceTime;
	
	public Server() {
		cust = null;
		serviceTime = 0;
	}
	
	public void newCust(ServerQueue queue) {
		if(queue.getHead() != null)
			cust = queue.pop();
		else
			cust = null;
	}
	
	public void serveCust() {
		serviceTime++;
	}
	
	public Customer getCust() {
		return cust;
	}
	
	public int getServiceTime() {
		return serviceTime;
	}
	
	public void resetServiceTime() {
		serviceTime = 0;
	}

}
