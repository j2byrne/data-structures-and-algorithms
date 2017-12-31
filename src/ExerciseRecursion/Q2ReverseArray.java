package ExerciseRecursion;

public class Q2ReverseArray {
	public static void reverseArray(int A[], int i, int j) {
		if (i < j) {
			int tmp = A[i];
			A[i] = A[j];
			A[j] = tmp;
			reverseArray(A, i+1, j-1);
		}
		return;
	}
	
	public static void main(String[] args) {
		int[] A = {12, 5, 19, 6, 11, 3, 9, 34, 2, 1, 15};
		
		reverseArray(A, 2, 8);
		
		for(int i : A) {
			System.out.println(i);
		}
	}
}
