package M0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 영우는사기꾼 {
	static int N, M, K;
	static List<Integer>[] graph;
	static int[] degree;
	static int[] build;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		build = new int[N + 1];
		degree = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());

			graph[X].add(Y);
			degree[Y]++;
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			if (op == 1) {
				if (degree[num] == 0) {
					build[num]++;
					if (build[num] == 1) {
						for (Integer child : graph[num]) {
							degree[child]--;
						}
					}
				}else {
					System.out.println("Lier!");
					System.exit(0);
				}
			}
			
			else {
				if(build[num]>0) {
					build[num]--;
					if(build[num]==0) {
						for (Integer child : graph[num]) {
							degree[child]++;
						}
					}
				}else {
					System.out.println("Lier!");
					System.exit(0);
				}
				
			}
		}
		System.out.println("King-God-Emperor");
	}

}
