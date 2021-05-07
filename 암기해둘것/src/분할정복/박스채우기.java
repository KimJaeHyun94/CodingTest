package 분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 박스채우기 {
	static int L, W, H, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		N = Integer.parseInt(br.readLine());

		int cube[] = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cube[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}

		long before = 0;
		long ans = 0;

		for (int i = N - 1; i >= 0; i--) {

			before <<= 3;

			long possible = (long) (L >> i) * (W >> i) * (H >> i) - before;
			long newCube = Math.min((long) cube[i], possible);

			before += newCube;
			ans += newCube;
		}
		if (before == (long) L * W * H) {
			System.out.println(ans);
		} else {
			System.out.println(-1);
		}

	}

}