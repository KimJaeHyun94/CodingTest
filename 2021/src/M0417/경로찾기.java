package M0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 경로찾기 {
	static int N, K, A, B;
	static List<Integer> graph[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new List[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		String[] str = new String[N];

		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken()) - 1;
		B = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (check(str[i], str[j])) {
					graph[i].add(j);
					graph[j].add(i);
				}
			}
		}
		BFS(A, B);
	}

	private static void BFS(int a, int b) {
		boolean visited[] = new boolean[N];
		visited[a] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(a, String.valueOf(a + 1)));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.num == b) {
				System.out.println(cur.str);
				System.exit(0);
			}

			for (int child : graph[cur.num]) {
				if (!visited[child]) {
					visited[child] = true;
					q.add(new Node(child, cur.str + " " + String.valueOf(child + 1)));
				}
			}
		}
		System.out.println(-1);

	}

	private static boolean check(String str1, String str2) {
		boolean flag = true;
		int cnt = 0;
		for (int i = 0; i < K; i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				cnt++;
			}
			if (cnt > 1) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	static class Node {
		int num;
		String str;

		public Node(int num, String str) {
			this.num = num;
			this.str = str;
		}

	}

}
