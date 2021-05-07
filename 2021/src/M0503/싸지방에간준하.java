package M0503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 싸지방에간준하 {

	static int N;
	static PriorityQueue<Node> pq;
	static PriorityQueue<Integer> seat;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		ArrayList<Node> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list.add(new Node(s, e));
		}
		pq = new PriorityQueue();
		seat = new PriorityQueue();

		int ans[] = new int[N];
		Collections.sort(list);

		int idx = 0;
		for (Node node : list) {
			int s = node.s;
			int e = node.e;

			while (!pq.isEmpty()) { // 다음에 들어올 놈의 시작시간과 이용중인 사용자의 끝나는 시간을 비교
				if (pq.peek().s <= s) {
					seat.add(pq.poll().e); // 자리 끝난넘있으면 그넘들을 저장해둔다.
				} else
					break;
			}

			if (seat.isEmpty()) { // 끝난넘이 없으면
				pq.add(new Node(e, idx)); // 다음 기회에
				ans[idx++]++;	
			} else {
				int p = seat.poll(); // 끝난넘이 있다면
				pq.add(new Node(e, p));
				ans[p]++; // 그 끝난 자리에서 사용
			}

		}
		System.out.println(idx);

		for (int i = 0; i < idx; i++) {
			System.out.print(ans[i] + " ");
		}
	}

	static class Node implements Comparable<Node> {
		int s;
		int e;

		public Node(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Node o) {
			return this.s - o.s;
		}

	}
}
