package M0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 땅콩먹기 {
	static int E;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		int map[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		combination(M, 0, 0, new int[M], map);
		System.out.println(ans);
	}

	private static void combination(int m, int ti, int start, int[] temp, int[] map) {
		if (ti == m) {
			List<Integer> list = new ArrayList<>();
			list.add(E);
			for (Integer integer : temp) {
				list.add(integer);
			}
			Collections.sort(list);
			ans = Math.min(ans, list.get(list.size() - 1) - list.get(0));
		} else {
			for (int i = start; i < map.length; i++) {
				temp[ti] = map[i];
				combination(m, ti + 1, i + 1, temp, map);
			}
		}
	}
}
