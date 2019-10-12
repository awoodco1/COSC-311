// Name: Adam "Nix" Woodcock
// COSC 311, Fall 2019
// Project: pp1008 - Programming Project #1
// URL: 

package pp1008;

public class Server {
	
	ServerQueue queue;
	Customer cust;
	int serviceTime;
	
	public Server(ServerQueue q) {
		queue = q;
		cust = null;
		serviceTime = 0;
	}
	
	public void newCust() {
		if(queue.size() != 0)
			cust = queue.pop();
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

}
