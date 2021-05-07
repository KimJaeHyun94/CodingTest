package M0504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 단절점 {
	static int V, E;
	static int visited[];
	static boolean checked[];
	static ArrayList<Node> ans;
	static int cnt = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		List<Integer> graph[] = new List[V + 1];
		visited = new int[V + 1];
		checked = new boolean[V + 1];
		ans = new ArrayList<>();

		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		for (int i = 1; i <= V; i++) {
			if (visited[i] == 0) {
				dfs(i, true, graph);
			}
		}
		int count = 0;
		for (int i = 1; i <= V; i++) {
			if(checked[i]) {
				count++;
			}
		}

		System.out.println(count);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if(checked[i]) {
				sb.append(i+" ");
			}
		}
		System.out.println(sb);
	}

	private static int dfs(int v, boolean p, List<Integer>[] graph) {
		visited[v] = cnt++;
		int re = visited[v];
		int ch = 0;
		
		for (Integer child : graph[v]) {
			if (visited[child] == 0) { // 방문 되지 않은 경우에
				ch++;
				
				int low = dfs(child, false, graph);
				
				if(!p && low>=visited[v]) {
					checked[v] = true;
				}
				re = Math.min(re, low);
			} else {
				re = Math.min(re, visited[child]);
			}
		}
		
		if(p && ch>=2) {		//자식이 2개이고 루트 정점이면 단절점
			checked[v] = true;
		} 
		return re;
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			if (o.x == this.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}
	}
}
