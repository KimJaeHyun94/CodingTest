package M0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 비밀번호 {
	static int n, m;
	static int selected[];
	static int ans;
	static int num[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		selected = new int[m];
		num = new int[10];
		
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < m; i++) {
			selected[i] = Integer.parseInt(st.nextToken());
		}

		DFS(0);
		System.out.println(ans);
	}

	private static void DFS(int idx) {
		if (idx == n) {
			check();
			return;
		}

		for (int i = 0; i < 10; i++) {
			num[i]++;
			DFS(idx + 1);
			num[i]--;
		
		}

	}

	private static void check() {
		for (int i = 0; i < m; i++) {
			if(num[selected[i]]==0)
				return;
		}
		ans++;
		return;
	}

}
