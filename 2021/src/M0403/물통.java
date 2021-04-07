package M0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class 물통 {
	static int A, B, C, D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		BFS();

	}

	private static void BFS() {
		HashSet<Water> set = new HashSet<>();
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0));
		set.add(new Water(0, 0));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.a == C && cur.b == D) {
				System.out.println(cur.d);
				System.exit(0);
			}
			// Fill A
			if (!set.contains(new Water(A, cur.b))) {
				set.add(new Water(A, cur.b));
				q.add(new Node(A, cur.b, cur.d + 1));
			}
			// Fill B
			if (!set.contains(new Water(cur.a, B))) {
				set.add(new Water(cur.a, B));
				q.add(new Node(cur.a, B, cur.d + 1));
			}
			// Empty A
			if (!set.contains(new Water(0, cur.b))) {
				set.add(new Water(0, cur.b));
				q.add(new Node(0, cur.b, cur.d + 1));
			}
			// Empty B
			if (!set.contains(new Water(cur.a, 0))) {
				set.add(new Water(cur.a, 0));
				q.add(new Node(cur.a, 0, cur.d + 1));
			}
			// Move water A -> B
			if (cur.a <= B - cur.b) {// a에 남아있는 물통의 양이 b에 남아있는 빈 공간보다 적거나 같다면
				if (!set.contains(new Water(0, cur.a + cur.b))) {
					set.add(new Water(0, cur.a + cur.b));
					q.add(new Node(0, cur.a + cur.b, cur.d + 1));
				}
			} else {
				int bcup = B;
				int acup = (cur.a + cur.b) - B;
				if (!set.contains(new Water(acup, bcup))) {
					set.add(new Water(acup, bcup));
					q.add(new Node(acup, bcup, cur.d + 1));
				}
			}

			// Move Water B -> A
			if (cur.b <= A - cur.a) {// a에 남아있는 물통의 양이 b에 남아있는 빈 공간보다 적거나 같다면
				if (!set.contains(new Water(cur.a + cur.b, 0))) {
					set.add(new Water(cur.a + cur.b, 0));
					q.add(new Node(cur.a + cur.b, 0, cur.d + 1));
				}
			} else {
				int acup = A;
				int bcup = (cur.a + cur.b) - A;
				if (!set.contains(new Water(acup, bcup))) {
					set.add(new Water(acup, bcup));
					q.add(new Node(acup, bcup, cur.d + 1));
				}
			}
		}
		System.out.println(-1);
	}

	static class Water {
		int a;
		int b;

		public Water(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
        public boolean equals(Object o){
            if (this == o)
                return true;
            if (!(o instanceof Water))
                return false;
            Water si = (Water)o;
            return a == si.a && b == si.b;
        }
		
        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
	}

	static class Node {
		int a;
		int b;
		int d;

		public Node(int a, int b, int d) {
			this.a = a;
			this.b = b;
			this.d = d;
		}
	}
}
