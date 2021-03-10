package M0310;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 개미굴 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Trie trie = new Trie();
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			String[] ants = new String[K];

			for (int k = 0; k < K; k++) {
				ants[k] = st.nextToken();
			}
			trie.insert(ants);
		}
		trie.dfs(trie.root, 0);
	}

	static class Trie {
		static class Node { // node
			private final TreeMap<String, Node> children; // 자식 노드
			
			Node() {
				children = new TreeMap<>();
			}
		}

		private final Node root;

		Trie() {
			root = new Node();
		}

		public void insert(String[] ants) {
			Node cur = root;
			int len = ants.length;

			for (int i = 0; i < len; i++) {
				String c = ants[i];
				Node child = cur.children.get(c);
				if (child == null) {
					child = new Node();
					cur.children.put(c, child);
				}
				cur = child;
			}
		}

		public void dfs(Node root, int cnt) {
			for (String key : root.children.keySet()) {
				for (int i = 0; i < cnt; i++) {
					System.out.print("--");
				}
				System.out.println(key);
				dfs(root.children.get(key), cnt + 1);
			}
		}
	}
}
