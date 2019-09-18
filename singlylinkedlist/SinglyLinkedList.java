package singlylinkedlist;

/**
 *  Modified from Drozdek, Data Structures and Algorithms in Java
 *  
 *  Name: Adam "Nix" Woodcock
 *  COSC 311 FA19
 *  hw0912
 *  URL: https://github.com/awoodco1/COSC-311/tree/master/singlylinkedlist
 */

public class SinglyLinkedList {

   public class Node {
      int   data;
      Node  next;
      
      public Node () {
         this (0, null);
      }
      
      public Node (int data) {
         this(data, null);
      }
      
      public Node(int data, Node next) {
         this.data = data;
         this.next = next;
      }
   }
   
   Node  head, tail;
   
   public SinglyLinkedList() {
      head = tail = null;
   }
   
   public void insert(int data) {
	   if (isEmpty() )
		   head = tail = new Node(data);
	   else if(head.data >= data)
		   head = new Node(data, head);
	   else {
		   Node p;
		   for (p = head; 
				   p.next != null && p.next.data < data; 
				   p = p.next) ;
			   if (p != null) {
				   p.next = new Node(data, p.next);
				   if (p == tail)
					   tail = p.next;
			   }
	   }
   }
   
   public void delete(int data) {
      if (!isEmpty() )
         if ( head == tail && data == head.data)
            head = tail = null;
         else if (data == head.data )
            head = head.next;
         else {   
            Node p, q;
            for ( p= head, q = head.next; 
                  q != null && !(q.data == data ); 
                  p = p.next, q = q.next) ;
            if (q != null) {
               p.next = q.next;
               if (q == tail )
                  tail = p;
            }
         } 
   }
   
   public boolean isEmpty() {
      return head == null;
   }
   
   public String  toString() {
      String s = "";
      if (head == null) { 
         return "Empty String" ;
      }
      for (Node p = head; p != null; p = p.next) 
         s += p.data + " ";
      return s;
   }
     
   public static void main(String[] args) {
	   SinglyLinkedList list = new SinglyLinkedList();
	      
	   System.out.println("Execution begun");
	   System.out.println("initial list: " + list );
	       
	   // testing
	   list.insert(3);
	   list.insert(5);
	   System.out.println(list);
	   
	   list.insert(2);
	   list.insert(2);
	   list.insert(2);
	   list.insert(7);
	   list.insert(6);
	   System.out.println("list after inserts: " + list);
	   
	   list.delete(2);
	   list.delete(5);
	   list.delete(7);
	   System.out.println("list after deletes: " + list);
	       
	   System.out.println("Execution terminated");
   }
}