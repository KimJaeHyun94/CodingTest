package M0510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 버블소트 {
	static int N;
	static Node arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new Node[N];

		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			arr[i] = new Node(n, i);
		}
		
		Arrays.sort(arr);
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int dist = arr[i].idx-i;
			System.out.println(i+" "+arr[i].idx+" "+arr[i].num);
			max = Math.max(dist, max);
		}

		System.out.println(max+1);
	}

	static class Node implements Comparable<Node> {
		int num;
		int idx;


		public Node(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}

		@Override
		public int compareTo(Node o) {
			return this.num - o.num;
		}

	}
}
