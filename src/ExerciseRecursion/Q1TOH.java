package ExerciseRecursion;

import java.util.Scanner;

public class Q1TOH {
	public static void recursive(int n, char source, char auxillary, char destination) {
		if (n == 1) {
			System.out.println("1 -> " + source + " -> " + destination);
		} else {
			recursive(n-1, source, auxillary, destination);
			System.out.println(n + " -> " + source + " -> " + destination);
			recursive(n-1, auxillary, source, destination);
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Number of disks for tower of hanoi: ");
		int n = in.nextInt();
		
		recursive(n, 'A', 'B', 'C');
		
		in.close();
	}
}