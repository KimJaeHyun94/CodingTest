package M0222;

import java.util.HashMap;

public class 자동완성 {
	public int solution(String[] words) {
		int answer = 0;

		Trie trie = new Trie();
		for (String word : words) {
			trie.insert(word);
		}

		for (String word : words) {
			answer += trie.count(word);
		}
		return answer;
	}

	static class Trie {
		private final Node root;

		Trie() {
			root = new Node();
		}

		public void insert(String word) {
			Node node = root;
			int len = word.length();
			for (int i = 0; i < len; i++) {
				char c = word.charAt(i);

				Node child = node.children.get(c);
				if (child == null) {
					child = new Node();
					node.children.put(c, child);
				}
				child.cnt++;
				node = child; // 그 다음 자식으로 접근
			}
		}

		public int count(String word) {
			Node node = root;
			int len = word.length();
			int cnt = 1;
			for (int i = 0; i < len; i++) {
				char c = word.charAt(i);
				if (i == len - 1)
					return cnt;
				Node child = node.children.get(c);
				if (child != null) {
					if (child.cnt <= 1) {
						return cnt;
					}
					node = child;
				}
				cnt++;
			}
			return cnt;
		}
	}

	static class Node {
		private final HashMap<Character, Node> children; // 자식 노드
		private int cnt; // 개수 세기

		Node() {
			children = new HashMap<>();
		}
	}
}
