package M0229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA2814_최장경로 {
	static List<Integer>[] graph;
	static boolean visited[];
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			graph = new List[N + 1];
			visited = new boolean[N + 1];
			max = 0;
			for (int i = 1; i < graph.length; i++) {
				graph[i] = new ArrayList<>();
			}

			if (N > 1) {
				for (int i = 0; i < M; i++) {
					st = new StringTokenizer(br.readLine());
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());

					graph[a].add(b);
					graph[b].add(a);
				}
				for (int i = 1; i <= N; i++) {
					visited = new boolean[N + 1];
					DFS(i,1); 
				}
			}
			if (N == 1) {
				System.out.println("#" + tc + " " + 1);
			} else
				System.out.println("#" + tc + " " + max);
		}
	}

	private static void DFS(int n,int cnt) {
		visited[n]=true;
		max = Math.max(max, cnt);
		
		List<Integer> childs = graph[n];
		for (int i = 0; i < childs.size(); i++) {
			Integer child = childs.get(i);
			if (!visited[child]) {
				DFS(child,cnt+1);
			}
		}
		visited[n]=false;
	}
}
