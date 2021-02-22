package M0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author 김재현
 * @see https://iamheesoo.github.io/blog/algo-boj11438
 */
public class LCA {

	private static int[] depth;
	private static int[][] parents;
	private static List<Integer> graph[];
	static int N, M;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		graph = new List[N + 1];
		depth = new int[N + 1];
		int check = 1;
		while (check <= N) {
			check *= 2;
			max++;
		}

		parents = new int[N + 1][max + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		DFS(1, 1); // 트리 생성
		setParent();

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(sol(a, b));
		}
	}

	private static int sol(int a, int b) {
		if (depth[a] > depth[b]) { // a의 깊이가 b보다 더 작도록
			int temp = a;
			a = b;
			b = temp;
		}
		for (int i = max - 1; i >= 0; i--) { // a, b가 같은 깊이가 되도록 설정
			if (depth[a] <= depth[parents[b][i]])
				b = parents[b][i];
		}

		if (a == b)
			return a;
		// 다르다면
		for (int i = max - 1; i >= 0; i--) { // 같이 깊이를 올리며
			if (parents[a][i] != parents[b][i]) {
				a = parents[a][i];
				b = parents[b][i];
			}
		}
		return parents[a][0];
	}

	private static void setParent() {
		for (int i = 1; i < max; i++) {
			for (int j = 1; j <= N; j++) {
				parents[j][i] = parents[parents[j][i - 1]][i - 1];
			}
		}
	}

	private static void DFS(int n, int cnt) {
		depth[n] = cnt;
		for (int d : graph[n]) {
			if (depth[d] != 0)
				continue;
			parents[d][0] = n;
			DFS(d, cnt + 1);
		}
	}

}
