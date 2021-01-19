package M0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA모의_디저트카페 {
	static int N;
	static int map[][];
	static int max;
	static int starti, startj;
	static int[][] dirs = { { 1, -1 }, { 1, 1 }, { -1, 1 }, { -1, -1 } };
	static boolean[] visited;
	static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[101];
			max = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					starti = i;
					startj = j;
					visited[map[i][j]]=true;
					DFS(i, j, 0, 0);
					visited[map[i][j]]=false;
				}
			}
			System.out.println("#" + tc + " " + max);
		}
	}

	private static void DFS(int y, int x, int cnt, int dir) {
		if (y == starti && x == startj && dir==3) {
			max = Math.max(max, cnt);
			return;
		} 
		for (int i = 1; i <= 2; i++) {
			int dy,dx,direction;
			
			if(i==1) {
				direction=dir;
				dy=y+dirs[direction][0];
				dx=x+dirs[direction][1];
				
			}
			else {
				if(y==starti && x==startj)
					continue;
				if(dir==3) {
					continue;
				}
				direction=dir+1;
				dy=y+dirs[direction][0];
				dx=x+dirs[direction][1];
				
			}
			if(dy<0||dy>=N||dx<0||dx>=N)
				continue;
			
			if(dy==starti&&dx==startj) {
				DFS(dy,dx,cnt+1,direction);
				break;
			}
			
			if(!visited[map[dy][dx]]) {
				visited[map[dy][dx]]=true;
				DFS(dy,dx,cnt+1,direction);
				visited[map[dy][dx]]=false;
			}
		}
	}
}
