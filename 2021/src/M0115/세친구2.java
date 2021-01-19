package M0115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 세친구2 {
	static List<Integer> graph[];
	static int N, M;
	static int ans = Integer.MAX_VALUE;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = i+1; j <= N; j++) {
				if(graph[i].contains(j)) {
					for (int k = j+1; k <= N; k++) {
						if(graph[j].contains(k) && graph[k].contains(i)) {
							ans = Math.min(ans, graph[i].size()-2 + graph[j].size()-2 + graph[k].size()-2);
							flag = true;
						}
					}
				}
			}
		}
		if(flag) {
			System.out.println(ans);
		}
		else {
			System.out.println(-1);
		}
	}
	
}
