package M0505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 새로운하노이탑 {
	static char[] compare = { 'A', 'B', 'C' };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = new String[3];

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 0)
				str[i] = "";
			else
				str[i] = st.nextToken();
		}

		Node node = new Node();
		node.one = str[0];
		node.two = str[1];
		node.three = str[2];
		node.cnt = 0;

		BFS(node);

	}

	private static void BFS(Node node) {
		Queue<Node> q = new LinkedList<>();
		q.add(node);
		HashSet<String> set = new HashSet<>();
		String first = node.one + " " + node.two + " " + node.three;
		set.add(first);
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (check(cur)) {
				System.out.println(cur.cnt);
				return;
			}
			if (cur.one.length() > 0) {
				String one = cur.one;
				String two = cur.two;
				String three = cur.three;
				// A->B
				char ch = one.charAt(one.length() - 1);
				String ta = one.substring(0, one.length() - 1);
				String tb = two + ch;

				String ans = ta + " " + tb + " " + three;
				if (!set.contains(ans)) {
					set.add(ans);
					q.add(new Node(ta, tb, three, cur.cnt + 1));
				}

				// A->C
				String tc = three + ch;
				ans = ta + " " + two + " " + tc;
				if (!set.contains(ans)) {
					set.add(ans);
					q.add(new Node(ta, two, tc, cur.cnt + 1));
				}
			}

			if (cur.two.length() > 0) {
				String one = cur.one;
				String two = cur.two;
				String three = cur.three;
				// B->A
				char ch = two.charAt(two.length() - 1);
				String tb = two.substring(0, two.length() - 1);
				String to = one + ch;

				String ans = to + " " + tb + " " + three;
				if (!set.contains(ans)) {
					set.add(ans);
					q.add(new Node(to, tb, three, cur.cnt + 1));
				}

				// B->C
				String tc = three + ch;
				ans = one + " " + tb + " " + tc;
				if (!set.contains(ans)) {
					set.add(ans);
					q.add(new Node(one, tb, tc, cur.cnt + 1));
				}
			}

			if (cur.three.length() > 0) {
				String one = cur.one;
				String two = cur.two;
				String three = cur.three;
				// C->A
				char ch = three.charAt(three.length() - 1);
				String tc = three.substring(0, three.length() - 1);
				String ta = one + ch;

				String ans = ta + " " + two + " " + tc;
				if (!set.contains(ans)) {
					set.add(ans);
					q.add(new Node(ta, two, tc, cur.cnt + 1));
				}

				// C->B
				String tb = two + ch;
				ans = one + " " + tb + " " + tc;
				if (!set.contains(ans)) {
					set.add(ans);
					q.add(new Node(one, tb, tc, cur.cnt + 1));
				}
			}
		}
	}

	private static boolean check(Node cur) {
		String one = cur.one;
		String two = cur.two;
		String three = cur.three;

		for (int i = 0; i < one.length(); i++) {
			if (one.charAt(i) != 'A')
				return false;
		}
		for (int i = 0; i < two.length(); i++) {
			if (two.charAt(i) != 'B')
				return false;
		}
		for (int i = 0; i < three.length(); i++) {
			if (three.charAt(i) != 'C')
				return false;
		}

		return true;
	}

	static class Node {
		String one;
		String two;
		String three;
		int cnt;

		public Node() {
		}

		public Node(String one, String two, String three, int cnt) {
			this.one = one;
			this.two = two;
			this.three = three;
			this.cnt = cnt;
		}
	}
}
