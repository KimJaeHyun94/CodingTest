package M0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김재현
 * @see https://real-012.tistory.com/157
 * @category Union-Find
 *  - A서랍과 B서랍이 따로 있는 것이 아니라 우선 순위를 가지고 있다. 
 */	
public class 방청소 {
	static int N, L;
	static int parents[];
	static boolean drawser[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		parents = new int[L + 1];
		drawser = new boolean[L + 1];

		for (int i = 1; i <= L; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			String ans = "LADICA";
			if (!drawser[a]) {
				drawser[a] = true;
				union(a, b);
			} else if (!drawser[b]) {
				drawser[b] = true;
				union(b, a);
			} else if (!drawser[findSet(a)]) {
				drawser[findSet(a)] = true;
				union(a, b);
			} else if (!drawser[findSet(b)]) {
				drawser[findSet(b)] = true;
				union(b, a);
			} else {
				ans = "SMECE";
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);

		parents[pa] = pb;
	}

	private static int findSet(int a) {
		if (parents[a] == a) {
			return a;
		} else {
			parents[a] = findSet(parents[a]);
			return parents[a];
		}
	}
}
