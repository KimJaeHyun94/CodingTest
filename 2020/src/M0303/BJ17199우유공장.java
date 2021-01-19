package M0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17199우유공장 {

	static List<Integer> graph[];
	static int N;
	static boolean visited[];
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		visited = new boolean[N + 1];
		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
		}
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			BFS(i);
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == N - 1) {
				System.out.println(i);
				System.exit(0);
			}
			
		}
		System.out.println(-1);
	}

	private static void BFS(int i) {
		Queue<Integer> q = new LinkedList<>();
		q.add(i);
		visited[i] = true;
		while (!q.isEmpty()) {
			Integer temp = q.poll();

			for (Integer child : graph[temp]) {
				if (!visited[child]) {
					arr[child]++;
					q.add(child);
				}
			}
		}
	}
}
