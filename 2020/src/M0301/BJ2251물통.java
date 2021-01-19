package M0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2251물통 {
	private static int A;
	private static int B;
	private static int C;
	static ArrayList<Integer> arr=new ArrayList();
	static boolean visited[][][] = new boolean[201][201][201];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		BFS(0, 0, C);
		Collections.sort(arr);
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(arr.get(i)+" ");
		}
	}

	private static void BFS(int x, int y, int z) {
		Queue<water> queue = new LinkedList<>();
		queue.add(new water(0, 0, z));

		while (!queue.isEmpty()) {
			water temp = queue.poll();
			int a = temp.acup;
			int b = temp.bcup;
			int c = temp.ccup;
			if (visited[a][b][c])
				continue;
			visited[a][b][c] = true;

			if (a == 0) {
				arr.add(c);
			}
			//A->B
			if(a+b>B)queue.add(new water(a+b-B,B,c));
			else queue.add(new water(0,a+b,c));
			//A->C
			if(a+c>C)queue.add(new water(a+c-C,b,C));
			else queue.add(new water(0,b,a+c));
			//B->C
			if(b+c>C)queue.add(new water(a,b+c-C,C));
			else queue.add(new water(a,0,b+c));
			//B->A
			if(b+a>A)queue.add(new water(A,b+a-A,c));
			else queue.add(new water(b+a,0,c));
			//C->B
			if(c+b>B)queue.add(new water(a,B,c+b-B));
			else queue.add(new water(a,c+b,0));
			//C->A
			if(c+a>A)queue.add(new water(A,b,c+a-A));
			else queue.add(new water(c+a,b,0));
		}
	}

	static class water {
		int acup;
		int bcup;
		int ccup;

		public water(int a, int b, int c) {
			super();
			this.acup = a;
			this.bcup = b;
			this.ccup = c;
		}
	}
}
