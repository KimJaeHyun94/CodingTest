package M0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2636치즈 {
	static int N, M;
	static int map[][];
	static int cheese;
	static int time;
	static int cnt;
	static int result;
	static boolean visited[][];
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					cheese++;
			}
		}
		while (true) {
			BFS();
			time++;
			
			cnt = 0;
			boolean flag=true;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0) {
						flag=false;
						cnt++;
					}
				}
			}
			if(flag)
				break;
			visited = new boolean[N][M];
			cheese = cnt;
		}
		System.out.println(time);
		System.out.println(cheese);
	}

	private static void BFS() {
		Queue<Cheese> q=new LinkedList<>();
		q.add(new Cheese(0,0));
		
		while(!q.isEmpty()) {
			Cheese temp=q.poll();
			int y=temp.y;
			int x=temp.x;
			for (int i = 0; i < dir.length; i++) {
				int dy=y+dir[i][0];
				int dx=x+dir[i][1];
				
				if(isOK(dy,dx)) {
					if(map[dy][dx]==0) {
						q.add(new Cheese(dy,dx));
						visited[dy][dx]=true;
					}
					else {
						map[dy][dx]=0;
						visited[dy][dx]=true;
					}
				}
			}
		}
	}

	private static boolean isOK(int dy, int dx) {
		if(dy>=0 && dy<N && dx>=0 && dx<M) {
			if(!visited[dy][dx])
				return true;
		}
		return false;
	}

	static class Cheese {
		int y;
		int x;

		public Cheese(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
