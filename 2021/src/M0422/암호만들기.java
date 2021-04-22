package M0422;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기 {
	static int N;
	static int M;
	static char map[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			map[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(map);

		Combination(0, 0, new char[N]);
	}

	private static void Combination(int start, int idx, char[] temp) {
		if (idx == N) {
			int mo = 0;
			int ja = 0;
			for (char c : temp) {
				if (isMO(c))
					mo++;
				else
					ja++;
			}
			if (mo >= 1 && ja >= 2) {
				for (char c : temp) {
					System.out.print(c);
				}
				System.out.println();
			}
			return;
		}

		for (int i = start; i < M; i++) {
			temp[idx] = map[i];
			Combination(i+1, idx+1, temp);
		}

	}

	private static boolean isMO(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
			return true;
		}
		return false;
	}

}
