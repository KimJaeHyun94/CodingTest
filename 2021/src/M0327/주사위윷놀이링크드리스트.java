package M0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 주사위윷놀이링크드리스트 {
	static int[] dice;
	static int[] selections;
	static Node[] markers;
	static Node start;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		selections = new int[10];
		dice = new int[10];
		markers = new Node[4];
		ans = Integer.MIN_VALUE;

		for (int i = 0; i < 10; ++i) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		init();
		DFS(0);
		System.out.println(ans);
	}

	private static void DFS(int d) {
		if (d == 10) {
			Arrays.fill(markers, start);

			int score = play();
			ans = Math.max(ans, score);

			for (int i = 0; i < 4; i++) { // 마커들을 다 비워둔다.
				markers[i].isEmpty = true;
			}
			return;
		}
		for (int i = 0; i < 4; ++i) {
			selections[d] = i; // 선택
			DFS(d + 1);
		}
	}

	private static int play() {
		int score = 0;
		for (int i = 0; i < 10; i++) {	//10번의 턴
			Node cur = markers[selections[i]]; // 선택한 말 이동
			cur.isEmpty = true; // 비우기

			for (int j = 0; j < dice[i]; ++j) {
				if (j == 0 && cur.shortcut != null) {	//지름길이 있다면
					cur = cur.shortcut;
				} else {
					cur = cur.next;
				}
			}

			markers[selections[i]] = cur;
			// 마지막 노드에 도착하지 않았으며 이미 말이 있는 노드라면
			if (!cur.isEmpty && !cur.isEnd) {		 
				return 0;
			} else {
				cur.isEmpty = false;
				score += cur.value;
			}
		}

		return score;
	}

	private static void init() {
		start = new Node(0); // 처음 시작점
		Node end = null;
		Node center = new Node(25);

		Node temp = start;
		for (int i = 2; i <= 40; i += 2) {
			temp = temp.addNext(i);
		}

		end = temp.addNext(0);
		end.next = end;
		end.isEnd = true;

		// 10 -> 13 16 19 -> 25
		Node n = Node.getNode(10);
		n = n.shortcut = new Node(13); // 지름길
		n = n.addNext(16);
		n = n.addNext(19);
		n.next = center;

		// 20 -> 22 24 -> 25
		n = Node.getNode(20);
		n = n.shortcut = new Node(22);
		n = n.addNext(24);
		n.next = center;

		// 30 -> 28 27 26 -> 25
		n = Node.getNode(30);
		n = n.shortcut = new Node(28);
		n = n.addNext(27);
		n = n.addNext(26);
		n.next = center;

		// 25 -> 30 35 40
		n = center.addNext(30);
		n = n.addNext(35);
		n.next = Node.getNode(40);
	}

	static class Node {
		int value;
		boolean isEmpty, isEnd;
		Node next, shortcut;

		Node(int value) {
			this.value = value;
			this.isEmpty = true;
			this.isEnd = false;
			this.next = null;
			this.shortcut = null;
		}

		Node addNext(int value) {
			Node nextNode = new Node(value);

			this.next = nextNode;

			return nextNode;
		}

		static Node getNode(int target) {
			Node temp = start;

			while (true) {
				if (temp == null)
					return null;
				if (temp.value == target)
					return temp;

				temp = temp.next;
			}
		}
	}

}
