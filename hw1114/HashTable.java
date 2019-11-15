package hw1114;

/* Name: Nix Woodcock
 * COSC 311 Fall 2019
 * hw1114
 * URL: https://github.com/awoodco1/COSC-311/hw1114/HashTable.java
 */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class HashTable {
	
	Random gen;
	
	public HashTable() {
		gen = new Random(97);
	}
	
	public int newVal() {
		return gen.nextInt(100);
	}
	
	public int h1(int val) {
		return val % 11;
	}

	public static void main(String[] args) throws IOException {
		HashTable hash = new HashTable();
		
		RandomAccessFile fp = new RandomAccessFile("data.ref", "rw");
		for (int i = 0; i < 11; i++) {
			fp.writeInt(-1);
		}
		
		for(int i = 0; i < 8; i++) {
			int val = hash.newVal();
			int key = hash.h1(val);
			fp.seek(key * 4);
			if(fp.readInt() == -1) {
				fp.seek(key * 4);
				fp.writeInt(val);
			}
		}
		
		fp.seek(0);
		for(int i = 0; i < fp.length() / 4; i++) {
			System.out.println("Byte Offset: 4 Int Index: " + i + " Int Value: " + fp.readInt());
		}
		
		fp.close();
	}

}
