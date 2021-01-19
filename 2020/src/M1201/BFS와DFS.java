package M1201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS와DFS {

	static int N, M, V;
	static List<Integer>[] Graph;
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		Graph = new List[N+1];
		visited = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			Graph[i] = new LinkedList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			Graph[a].add(b);
			Graph[b].add(a);
		}
		for (int i = 1; i <= N; i++) {
			Collections.sort(Graph[i]);
		}
		DFS(V);
		System.out.println();
		visited = new boolean[N+1];
		BFS(V);

	}

	private static void DFS(int v) {
		visited[v] = true;
		System.out.print(v+" ");
		
		for (Integer p: Graph[v]) {
			if(!visited[p]) {
				DFS(p);
			}
		}
	}
	
	private static void BFS(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		visited[v] = true;
		while(!q.isEmpty()) {
			int r = q.poll();
			System.out.print(r+" ");
			for (Integer p : Graph[r]) {
				if(!visited[p]) {
					visited[p] = true;
					q.add(p);
				}
			}
			
		}
	}
}
