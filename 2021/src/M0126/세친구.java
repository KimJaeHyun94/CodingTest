package M0126;

import java.io.*;
import java.util.*;

public class 세친구 {
	static List<Integer> graph[];
	static int N, M;
	static int ans = Integer.MAX_VALUE;
	static boolean flag;
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

		for (int i = 1; i <= N; i++) { // 1부터 N번까지의 학생 모두
			boolean visited[] = new boolean[N + 1]; // 매번 초기화 해줌
			idx = i;
			DFS(i, new ArrayList<>(), visited);
		}
		
		if(!flag) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
	}

	private static void DFS(int cnt, ArrayList<Integer> friends, boolean[] visited) {
		if (cnt == idx && friends.size() == 3) { // 3명의 친구를 만나고 다 돌아왔다면
			ans = Math.min(ans, sum(friends));
			flag = true;  //친구 관계가 있다는 것을 증명
			return;
		}

		if (visited[cnt] || friends.size() >= 3) { // A,B,C 이상의 친구 관계는 필요가 없다 || 조건 달성 전에 되돌아오는 것 역시 필요가 없다  <<가지치기>>
			return;
		}

		if (!visited[cnt]) {
			visited[cnt] = true;
			friends.add(cnt);
			for (int friend : graph[cnt]) {
				DFS(friend, friends, visited);
			}
			friends.remove((Integer)cnt);
		}
	}

	private static int sum(ArrayList<Integer> friends) {
		return graph[friends.get(0)].size() + graph[friends.get(1)].size() +graph[friends.get(2)].size()-6; 
	}

}
