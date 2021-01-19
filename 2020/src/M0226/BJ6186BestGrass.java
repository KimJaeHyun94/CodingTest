package M0226;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ6186BestGrass {
	private static int N, M;
	private static char map[][];
	private static boolean visited[][];
	private static int dir[][]= {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		int cnt=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '#' && !visited[i][j]) {
					BFS(i,j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

	private static void BFS(int a, int b) {
		Queue<pair>queue=new LinkedList<>();
		queue.add(new pair(a,b));
		visited[a][b]=true;
		
		while(!queue.isEmpty()) {
			pair now=queue.poll();
			int y=now.y;
			int x=now.x;
			
			for (int i = 0; i < dir.length; i++) {
				int dy=y+dir[i][0];
				int dx=x+dir[i][1];
				
				if(isOk(dy,dx)) {
					queue.add(new pair(dy,dx));
					visited[dy][dx]=true;
				}
			}
		}
	}
	private static boolean isOk(int dy, int dx) {
		if(dy>=0&&dy<N&&dx>=0&&dx<M) {
			if(!visited[dy][dx] && map[dy][dx]=='#')
				return true;
		}
		return false;
	}
	static class pair{
		int y;
		int x;
		public pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
