package M0312;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ5639이진검색트리 {
	static Node root;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str="";
		while ((str=br.readLine())!=null && str.length()!=0) {
				addNode(Integer.parseInt(str));
		}
		postorder(root);
		
	}

	private static boolean addNode(int v) {
		Node newNode = new Node(v);
		if (root == null) {
			root = newNode;
			return true;
		}
		Node current = root;
		while (true) {
			if (current.v == v) {
				return false;
			} else if (current.v > v) {
				if (current.l == null) {
					newNode.p = current;
					current.l = newNode;
					return true;
				} else {
					
					current = current.l;
				}
			} else {
				if (current.r == null) {
					newNode.p = current;
					current.r = newNode;
					return true;
				} else {
					current = current.r;
				}
			}
		}
	}

	public static void postorder(Node node) {
		if (node != null) {
			postorder(node.l);
			postorder(node.r);
			System.out.println(node.v);
		}
	}

	static class Node {
		int v;
		Node l, r, p;

		public Node(int v) {
			super();
			this.v = v;
		}
	}
}
