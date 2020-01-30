package Week1Day1;


public class Pattern {

	//Main Method
	public static void main(String[] args) {
		System.out.println("1)");
		Pattern.PrintPatternA('*', '.', 4, 9);
		System.out.println("\n2)");
		Pattern.PrintPatternB('*', '.', 4, 10);
		System.out.println("\n3)");
		Pattern.PrintPatternC('*', '.', 4, 11);
		System.out.println("\n4)");
		Pattern.PrintPatternD('*', '.', 4, 12);

	}
	
	//Print single line of size n using character c
	private static void PrintLine(char c, int n) {
		for(int i = 0; i < n; i++) {
			System.out.print(c);
		}
		System.out.println("");
	}
	
	//Print first pattern of size n1 using character c1, with line size n2 character c2
	public static void PrintPatternA(char c1, char c2, int n1, int n2) {
		for(int i = 0; i < n1; i++) {
			for(int j = 0; j <= i; j++) {
				System.out.print(c1);
			}
			System.out.println();
		}
		Pattern.PrintLine(c2, n2);
	}

	//Print second pattern of size n1 using character c1, with line size n2 character c2
	public static void PrintPatternB(char c1, char c2, int n1, int n2) {
		Pattern.PrintLine(c2, n2);
		for(int i = 0; i < n1; i++) {
			for(int j = n1; j > i; j--) {
				System.out.print(c1);
			}
			System.out.println();
		}
	}

	//Print third pattern of size n1 using character c1, with line size n2 character c2
	public static void PrintPatternC(char c1, char c2, int n1, int n2) {
		for(int i = 0; i < n1; i++) {
			int m1 = (n1 + 1) - i;
			int m2 = (n1 + 1) + i;
			for(int j = 0; j < m1; j++) {
				System.out.print(' ');
			}
			for(int j = m1; j <= m2; j++) {
				System.out.print(c1);
			}
			System.out.println();
		}
		Pattern.PrintLine(c2, n2);
	}

	//Print fourth pattern of size n1 using character c1, with line size n2 character c2
	public static void PrintPatternD(char c1, char c2, int n1, int n2) {
		Pattern.PrintLine(c2, n2);
		for(int i = (n1 - 1); i >= 0; i--) {
			int m1 = (n1 + 1) - i;
			int m2 = (n1 + 1) + i;
			for(int j = 0; j < m1; j++) {
				System.out.print(' ');
			}
			for(int j = m1; j <= m2; j++) {
				System.out.print(c1);
			}
			System.out.println();
		}
	}

}
