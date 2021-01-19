package M0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ3184양 {
	static int R,C;
	static char map[][];
	static boolean visited[][];
	static int wolf,sheep;
	static int dir[][]= {{1,0},{-1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		map=new char[R][C];
		visited=new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String str=br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j]=str.charAt(j);
			}
		}
		int wolfsum=0,sheepsum=0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]!='#'&&!visited[i][j]) {
					wolf=0; sheep=0;
					//DFS(i,j);
					BFS(i,j);
					if(wolf<sheep) {
						sheepsum+=sheep;
					}
					else
						wolfsum+=wolf;
				}
			}
		}
		System.out.println(sheepsum+" "+wolfsum);
	}
	private static void BFS(int y, int x) {
		visited[y][x]=true;
		Queue<Node> queue=new LinkedList<>();
		queue.add(new Node(y,x));
		if(map[y][x]=='o') sheep++;
		else if(map[y][x]=='v') wolf++;
		
		while(!queue.isEmpty()) {
			Node now=queue.poll();
		
			for (int i = 0; i < 4; i++) {
				int dy=now.y+dir[i][0];
				int dx=now.x+dir[i][1];
				
				if(isOK(dy,dx)) {
					queue.add(new Node(dy,dx));
					visited[dy][dx]=true;
					if(map[dy][dx]=='o') sheep++;
					else if(map[dy][dx]=='v') wolf++;
				}
			}
		}
	}
	private static void DFS(int y, int x) {
		visited[y][x]=true;
		
		if(map[y][x]=='o') sheep++;
		else if(map[y][x]=='v') wolf++;
		
		for (int i = 0; i < 4; i++) {
			int dy=y+dir[i][0];
			int dx=x+dir[i][1];

			if(isOK(dy,dx)) {
				DFS(dy,dx);
			}
		}
	}
	private static boolean isOK(int dy, int dx) {
		if(dy>=0&&dy<R&&dx>=0&&dx<C) {
			if(!visited[dy][dx]&&map[dy][dx]!='#')
				return true;
		}
		return false;
	}
	static class Node{
		int y;
		int x;
		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
