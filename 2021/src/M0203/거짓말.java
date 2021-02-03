package M0203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 거짓말 {
	static int N, M;
	static boolean Truth[];
	static List<Integer>[] Party;
	static boolean know[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Party = new List[M];
		Truth = new boolean[N + 1];
		know = new boolean[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			Party[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
		for (int i = 0; i < T; i++) {
			Truth[Integer.parseInt(st.nextToken())] = true; // 진실을 아는 사람들의 번호
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				Party[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 0; i < M; i++) {
			if (Party[i].size() > 1) {
				for (int j = 0; j < Party[i].size() - 1; j++) {
					know[Party[i].get(j)][Party[i].get(j + 1)] = know[Party[i].get(j + 1)][Party[i].get(j)] = true;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (Truth[i]) {
				Check(i);
			}
		}
		int ans = 0;
		for (int i = 0; i < M; i++) {
			if (!Truth[Party[i].get(0)]) {
				ans++;
			}
		}
		System.out.println(ans);
	}

	private static void Check(int cnt) {
		for (int i = 1; i <= N; i++) { // 1번부터 N번까지
			if (know[cnt][i] && !Truth[i]) { // 이미 같은 반이여서 알고 있지만 원래 알지 않은 사람을 바꿔준다(진실을 아는 사람으로)
				Truth[i] = true;
				Check(i);
			}
		}
	}
}
