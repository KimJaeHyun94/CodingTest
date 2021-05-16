package M0510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z {
	static int N, R, C;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		Divide((int) Math.pow(2, N), 0, 0);
	}

	private static void Divide(int len, int x, int y) {

		if (x == R && y == C) { // 끝에 도달하면
			System.out.println(cnt); // 갯수 출력
			System.exit(0);
		}

		if (len == 1) { // 길이가 1이라면
			cnt++; // 하나씩 증가
			return;
		}

		if (!(x <= R && R < len + x && y <= C && C < len + y)) {	//그 사분면 안에 없으면 사분면을 건너 띈다(시간 효율)
			cnt += len * len;
			return;
		}
		Divide(len / 2, x, y);
		Divide(len / 2, x, y + len / 2);
		Divide(len / 2, x + len / 2, y);
		Divide(len / 2, x + len / 2, y + len / 2);
	}
}
