package M1129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import M1129.미로.Node;

public class 적록색약 {
	static int N;
	static char arr[][];
	static boolean visited[][];
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		arr = new char [N][N];
		visited = new boolean[N][N];
		int cnt = 0;
		int cnt2 = 0;
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					BFS(i,j);
					cnt++;
				}
			}
		}
		
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j]=='G') {
					arr[i][j]='R';
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					BFS(i,j);
					cnt2++;
				}
			}
		}
		System.out.println(cnt+" "+cnt2);
	}
	private static void BFS(int i, int j) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(i,j));

		while(!q.isEmpty()) {
			Node temp = q.poll();
			int si = temp.i;
			int sj = temp.j;
			visited[si][sj] = true;
			
			for (int d = 0; d < dir.length; d++) {
				int di = si+dir[d][0];
				int dj = sj+dir[d][1];
				
				if(isOK(di,dj,si,sj)) {
					visited[di][dj]=true;
					q.offer(new Node(di,dj));
				}
			}
		}
		
	}
	
	private static boolean isOK(int di, int dj, int si, int sj) {
		return di>=0 && di<N && dj>=0 && dj<N && !visited[di][dj] && arr[di][dj]==arr[si][sj];
	}

	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
	}
}
