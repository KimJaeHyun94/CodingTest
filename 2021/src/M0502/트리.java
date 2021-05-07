package M0502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리 {
	static StringBuilder sb = new StringBuilder();
	static int preorder[];
	static int inorder[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			preorder = new int[N];
			inorder = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				preorder[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				inorder[i] = Integer.parseInt(st.nextToken());
			}
			findPostOrder(0,0,N);
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	private static void findPostOrder(int root, int s, int e) {
		for (int i = s; i < e; i++) {
			if(inorder[i]==preorder[root]) {
				findPostOrder(root+1, s, i);
				findPostOrder(root+i+1-s, i+1, e);
				sb.append(preorder[root]+" ");
			}
		}
		
	}
}
