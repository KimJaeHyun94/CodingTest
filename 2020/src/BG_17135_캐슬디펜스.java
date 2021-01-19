import java.util.Arrays;
import java.util.Scanner;

public class BG_17135_캐슬디펜스 {
	
	private static int arr[][];
	private static int max;
	private static int temp[]=new int[3];
	private static int dir[][]= {{0, -1}, {-1, 0 }, {0, 1}};
	private static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int M=sc.nextInt();
		int D=sc.nextInt();
		
		int cnt=0;
		arr=new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j]=sc.nextInt();
			}
		}
	}
	
	public static void makecombination(int ti, int start) {
		if(ti==3) {
			System.out.println(Arrays.toString(temp));
		}
		else {
			for (int i = 0; i < arr.length; i++) {
			}
		}
	}
	
	public static void DFS(int y, int x, int cnt) {
		
	}
}
