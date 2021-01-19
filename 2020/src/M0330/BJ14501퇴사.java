package M0330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14501퇴사 {
	static int N;
	static int T[];
	static int P[];
	static int dp[];
	static int max;
	static boolean status[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int[16];
		P = new int[16];
		status = new boolean[16];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		DFS(0, 0);
		System.out.println(max);
	}

	private static void DFS(int r, int total) {
		if (r == N) {
			max = Math.max(max, total);
			return;
		} 
		else if(r>N) {
			return;
		}
		else {
			status[r] = true;
			DFS(r + T[r], total + P[r]);
			status[r] = false;
			DFS(r + 1, total);
		}
	}
}
