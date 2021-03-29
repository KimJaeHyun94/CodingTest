package M0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 공항 {
	static int parents[];
	static int G, P;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());

		parents = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			parents[i] = i;
		}
		int ans = 0;
		for (int i = 0; i < P; i++) {
			int g = Integer.parseInt(br.readLine());
			int fg = findSet(g);

			if (fg == 0)		//도킹할 수 없다면 폐쇄
				break;
			ans++;
			union(fg, fg - 1);
		}
		System.out.println(ans);
	}

	public static void union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);

		if (pa != pb) {
			if (pa < pb) {
				parents[pb] = pa;
			} else {
				parents[pa] = pb;
			}
		}
	}

	private static int findSet(int x) {
		if (x == parents[x]) {
			return x;
		} else {
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}
}
