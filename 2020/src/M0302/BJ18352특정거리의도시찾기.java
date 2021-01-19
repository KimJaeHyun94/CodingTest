package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ18352특정거리의도시찾기 {
	static List<Integer> graph[];
	static int N, M, K, X;
	static boolean visited[];
	static boolean arr[];
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		arr=new boolean[N+1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
		}
		BFS(X, 0);
		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]) {
				flag=true;
				System.out.println(i);
			}
		}
		if(!flag)
			System.out.println(-1);
		
	}

	private static void BFS(int x, int depth) {
		Queue<city> queue = new LinkedList<>();
		queue.add(new city(x, depth));
		visited[x] = true;

		while (!queue.isEmpty()) {
			city now = queue.poll();
			int a = now.a;
			int d = now.d;
			if (d > K)
				continue;
			else if(d==K)
			{
				arr[a]=true;
			}
			List<Integer> childs = graph[a];
			for (int i = 0; i < childs.size(); i++) {
				Integer child = childs.get(i);
				if (visited[child])
					continue;
				visited[child] = true;
				queue.add(new city(child, d + 1));
			}
		}
	}

	static class city {
		int a;
		int d;

		public city(int a, int d) {
			this.a = a;
			this.d = d;
		}
	}
}
