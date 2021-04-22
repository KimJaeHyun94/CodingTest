package M0419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여러분의다리가되어드리겠습니다 {
	static int N;
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < N - 2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			union(s, e);
		}

		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (findSet(i) != findSet(j)) {
					System.out.println(i + " " + j);
					System.exit(0);
				}
			}
		}

	}

	private static void union(int i, int j) {
		int si = findSet(i);
		int sj = findSet(j);

		if (si > sj) {
			parents[si] = sj;
		} else
			parents[sj] = si;
	}

	private static int findSet(int x) {
		if (x == parents[x])
			return x;
		else
			return parents[x] = findSet(parents[x]);
	}
}
