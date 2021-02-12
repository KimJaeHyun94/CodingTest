package M0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * 
 * @author AKKabiyo
 * @See https://www.tutorialspoint.com/preorder-from-inorder-and-postorder-traversals-in-cplusplus
 */
public class 트리의순회_Stack {
	static int N, postindex;
	static int inOrder[];
	static int postOrder[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		inOrder = new int[N];
		postOrder = new int[N];
		for (int i = 0; i < N; i++) { // l -> 본 -> r
			inOrder[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken()); // l -> r -> 본
		}
		PreOrderTraversal();
		System.out.println(sb);
	}

	private static void PreOrderTraversal() {
		int len = N;
		postindex = N - 1;
		Stack<Integer> preorder = new Stack<Integer>();
		preOrder(0, len - 1, preorder);
		while (!preorder.isEmpty()) {
			sb.append(preorder.pop() + " ");
		}
	}

	private static void preOrder(int start, int end, Stack<Integer> preorder) {
		if (start > end)
			return;
		int val = postOrder[postindex];
		int inindex = searchValue(val);
		postindex--;
		preOrder(inindex + 1, end, preorder);  //왼쪽 탐색
		preOrder(start, inindex - 1, preorder);  //오른쪽 탐
		preorder.push(val);
	}

	private static int searchValue(int val) {
		int i = 0;
		for (i = 0; i < inOrder.length; i++)
			if (inOrder[i] == val)
				return i;
		return i;
	}
}
