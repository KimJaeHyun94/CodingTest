package M0403;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 중량제한BFS {
	static int N, M;
	static List<Node> graph[];
	static int max = 0;

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
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			graph[A].add(new Node(B, C));
			graph[B].add(new Node(A, C));
			max = Math.max(max, C);
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		System.out.println(BinarySearch(start, end));
	}

	private static int BinarySearch(int start, int end) {
		int left = 1;
		int right = max;
		int mid = 0;
		int ans = 0;
		
		while(left<=right) {
			mid = (left+right)/2;
			
			if(BFS(start, end, mid)) {
				ans = mid > ans ? mid : ans;
				left = mid + 1;
			}else {
				right = mid-1;
			}
		}
		return ans;
	}

	private static boolean BFS(int start, int end, int mid) {
		boolean visited[] = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		
		q.add(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur==end) {
				return true;
			}
			for (Node next : graph[cur]) {
				if(!visited[next.to] && next.weight>=mid) {
					q.add(next.to);
					visited[next.to] = true;
				}
			}
		}
		return false;
	}

	static class Node {
		int to, weight;

		Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}
