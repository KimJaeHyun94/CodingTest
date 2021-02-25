package M0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리의순회 {
	static int N;
	static int inOrder[];
	static int postOrder[];
	static int position[]; // 위치 저장 배열
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		inOrder = new int[N + 1];
		postOrder = new int[N + 1];
		position = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			position[inOrder[i]] = i; // 위치를 저장해둔다. l->본->r 이므로 inorder를 통해서
		}

		solution(1, N, 1, N); // 자리찾기
		System.out.println(sb);
	}

	private static void solution(int instart, int inend, int postart, int postend) {
		if (instart > inend || postart > postend) {
			return;
		}
		int root = postOrder[postend];   //postorder의 마지막 번째가 루트인 것을 활용한다. 
		
		sb.append(root+" ");
		int idx = position[root];  //인덱스 (루트)
		solution(instart, idx - 1, postart, postart - instart + idx - 1);  
		solution(idx + 1, inend, postend - inend + idx, postend - 1);
		
	}
}
