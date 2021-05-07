package M0507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동방프로젝트Large {
	static int N;
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		int M = Integer.parseInt(br.readLine());

		int ans = N;
		while(M-- >0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			while (findSet(a) != findSet(b) && a <= N) { // 둘의 부모가 다를때까지
				ans--;
				int p = findSet(a);
				union(a, b);
				a = p + 1; // 합치고 그 다음칸으로 이동
			}
		}

		System.out.println(ans);
	}

	private static void union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);

		parents[pa] = pb; // 왼쪽이 무너지며 오른쪽으로 합쳐진다.

	}

	private static int findSet(int a) {
		if (a == parents[a])
			return a;
		return findSet(parents[a]);
	}

}
