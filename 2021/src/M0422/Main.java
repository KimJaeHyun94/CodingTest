package M0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[];
	static ArrayList<Node> list;
	static int lis[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		visited = new boolean[500001];
		list = new ArrayList<>();
		Node[] trace = new Node[N + 1];
		lis = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());

			list.add(new Node(idx, val));
			visited[idx] = true;
		}
		Collections.sort(list);

		int idx = 0;

		trace = new Node[N];
		lis[idx] = list.get(0).r;
		trace[0] = new Node(0, list.get(0).l);

		for (int i = 1; i < N; i++) {
			if (lis[idx] < list.get(i).r) {
				lis[++idx] = list.get(i).r;
				trace[i] = new Node(idx, list.get(i).l);
			} else {
				int low = lowerbound(0, idx, list.get(i).r);
				lis[low] = list.get(i).r;
				trace[i] = new Node(low, list.get(i).l);
			}
		}

		System.out.println(N - (idx + 1));
		ArrayList<Integer> slist = new ArrayList<>();

		for (int i = N - 1; i >= 0; i--) {
			if (trace[i].l == idx) {
				slist.add(trace[i].r);
				idx--;
			}
		}
		for (int a : slist)
			visited[a] = false;

		for (int i = 0; i <= 500000; i++) {
			if (visited[i])
				System.out.println(i);
		}
	}

	private static int lowerbound(int left, int right, int target) {
		while (left <= right) {
			int mid = (left + right) >> 1;
			if (lis[mid] < target)
				left = mid + 1;
			else
				right = mid-1;
		}
		return right+1;
	}

	static class Node implements Comparable<Node> {
		int l;
		int r;

		public Node(int l, int r) {
			this.l = l;
			this.r = r;
		}

		@Override
		public int compareTo(Node o) {
			return this.l - o.l;
		}
	}
}
