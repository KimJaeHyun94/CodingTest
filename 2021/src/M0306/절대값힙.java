package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class 절대값힙 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());

			if (x == 0) {
				if (pq.isEmpty()) {
					System.out.println(0);
				} else {
					System.out.println(pq.poll().r);
				}
			} else {
				pq.add(new Node(x, Math.abs(x)));
			}

		}

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
			if (o.c == this.c) {
				return this.r - o.r;
			}else 
			return this.c - o.c;
		}

	}

}
