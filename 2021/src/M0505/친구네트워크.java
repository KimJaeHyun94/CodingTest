package M0505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 친구네트워크 {
	static HashMap<String, Integer> map;
	static int parents[];
	static int Route[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			int F = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			int idx = 0;
			int cnt = 0;
			parents = new int[F * 2];
			Route = new int[F * 2];
			for (int j = 0; j < F * 2; j++) {
				parents[j] = j;
				Route[j] = 1;		//나 혼자 
			}

			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String friend1 = st.nextToken();
				String friend2 = st.nextToken();

				if (!map.containsKey(friend1)) {
					map.put(friend1, idx++);
				}

				if (!map.containsKey(friend2)) {
					map.put(friend2, idx++);
				}

				int a = map.get(friend1);
				int b = map.get(friend2);

				sb.append(union(a, b)).append("\n");

			}
		}
		System.out.println(sb);
	}

	private static int union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);

		if (pa != pb) {
			if (pa > pb) {
				parents[pa] = pb;
				Route[pb] += Route[pa];
				return Route[pb];
			} else
				parents[pb] = pa;
			Route[pa] += Route[pb];
			return Route[pa];
		} else
			return Route[pb];
	}

	private static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return findSet(parents[a]);
	}
}
