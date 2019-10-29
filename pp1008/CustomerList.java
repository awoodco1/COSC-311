// Name: Adam "Nix" Woodcock
// COSC 311, Fall 2019
// Project: pp1008 - Programming Project #1
// URL: https://github.com/awoodco1/COSC-311/tree/master/pp1008

package pp1008;

public class CustomerList {
	public Customer head;
	public Customer tail;
	
	public CustomerList() {
		head = null;
		tail = null;
	}
	
	public void addCust(Customer cust) {
		if(head == null) {
			head = cust;
			tail = cust;
		}
		else {
			tail.next = cust;
			tail = cust;
		}
		cust.next = null;
	}
	
	public Customer getHead() {
		return head;
	}
	
	public Customer getTail() {
		return tail;
	}
	
	public int size() {
		int size = 0;
		if(head != null) {
			for(Customer cust = head; cust != null && cust != tail; cust = cust.next) {
				size++;
			}
		}
		return size;
	}

}
