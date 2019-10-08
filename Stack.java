// Adam "Nix" Woodcock
// COSC 311, FA19
// hw0926a
// URL: https://github.com/awoodco1/COSC-311/blob/master/Stack.java

public class Stack{
	public static class Node{
		String val; 
		Node next;
		
		public Node(String str){
			val = str;
			next = null;
		}
		
		public void setVal(String str){
			val = str;
		}
		
		public void setNext(Node n){
			next = n;
		}
		
		public String getVal(){
			return val;
		}
		
		public Node getNext(){
			return next;
		}
	}

	Node top;
	
	public Stack(){
		top = null;
	}
	
	public void push(Node n){
		if(top == null){
			top = n;
		}
		else{
			n.setNext(top);
			top = n;
		}
	}
	
	public Node pop(){
		Node n;
		if(top == null){
			return null;
		}
		else if(top.getNext() == null){
			n = top;
			top = null;
			return n;
		}
		else{
			n = top;
			top = top.getNext();
			return n;
		}
	}
	
	public static void main(String[] args){
		Stack s = new Stack();
		boolean pal = true;
		for(String word : args){
			s.push(new Node(word));
		}
		for(String word : args){
			Node n = s.pop();
			pal = word.equals(n.getVal());
			if(!pal){
				break;
			}
		}
		System.out.println(pal);
	}
}

