package M0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리순회 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		BinaryTree tree = new BinaryTree();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char n = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			tree.insert(n, left, right);
		}
		tree.preOrder(tree.root);
		System.out.println();
		tree.inOrder(tree.root);
		System.out.println();
		tree.postorder(tree.root);

	}

	static class BinaryTree {
		Node root = null;

		public BinaryTree() {
		}

		public void postorder(Node temp) {
			if (temp.left != null)
				postorder(temp.left);
			if (temp.right != null)
				postorder(temp.right);
			System.out.print(temp.n);

		}

		public void inOrder(Node temp) {
			if (temp.left != null)
				inOrder(temp.left);
			System.out.print(temp.n);
			if (temp.right != null)
				inOrder(temp.right);

		}

		public void preOrder(Node temp) {
			System.out.print(temp.n);
			if (temp.left != null)
				preOrder(temp.left);
			if (temp.right != null)
				preOrder(temp.right);
		}

		void insert(char n, char left, char right) {
			if (root == null) {
				root = new Node(n);

				if (left != '.')
					root.left = new Node(left);
				if (right != '.')
					root.right = new Node(right);
			} else {
				search(root, n, left, right);
			}
		}

		private void search(Node node, char n, char left, char right) {
			if (node == null) {
				return;
			} else {
				if (node.n == n) {
					if (left != '.') {
						node.left = new Node(left);
					}
					if (right != '.') {
						node.right = new Node(right);
					}
				} else {
					search(node.left, n, left, right);
					search(node.right, n, left, right);
				}

			}
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
