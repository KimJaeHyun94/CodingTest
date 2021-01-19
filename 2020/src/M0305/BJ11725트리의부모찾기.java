package M0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ11725트리의부모찾기 {
	static List<Integer> graph[];
	static int arr[];
	static int N;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		graph = new List[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}
		BFS();
		for (int i = 2; i <= N; i++) {
			System.out.println(arr[i]);
		}
	}

	private static void BFS() {
		Queue<Integer> q=new LinkedList<>();
		q.add(1);
		visited[1]=true;
		while(!q.isEmpty()) {
			Integer parent=q.poll();
			for (Integer child : graph[parent]) {
				if(!visited[child]) {
					visited[child]=true;
					arr[child]=parent;
					q.add(child);
				}
			}
		}
		
	}
	

}
