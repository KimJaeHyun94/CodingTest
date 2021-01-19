import java.util.Arrays;
import java.util.Scanner;


public class BJ2567_색종이2 {
	private static int dir[][]= {{-1,0},{1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int arr[][]=new int[100][100];
		for (int i = 0; i < N; i++) {
			int R=sc.nextInt()-1;
			int C=sc.nextInt()-1;
			
			for (int j = 0; j < 10; j++) {
				for (int j2 = 0; j2 < 10; j2++) {
					arr[R+j][C+j2]=1;
				}
			}
		}
		int sum=0;
		for (int i = 0; i < 99; i++) {
			for (int j = 0; j < 99; j++) {
				if(arr[i][j]==1) {
					for (int j2 = 0; j2 < dir.length; j2++) {
						int y=i+dir[j2][0];
						int x=j+dir[j2][1];
						if(x<0 || y<0 || x>=100 || y>=100) {
							sum++;
						}
						else if(arr[y][x]==0)
							sum++;
					}
				}
			}
		}
		System.out.println(sum);
	}
}
