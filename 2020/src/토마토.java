import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 토마토 {
	static int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }};
	static int N;
	static int M;
	static int arr[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().trim().split(" ");
		
		M=Integer.parseInt(input[0]);
		N=Integer.parseInt(input[1]);
		arr=new int[N][M];
		
		for (int i = 0; i < N; i++) {
			input = br.readLine().trim().split(" ");
			for (int j = 0; j < M; j++) {
				arr[i][j]=Integer.parseInt(input[j]);
			}
		}
		BFS();
		boolean flag=true;
		int max=Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]==0) {
					System.out.print(-1);
					return;
				}
				if(max<arr[i][j])
					max=arr[i][j];
			}
		}
			System.out.print(max-1);
	}
	private static void BFS() {
		Queue<Tomato> q=new LinkedList<Tomato>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]==1)
					q.offer(new Tomato(i,j));
			}
		}
		
		while(!q.isEmpty()) {
			Tomato to=q.poll();
			int dy,dx;
			for (int i = 0; i < 4; i++) {
				
				dy=to.y+dirs[i][0];
				dx=to.x+dirs[i][1];
				int y=to.y;
				int x=to.x;
				
				if(isOK(dy,dx,y,x)) {
					arr[dy][dx]=arr[y][x]+1;
					q.offer(new Tomato(dy,dx));
				}
			}
		}
	}
	
	private static boolean isOK(int dy, int dx, int y, int x) {
		if(dy>=0&&dx>=00&&dy<N&&dx<M) {
			if(arr[dy][dx]==0 || arr[dy][dx]>arr[y][x]+1)
				return true;
		}
		return false;
	}

	static class Tomato{
		int y;
		int x;
	
		public Tomato(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}