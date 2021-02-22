package M0208;

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

	static class Node { // node
		private static final int ALPHA_CNT = 26;
		private final Node[] children = new Node[ALPHA_CNT + 1];
		private int cnt;
	}

	static class Trie {

		private final Node root;

		Trie() {
			root = new Node();
		}

		public void insert(final String word) {
			Node cur = root;
			final int len = word.length();

			for (int i = 0; i < len; i++) {
				char c = word.charAt(i);
				int cIdx = c - 'a';

				if (cur.children[cIdx] == null) {
					cur.children[cIdx] = new Node();
				}
				cur.children[cIdx].cnt++;
				
				cur = cur.children[cIdx];
			}
		}

		public int count(final String query) {

			Node cur = root;
			int cnt = 1;
			final int len = query.length();

			for (int i = 0; i < len; i++) {
				char c = query.charAt(i);
				int cIdx = c - 'a';
				if (i == len - 1)
					return cnt;
				Node child = cur.children[cIdx];
				if (child != null) {
					if (child.cnt <= 1) {
						return cnt;
					}
					cur = child;
				}
				cnt++;
			}
			return cnt;
		}
	}
}
