package pp1126;

/* Name: Nix Woodcock
 * COSC 311 Fall 2019
 * pp1126
 * URL: https://github.com/awoodco1/COSC-311/pp1126/Ext_Merge.java
 */

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ext_Merge {
	RandomAccessFile data;
	public long startTime;
	public int fileCount;
	
	public Ext_Merge(int input[]) throws IOException {
		data = new RandomAccessFile("data.ref", "rw");
		for(int i = 0; i < input.length; i++) {
			data.writeInt(input[i]);
		}
		startTime = System.nanoTime();
		fileCount = 0;
	}
	
	public long getTimeSpent() {
		return System.nanoTime() - startTime;
	}
	
	public RandomAccessFile mergeSort(RandomAccessFile input) throws IOException{
		if(input.length() == 4) {
			return input;
		}
		
		RandomAccessFile f1 = new RandomAccessFile("file" + fileCount + ".ref", "rw");
		fileCount++;
		RandomAccessFile f2 = new RandomAccessFile("file" + fileCount + ".ref", "rw");
		fileCount++;
		
		input.seek(0);
		int inputPos = 0;
		
		for(int i = inputPos; i < input.length() / 2; i += 4) {
			f1.writeInt(input.readInt());
			inputPos += 4;
		}
		
		for(int i = inputPos; i < input.length(); i += 4) {
			f2.writeInt(input.readInt());
		}
		
		f1 = mergeSort(f1);
		f2 = mergeSort(f2);
		return merge(f1, f2);
	}
	
	public RandomAccessFile merge(RandomAccessFile f1, RandomAccessFile f2) throws IOException {
		RandomAccessFile f3 = new RandomAccessFile("file" + fileCount + ".ref", "rw");
		fileCount++;
		
		int f1Pos = 0;
		int f2Pos = 0;
		
		while(f1Pos < f1.length() && f2Pos < f2.length()) {
			f1.seek(f1Pos);
			f2.seek(f2Pos);
			if(f1.readInt() > f2.readInt()) {
				f2.seek(f2Pos);
				f3.writeInt(f2.readInt());
				f2Pos += 4;
			}
			else {
				f1.seek(f1Pos);
				f3.writeInt(f1.readInt());
				f1Pos += 4;
			}
		}
		
		if(f1Pos < f1.length()) {
			f1.seek(f1Pos);
			for(int i = f1Pos; i < f1.length(); i += 4) {
				f3.writeInt(f1.readInt());
			}
		}
		
		if(f2Pos < f2.length()) {
			f2.seek(f2Pos);
			for(int i = f2Pos; i < f2.length(); i += 4) {
				f3.writeInt(f2.readInt());
			}
		}
		f1.close();
		f2.close();
		return f3;
	}
}