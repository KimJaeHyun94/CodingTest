package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리순회 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		Tree tree = new Tree();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char n = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			tree.insert(left, n, right);
		}
		tree.preOrder(tree.root);
		System.out.println();
		tree.inOrder(tree.root);
		System.out.println();
		tree.postOrder(tree.root);
	}

	static class Tree {
		Node root = null;

		public Tree() {

		}

		public void insert(char l, char n, char r) {
			if (root == null) {
				this.root = new Node(n);

				if (l == '.') {
					root.left = null;
				} else
					root.left = new Node(l);

				if (r == '.') {
					root.right = null;
				} else
					root.right = new Node(r);
			} else {
				search(root, n, l, r);
			}
		}

		public void search(Node node, char n, char l, char r) {
			if (node == null) {
				return;
			} else {
				if (node.n == n) {
					if (l == '.') {
						node.left = null;
					} else
						node.left = new Node(l);

					if (r == '.') {
						node.right = null;
					} else
						node.right = new Node(r);
				} else {
					search(node.left, n, l, r);
					search(node.right, n, l, r);
				}
			}
		}

		public void preOrder(Node node) {
			System.out.print(node.n);
			if (node.left != null) {
				preOrder(node.left);
			}
			if (node.right != null) {
				preOrder(node.right);
			}
		}

		public void inOrder(Node node) {
			if (node.left != null) {
				inOrder(node.left);
			}
			System.out.print(node.n);
			if (node.right != null) {
				inOrder(node.right);
			}
		}

		public void postOrder(Node node) {
			if (node.left != null) {
				postOrder(node.left);
			}
			if (node.right != null) {
				postOrder(node.right);
			}
			System.out.print(node.n);

		}

	}

	static class Node {
		char n;
		Node left, right;

		public Node(char n) {
			this.n = n;
		}

	}

}
