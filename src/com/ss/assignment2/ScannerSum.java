package com.ss.assignment2;
import java.util.Scanner;

//Brendan Raymond
public class ScannerSum {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Input numbers to be summed, followed by a non-integer:");
		Integer sum = in.nextInt();
		while(in.hasNextInt())
			sum += in.nextInt();
		System.out.println(sum);
		in.close();
	}
	
}
