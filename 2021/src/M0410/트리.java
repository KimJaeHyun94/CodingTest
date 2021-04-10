package M0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리 {
	static List<Integer> tree[];
	static int N;
	static int root;
	static int cnt;
	static int ans;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new List[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(st.nextToken());
			if (val == -1) {
				root = i;
			} else {
				tree[val].add(i);
			}
		}

		ans = Integer.parseInt(br.readLine());

		if (ans == root) {
			System.out.println(0);
		} else {
			DFS(root);
			System.out.println(cnt);
		}

	}

	private static void DFS(int node) {
		visited[node] = true;
		int num = 0;
		for (Integer child : tree[node]) {
			if (!visited[child] && child != ans) {
				num++;
				DFS(child);
			}
		}
		if (num == 0) {
			cnt++;
		}
	}

}
