package M0209;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/*
 * @Author : AKKabiyo
 * @See : https://velog.io/@agugu95/Java-Binary-Tree-Binary-Search-Tree
 * 
 */
public class 길찾기게임 {
	static int index;
	static int node[][] = { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 },
			{ 2, 2 } };

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(node)[0]));
		System.out.println(Arrays.toString(solution(node)[1]));
	}

	public static int[][] solution(int[][] nodeinfo) {
		int[][] answer = {};
		List<Node> list = new ArrayList<>();

		int idx = 1;
		for (int i = 0; i < nodeinfo.length; i++) {
			list.add(new Node(nodeinfo[i][0], nodeinfo[i][1], idx++)); // 하나씩 값을 증가시킨다.
		}
		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node a0, Node a1) {
				if (a0.c != a1.c) {
					return a1.c - a0.c;
				} else {
					return a0.r - a1.r;
				}
			}
		});

		binaryTree tree = new binaryTree();

		for (Node node : list) {
			tree.insert(node);
		}

		answer = new int[2][list.size()];
		index = 0;
		tree.preOrder(answer, tree.root);
		index = 0;
		tree.postOrder(answer, tree.root);

		return answer;
	}

	static class binaryTree {
		Node root = null;
		public void insert(Node temp) {
			Node node = root;
			if (root == null) {
				root = temp;
			} else {
				while (true) {
					if (node.r > temp.r) {
						if (node.left == null) {
							node.left = temp;
							return;
						}
						node = node.left;
					} else {
						if (node.right == null) {
							node.right = temp;
							return;
						}
						node = node.right;
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
