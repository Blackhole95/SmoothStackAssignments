package Week1Day2;
// Brendan Raymond
public class ArrayMax {
	
	public static void main(String[] args) {
		int[] testArray = {1, 2, 3, 42, 5, 6, 7};
		System.out.println(ArrayMax.getMax(testArray));
	}
	
	public static int getMax(int[] numbers) {
		Integer max = 0;
		for(int i = 1; i < numbers.length; i++) {
			if(numbers[i] > numbers[max])
				max = i;
		}
		return max;
	}
	
}
