package M1201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS와DFS2{

	static int N, M, V;
	static int[][] Graph;
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		Graph = new int[N+1][N+1];
		visited = new boolean[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			Graph[a][b] = 1;
			Graph[b][a] = 1;
		}
		
		DFS(V);
		System.out.println();
		visited = new boolean[N+1];
		BFS(V);

	}

	private static void DFS(int v) {
		visited[v] = true;
		System.out.print(v+" ");
		
		for (int i = 0; i < Graph[v].length; i++) {
			if(isOK(i,v)) {
				visited[i]=true;
				DFS(i);
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
			for (int i = 0; i < Graph[r].length; i++) {
				if(isOK(i,r)) {
					q.add(i);
					visited[i]=true;
				}
			}
		}
	}

	private static boolean isOK(int i, int r) {
		return Graph[r][i]==1 && !visited[i];
	}
}