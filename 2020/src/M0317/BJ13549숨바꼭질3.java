package M0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ13549숨바꼭질3 {
	static int min = Integer.MAX_VALUE;
	static int N, K;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		BFS();
		System.out.println(min);
	}

	private static void BFS() {
		PriorityQueue<Node> queue = new PriorityQueue<Node>(new Comparator<Node>(){
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.d, o2.d);
			}
		});

		queue.add(new Node(N, 0));
		visited[N] = true;
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			int n = temp.n;
			int d = temp.d;
			visited[n]=true;
			if(n==K) {
				min=d;
				return;
			}
			if (n - 1 >= 0 && !visited[n - 1]) {
				queue.add(new Node(n - 1, d + 1));
			}
			if (n + 1 <= 100000 && !visited[n + 1]) {
				queue.add(new Node(n + 1, d + 1));
			}
			if (n * 2 <= 100000 && !visited[n * 2]) {
				queue.add(new Node(n * 2, d));
			}
		}
	}

	static class Node {
		int n;
		int d;

		public Node(int n, int d) {
			this.n = n;
			this.d = d;
		}
	}
}