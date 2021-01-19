import java.util.Scanner;

public class Main {
	static String whiteFirst[] = {
	         "WBWBWBWB", 
	         "BWBWBWBW",
	         "WBWBWBWB",
	         "BWBWBWBW",
	         "WBWBWBWB",
	         "BWBWBWBW",
	         "WBWBWBWB",
	         "BWBWBWBW",
	};

	static String blackFirst[] = {
			"BWBWBWBW",
			"WBWBWBWB",
			"BWBWBWBW",
			"WBWBWBWB",
			"BWBWBWBW",
			"WBWBWBWB",
			"BWBWBWBW",
			"WBWBWBWB"
	};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int M=sc.nextInt();
		 String empty = sc.nextLine();
		String[]chess=new String[N];
		
		for (int i = 0; i < N; i++) {
			chess[i]=sc.nextLine();
		}
		int min=Integer.MAX_VALUE;
		
		for (int i = 0; i < N-7; i++) {
			for (int j = 0; j < M-7; j++) {
				min=Math.min(min, checkW(chess,i,j));
				min=Math.min(min, checkB(chess,i,j));
			}
		}
		System.out.println(min);
		
		
	}
	private static int checkB(String[] chess, int i, int j) {
		int result=0;
		for (int k = i; k < i+8; k++) {
			for (int k2 = j; k2 < j+8; k2++) {
				if(chess[k].charAt(k2)==whiteFirst[k-i].charAt(k2-j))
					result++;
			}
		}
		return result;
	}
	private static int checkW(String[] chess, int i, int j) {
		int result=0;
		for (int k = i; k < i+8; k++) {
			for (int k2 = j; k2 < j+8; k2++) {
				if(chess[k].charAt(k2)==blackFirst[k-i].charAt(k2-j))
					result++;
			}
		}
		return result;
	}
}