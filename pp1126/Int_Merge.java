package pp1126;

/* Name: Nix Woodcock
 * COSC 311 Fall 2019
 * pp1126
 * URL: https://github.com/awoodco1/COSC-311/pp1126/Int_Merge.java
 */

import java.util.Random;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Int_Merge {
	
	public int data[];
	public long startTime;
	
	public Int_Merge(int input[]){
		data = input;
		startTime = System.nanoTime();
	}

	public long getTimeSpent() {
		return System.nanoTime() - startTime;
	}
	
	public int[] mergeSort(int arr[]) {
		if(arr.length == 1)
			return arr;
		
		int[] a1 = new int[arr.length / 2];
		int[] a2 = new int[arr.length - (arr.length / 2)];
		
		for(int i = 0; i < arr.length / 2; i++) {
			a1[i] = arr[i];
		}
		
		for(int i = arr.length / 2; i < arr.length; i++) {
			a2[i - (arr.length / 2)] = arr[i];
		}
		
		a1 = mergeSort(a1);
		a2 = mergeSort(a2);
		return merge(a1, a2);
	}
	
	public int[] merge(int[] a1, int[] a2) {
		int[] a3 = new int[0];
		while(a1.length > 0 && a2.length > 0) {
			if(a1[0] > a2[0]) {
				int[] temp = new int[a3.length+1];
				if(a3.length > 0) {
					for(int i = 0; i < a3.length; i++) {
						temp[i] = a3[i];
					}
				}
				temp[temp.length - 1] = a2[0];
				a3 = temp;
				temp = new int[a2.length - 1];
				if(temp.length > 0) {
					for(int i = 0; i < temp.length; i++) {
						temp[i] = a2[i + 1];
					}
				}
				a2 = temp;
			}
			else {
				int[] temp = new int[a3.length+1];
				if(a3.length > 0) {
					for(int i = 0; i < a3.length; i++) {
						temp[i] = a3[i];
					}
				}
				temp[temp.length - 1] = a1[0];
				a3 = temp;
				temp = new int[a1.length - 1];
				if(temp.length > 0) {
					for(int i = 0; i < temp.length; i++) {
						temp[i] = a1[i + 1];
					}
				}
				a1 = temp;
			}
		}
		
		if(a1.length > 0) {
			for(int i = 0; i < a1.length; i++) {
				int[] temp = new int[a3.length + 1];
				for(int j = 0; j < a3.length; j++) {
					temp[j] = a3[j];
				}
				a3 = temp;
				a3[a3.length - 1] = a1[i];
			}
		}
		
		if(a2.length > 0) {
			for(int i = 0; i < a2.length; i++) {
				int[] temp = new int[a3.length + 1];
				for(int j = 0; j < a3.length; j++) {
					temp[j] = a3[j];
				}
				a3 = temp;
				a3[a3.length - 1] = a2[i];
			}
		}
		return a3;
	}
	
	public static void main(String args[]) throws IOException{
		Random rand = new Random(97);
		System.out.println("Internal Mergesort:");
		for(int i = 0; i < 50; i++) {
			int[] input = new int[i + 1];
			for(int j = 0; j < i + 1; j++) {
				input[j] = rand.nextInt(50) + 1;
			}
			Int_Merge intmerge = new Int_Merge(input);
			intmerge.mergeSort(intmerge.data);
			System.out.println(intmerge.getTimeSpent());
		}
		System.out.println("External Mergesort:");
		for(int i = 0; i < 50; i++) {
			int[] input = new int[i + 1];
			for(int j = 0; j < i + 1; j++) {
				input[j] = rand.nextInt(50) + 1;
			}
			Ext_Merge extmerge = new Ext_Merge(input);
			extmerge.mergeSort(extmerge.data);
			System.out.println(extmerge.getTimeSpent());
		}
	}
}
