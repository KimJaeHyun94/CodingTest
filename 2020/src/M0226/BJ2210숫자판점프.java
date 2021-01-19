package M0226;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class BJ2210숫자판점프 {
	private static int map[][] = new int[5][5];
	private static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static LinkedHashSet<String> set = new LinkedHashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				DFS(i, j, 0, "");
			}
		}
		
		System.out.println(set.size());
	}

	private static void DFS(int a, int b, int ti, String str) {
		if (ti == 6) {
			
			set.add(str);
			return;
		} else {
			for (int i = 0; i < dir.length; i++) {
				int dy = a + dir[i][0];
				int dx = b + dir[i][1];

				if (isOK(dy, dx)) {
					DFS(dy, dx, ti + 1, str + map[dy][dx]);
				}
			}
		}
	}
	private static boolean isOK(int dy, int dx) {
		if (dy >= 0 && dy < 5 && dx >= 0 && dx < 5)
			return true;
		return false;
	}

}
