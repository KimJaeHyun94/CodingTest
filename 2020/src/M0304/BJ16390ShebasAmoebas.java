package M0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16390ShebasAmoebas {
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 1 }, { -1, -1 }, { 1, -1 }, { 1, 1 } };
	static int N,M;
	static char map[][];
	static int cnt;
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new char[N][M];
		visited=new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str=br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j]=str.charAt(j);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]=='#' && !visited[i][j]) {
					cnt++;
					DFS(i,j);
				}
			}
		}
		System.out.println(cnt);
	}
	private static void DFS(int a, int b) {
		visited[a][b]=true;
		
		for (int i = 0; i < dir.length; i++) {
			int dy=a+dir[i][0];
			int dx=b+dir[i][1];
			
			if(isOk(dy,dx)) {
				DFS(dy,dx);
			}
				
		}
		
	}
	private static boolean isOk(int dy, int dx) {
		if(dy>=0&&dy<N&&dx>=0&&dx<M) {
			if(!visited[dy][dx]&& map[dy][dx]=='#')
				return true;
		}
		return false;
	}

}
