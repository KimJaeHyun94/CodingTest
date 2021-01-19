import java.util.Scanner;

public class BJ10974모든순열 {
	private static int N;
	private static int arr[];
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		arr=new int[N];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i]=i+1;
		}
		permutation(0,new int[N],new boolean[N]);
		
	}
	private static void permutation(int r, int[] temp, boolean[] visited) {
		if(r==N) {
			for (int i = 0; i < temp.length; i++) {
				System.out.print(temp[i]+" ");
			}
			System.out.println();
		}
		else {
			for (int i = 0; i < arr.length; i++) {
				if(!visited[i]) {
					visited[i]=true;
					temp[r]=arr[i];
					permutation(r+1,temp,visited);
					visited[i]=false;
				}
			}
		}
	}
}
