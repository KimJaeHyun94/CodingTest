package M0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 두더지게임 {
	static int N;
	static List<Integer> graph[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		graph = new List[N * N + 1];

		for (int i = 1; i <= N * N; i++) {
			graph[i] = new ArrayList<>();
		}
		int time = 0;
		for (int i = 0; i < N*N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			for (int j = 0; j < k; j++) {
				int t = Integer.parseInt(st.nextToken());
				time = Math.max(t, time);
				graph[a].add(t);
			}
		}
		int ans = 0;	


		for (int i = 1; i <= time; i++) {
			int cnt = 0;
			for (int j = 1; j < graph.length; j++) {
				if (graph[j].contains(i)) {
					cnt = Math.max(cnt, j);
				}
			}
			ans += cnt;
		}
		System.out.println(ans);
	}

}
