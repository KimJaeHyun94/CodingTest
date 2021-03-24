package M0320;

import java.util.ArrayList;
import java.util.Arrays;

public class 라인4 {
	public static void main(String[] args) {
		String[] data = { "1 BROWN 0", "2 CONY 0", "3 DOLL 1", "4 DOLL 2", "5 LARGE-BROWN 3", "6 SMALL-BROWN 3",
				"7 BLACK-CONY 4", "8 BROWN-CONY 4" };
		String word = "BROWN";

		System.out.println(Arrays.toString(solution(data, word)));
	}

	public static String[] solution(String[] data, String word) {
		String[] answer = {};
		ArrayList<BinaryTree> list = new ArrayList<>();
		for (String str : data) {
			String[] st = str.split(" ");
			int id = Integer.parseInt(st[0]);
			String name = st[1];
			int parent = Integer.parseInt(st[2]);

			if (parent == 0) {
				BinaryTree tree = new BinaryTree();
				tree.insert(id, name, parent);
				list.add(tree);
			}

		}

		return answer;
	}

	static class Node {
		int id;
		String name;
		Node left;
		Node right;

		public Node(int id, String name, Node left, Node right) {
			this.id = id;
			this.name = name;
			this.left = left;
			this.right = right;
		}
	}

	static class BinaryTree {
		Node root = null;

		public void insert(int id, String name, int parent) {
			if (parent == 0) {
				root = new Node(id, name, null, null);
			} else {
				Node head = root;
				Node current;
				while (true) {
					current = root;

					if (id == current.id) {
						if (current.left == null) {
							current.left = new Node(id, name, null, null);
						} else {
							current.right = new Node(id, name, null, null);
						}
					}
					
				}
			}
		}

	}
}
