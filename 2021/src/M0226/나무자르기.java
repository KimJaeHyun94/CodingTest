package M0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나무자르기 {
	static int N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int tree[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(tree);

		long left = 0;
		long right = tree[N - 1];
		long mid = 0;
		long h = 0; // 가장 작은 나무부터 높이 시작

		while (left <= right) {
			mid = (left + right) >> 1;
			h = 0;

			for (int tr : tree) {
				if (tr> mid) // 가운데 길이대로 자른다고 하면
					h += tr - mid;
			}
			if (h >= M) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		}
		System.out.println(right);
	}
}
