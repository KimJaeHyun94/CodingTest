package M0222;

import java.util.Arrays;
import java.util.HashMap;

public class 가사검색 {
	static String words[] = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
	static String queries[] = { "fro??", "????o", "fr???", "fro???", "pro?" };

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(words, queries)));
	}

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = {};
		Trie[] trie = new Trie[10001];
		Trie[] rev = new Trie[10001];

		answer = new int[queries.length];
		int idx = 0;
		for (String str : words) {
			int len = str.length();

			if (trie[len] == null) {
				trie[len] = new Trie();
			}
			trie[len].insert(words[idx]);

			if (rev[len] == null) {
				rev[len] = new Trie();
			}
			rev[len].insert(reverse(words[idx++]));
		}

		for (int i = 0; i < queries.length; i++) {
			int len = queries[i].length();

			if (queries[i].charAt(0) == '?') { // 접두사가 ?라면
				if (rev[len] != null) {
					answer[i] = rev[len].count(reverse(queries[i]));
				}
			} else {
				if (trie[len] != null)
					answer[i] = trie[len].count(queries[i]);
			}
		}
		return answer;
	}

	private static String reverse(String str) { // 거꾸로 돌리는 작업
		StringBuilder sb = new StringBuilder();
		for (int i = str.length() - 1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}

	static class Trie {

		private final Node root;

		Trie() {
			root = new Node();
		}

		public void insert(String word) {
			Node node = root; // 처음부터 시작
			int len = word.length(); // 단어의 길이

			for (int i = 0; i < len; i++) {
				char c = word.charAt(i);

				node.cnt++;
				Node child = node.children.get(c);
				if (child == null) {
					child = new Node();
					node.children.put(c, child);
				}
				node = child;
			}
		}

		public int count(String query) {
			Node node = root; // 처음부터 시작
			int len = query.length(); // 단어의 길이

			for (int i = 0; i < len; i++) {
				char c = query.charAt(i);

				if (c == '?') {
					return node.cnt;
				}
				Node child = node.children.get(c);
				if (child == null)
					return 0;
				node = child;
			}
			return node.cnt;
		}
	}

	static class Node { // node
		private final HashMap<Character, Node> children; // 자식 노드
		private int cnt; // 개수 세기

		Node() {
			children = new HashMap<>();
		}
	}
}
