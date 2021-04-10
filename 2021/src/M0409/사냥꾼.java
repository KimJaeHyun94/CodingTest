package M0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 사냥꾼 {
	static int M, N;
	static long L;
	static int sadae[];
	static ArrayList<Node> animals;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Long.parseLong(st.nextToken());

		sadae = new int[M];
		animals = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			sadae[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(sadae);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			animals.add(new Node(r, c));
		}
		Collections.sort(animals);

		long ans = 0;
		for (Node cur : animals) {
			if (BinarySearch(cur))
				ans++;
		}
		System.out.println(ans);
	}

	private static boolean BinarySearch(Node cur) {
		int r = cur.r;
		int c = cur.c;
		int left = 0;
		int right = M - 1;

		while (left <= right) {
			int mid = (left + right) >> 1;
			long dist = getDist(mid, r, c);

			if (dist <= L) {
				return true;
			} else {
				if (r < sadae[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}
		return false;
	}

	private static long getDist(int mid, int r, int c) {
		return Math.abs(r - sadae[mid]) + c;
	}

	static class Node implements Comparable<Node> {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return this.r - o.r;
		}
	}
}
