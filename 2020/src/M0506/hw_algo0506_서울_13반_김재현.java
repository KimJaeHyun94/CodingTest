package M0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hw_algo0506_서울_13반_김재현 {
	public static int win[] = new int[6];
	public static int draw[] = new int[6];
	public static int lose[] = new int[6];
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int a = 0; a < 4; a++) { // 4가지 경우의 수
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) { // 6개의 조
				win[i] = Integer.parseInt(st.nextToken());
				draw[i] = Integer.parseInt(st.nextToken());
				lose[i] = Integer.parseInt(st.nextToken());
			}

			flag = false;

			dfs(0, 1);

			if (flag) {
				System.out.print(1 + " ");
			} else
				System.out.print(0 + " ");
		}
	}

	private static void dfs(int i, int j) {
		if (i == 5) { // 6개의 조 모두 계산했을때
			int swin = 0, sdraw = 0, slose = 0;
			for (int s = 0; s < 6; s++) {
				swin += win[s];
				sdraw += draw[s];
				slose += lose[s];
			}
			if (swin + slose + sdraw == 0) {
				flag = true;
			} else
				flag = false;
			return;
		}
//		System.out.println(i+" "+j);
		if (win[i] > 0 && lose[j] > 0) { // 이길 수 있을 때
			win[i]--;
			lose[j]--;

			if (j == 5) {
				dfs(i + 1, i + 2);
			} else
				dfs(i, j + 1);

			win[i]++;
			lose[j]++;
		}
		if (lose[i] > 0 && win[j] > 0) {
			lose[i]--;
			win[j]--;

			if (j == 5) {
				dfs(i + 1, i + 2);
			} else
				dfs(i, j + 1);
			lose[i]++;
			win[j]++;
		}
		if (draw[i] > 0 && draw[j] > 0) {
			draw[i]--;
			draw[j]--;

			if (j == 5) {
				dfs(i + 1, i + 2);
			} else
				dfs(i, j + 1);
			draw[i]++;
			draw[j]++;
		}
	}

	private static boolean isOK(int i, int j) {
		return i >= 0 && i < 6 && j > 0 && j < 6;
	}
}
