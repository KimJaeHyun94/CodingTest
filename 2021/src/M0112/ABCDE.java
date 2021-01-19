package M0112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ABCDE {
	static List<Integer> graph[];
	static int N, M;
	static int ans;
	static boolean visited[];
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		flag = false;
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			DFS(i, 0);
			if(flag) {
				System.out.println(1);
				System.exit(0);
			}
		}
		System.out.println(0);
	}

	private static void DFS(int s, int d) {
		if (d == 4) {
			flag = true;
			return;
		}
		for (int children : graph[s]) {
			if (!visited[children]) {
				visited[children] = true;
				DFS(children, d + 1);
			}
		}
		visited[s] = false;
	}
}