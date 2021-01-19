import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ벽부수고이동하기 {
	private static int N;
	private static int M;
	private static int map[][];
	private static int dir[][]= {{-1,0},{1,0},{0,-1},{0,1}};
	private static int end=0;
	private static int visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		visited=new int[N][M];
		for (int i = 0; i < N; i++) {
			String str=br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j]=str.charAt(j)-'0';
				visited[i][j]=Integer.MAX_VALUE;
			}
		}
		BFS(0,0);
		if(end==0) {
			System.out.println(-1);
		}
		else {
			System.out.println(end);
		}
	}
	private static void BFS(int a, int b) {
		Queue<Node> queue=new LinkedList<>();
		
		queue.add(new Node(a,b,1,0));
		visited[a][b]=0;
		
		while(!queue.isEmpty()) {
			Node now=queue.poll();
			
			int y=now.y;
			int x=now.x;
			if(y==N-1 && x==M-1) {
				end=now.dist;
				return;
			}
			
			for (int i = 0; i < dir.length; i++) {
				int dy=y+dir[i][0];
				int dx=x+dir[i][1];
				
				if(isOK(dy,dx,now)) {
					if(map[dy][dx]==0) {
						visited[dy][dx]=now.broke;
						queue.add(new Node(dy,dx,now.dist+1,now.broke));
					}
					else {
						if(now.broke==0) {
							visited[dy][dx]=now.broke+1;
							queue.add(new Node(dy,dx,now.dist+1,now.broke+1));
						}
					}
				}
			}
		}
	}
	private static boolean isOK(int dy, int dx, Node now) {
		if(dy>=0&&dy<N&&dx>=0&&dx<M && visited[dy][dx]>now.broke) {
			return true;
		}
		return false;
	}
	static class Node{
		int y;
		int x;
		int dist;
		
		int broke;
		
		public Node(int y, int x, int dist, int broke) {
			super();
			this.y = y;
			this.x = x;
			this.dist = dist;
			this.broke = broke;
		}
	}
}
