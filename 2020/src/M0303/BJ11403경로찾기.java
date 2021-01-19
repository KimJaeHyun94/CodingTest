package M0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ11403경로찾기 {
	static int N;
	static int dir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int map[][];
	static int arr[][];
	static boolean visited[];
	static List<Integer> graph[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		arr = new int[N][N];
		graph = new List[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					graph[i].add(j);
				
				}
			}
		}
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			BFS(i);
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);

		while (!q.isEmpty()) {
			Integer temp = q.poll();
			for (Integer child : graph[temp]) {
				if (!visited[child]) {
					arr[start][child] = 1;
					
					q.add(child);
					visited[child] = true;
				}
			}
		}
	}
}
