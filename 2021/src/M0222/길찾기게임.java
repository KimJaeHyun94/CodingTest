package M0222;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class 길찾기게임 {
	static int index;

	public static void main(String[] args) {
		int[][] ans = solution(new int[][] { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 },
				{ 7, 2 }, { 2, 2 } });

		for (int i = 0; i < 2; i++) {
			System.out.println(Arrays.toString(ans[i]));
		}
	}

	public static int[][] solution(int[][] nodeinfo) {
		int[][] answer = {};
		List<Node> list = new LinkedList<>();
		for (int i = 0; i < nodeinfo.length; i++) {
			list.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1)); // 처음부터 하나씩 넣는다
		}
		Collections.sort(list, new Comparator<Node>() {

			@Override
			public int compare(Node n0, Node n1) {
				if (n0.c != n1.c) {
					return n1.c - n0.c; // 가로는 작은것부터
				} else {
					return n0.r - n1.r; // 세로는 큰것부터
				}
			}
		});

		Tree tree = new Tree();
		for (int i = 0; i < list.size(); i++) {
			tree.insert(list.get(i));
		}

		answer = new int[2][list.size()]; // preorder, postorder
		index = 0;
		tree.preOrder(answer, tree.root);
		index = 0;
		tree.postOrder(answer, tree.root);
		return answer;
	}

	static class Tree {
		Node root = null;

		public void insert(Node node) {
			if (root == null) {
				root = node;
			} else {
				Node temp = root;
				while (true) {
					if (temp.r > node.r) {
						if (temp.left == null) {
							temp.left = node;
							return;
						}
						temp = temp.left;
					} else {
						if (temp.right == null) {
							temp.right = node;
							return;
						}
						temp = temp.right;
					}
				}
			}
		}

		public void preOrder(int arr[][], Node node) {
			if (node != null) {
				arr[0][index++] = node.n;
				preOrder(arr, node.left);
				preOrder(arr, node.right);

			}
		}

		public void postOrder(int arr[][], Node node) {
			if (node != null) {
				postOrder(arr, node.left);
				postOrder(arr, node.right);
				arr[1][index++] = node.n;
			}
		}
	}

	static class Node {
		int r;
		int c;
		int n;
		Node left;
		Node right;

		public Node(int r, int c, int n) {
			this.r = r;
			this.c = c;
			this.n = n;
		}

	}
}
