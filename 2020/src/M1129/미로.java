package M1129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로 {
	static int arr[][];
	static boolean visited[][];
	static int N;
	static int M;
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line=br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j)-'0';
			}
		}
		BFS(0, 0);
		
		System.out.println(arr[N-1][M-1]);
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
				
				if(isOK(di,dj)) {
					arr[di][dj]=arr[si][sj]+1;
					q.offer(new Node(di,dj));
					visited[di][dj] = true;
				}
			}
		}
		
	}

	private static boolean isOK(int di, int dj) {
		return di>=0 && di<N && dj>=0 && dj<M && !visited[di][dj] && arr[di][dj]!=0;
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
