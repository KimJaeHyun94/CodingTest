package M0115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
 * @See https://github.com/surinoel/boj/blob/master/17089.cpp
 * @Author AKKabiyo
 */
public class 세친구 {
	static List<Integer> graph[];
	static int N, M;
	static int ans = Integer.MAX_VALUE;
	static boolean flag;
	static int map[];
	static int idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		for (int i = 1; i <= N; i++) {
			boolean visited[] = new boolean[N + 1];
			idx = i;
			DFS(i, new ArrayList<>(), visited);
		}

		if (flag) {
			System.out.println(ans);
		} else {
			System.out.println(-1);
		}
	}

	private static void DFS(int r, ArrayList<Integer> temp, boolean[] visited) {
		if (r == idx && temp.size() == 3) { // 한바퀴 돌았으면
			ans = Math.min(ans, sum(temp));
			flag = true;
			return;
		}

		if (temp.contains((Integer)r) || temp.size()>=3)	//가지치기
			return;

		if (!visited[r]) {
			visited[r] = true;
			temp.add(r);
			for (int i = 0; i < graph[r].size(); i++) {
				int check = graph[r].get(i);
				DFS(check, temp, visited);
			}
			temp.remove((Integer) r);
		}
	}

	private static int sum(ArrayList<Integer> arr) {
		return (graph[arr.get(0)].size() - 2) + (graph[arr.get(1)].size() - 2) + graph[arr.get(2)].size() - 2;
	}
}
