package M0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사이클게임 {
	static int N, M;
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		int ans = 0;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (findSet(a) == findSet(b)) {
				ans = i;
				break;
			} else {
				union(a, b);
			}
		}
		System.out.println(ans);
	}

	private static void union(int i, int j) {
		int pi = findSet(i);
		int pj = findSet(j);

		if (pi != pj) {
			if (pi < pj) {
				parents[pj] = pi;
			} else {
				parents[pi] = pj;
			}
		}

	}

	private static int findSet(int i) {
		if (i == parents[i]) {
			return i;
		} else {
			parents[i] = findSet(parents[i]);
			return parents[i];
		}
	}
}
