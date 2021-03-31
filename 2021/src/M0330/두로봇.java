package M0330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 두로봇 {
	static int N, start, end;
	static List<Node> Graph[];
	static boolean visited[];
	static int MIN = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		Graph = new List[N+1];
		visited = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			Graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			Graph[s].add(new Node(e,d));
			Graph[e].add(new Node(s,d));
		}
		visited[start] = true;
		DFS(start, 0, 0);
		
		System.out.println(MIN);
	}
	private static void DFS(int idx, int sum, int max) {
		if(idx==end) {
			System.out.println(sum-max);
			System.exit(0);
			return;
		}
		
		for (Node child : Graph[idx]) {
			int next= child.e;
			int dist = child.d;
			if(!visited[next]) {
				visited[next] = true;
				DFS(next, sum+dist, Math.max(max, dist));
			}
		}
	}
	static class Node{
		int e;
		int d;
		public Node(int e, int d) {
			super();
			this.e = e;
			this.d = d;
		}
		
		
	}
}
