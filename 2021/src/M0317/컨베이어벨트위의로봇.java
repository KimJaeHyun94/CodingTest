package M0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 컨베이어벨트위의로봇 {
	static int N, K;
	static int belt[];
	static boolean robot[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		belt = new int[2 * N];
		robot = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		int ans = 0;
		while (true) {
			ans++;
			Rotate();
			if (isOK()) { // 모두 다 내구도가 0이 되었다면 종료
				break;
			}
		}
		System.out.println(ans);
	}

	private static void Rotate() {
		int temp = belt[2 * N - 1]; // 처음 위치에 넣을 값
		for (int i = 2 * N - 1; i >= 1; i--) {
			belt[i] = belt[i - 1];
		}
		belt[0] = temp;

		for (int i = N - 1; i >= 1; i--) {
			robot[i] = robot[i - 1];
		}
		robot[0] = false; // 처음은 비워두기

		robot[N - 1] = false; // 맨 마지막 친구는 떨어집니다.
		for (int i = N - 2; i >= 0; i--) { // 로봇이 이동하기
			if (robot[i] && !robot[i + 1] && belt[i+1] > 0) {
				belt[i+1]--;
				robot[i + 1] = true;
				robot[i] = false;
			}
		}
		if (belt[0] > 0 && !robot[0]) {
			belt[0]--;
			robot[0] = true;
		}
	}

	private static boolean isOK() {
		int cnt = 0;
		for (int i = 0; i < 2 * N; i++) {
			if (belt[i] == 0) {
				cnt++;
			}
			if (cnt >= K) { // 내구도가 0인것이 K개 이상이면 종료
				return true;
			}
		}
		return false;
	}
}
