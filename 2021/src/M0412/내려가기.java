package M0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내려가기 {
	static int min[];
	static int max[];
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		min = new int[3];
		max = new int[3];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int x3 = Integer.parseInt(st.nextToken());

			if (r == 0) {
				min[0] = max[0] = x1;
				min[1] = max[1] = x2;
				min[2] = max[2] = x3;
			} else {
				int b0 = max[0];
				int b2 = max[2];
	
				max[0] = Math.max(max[0], max[1]) + x1;
				max[2] = Math.max(max[1], max[2]) + x3;
				max[1] = Math.max(Math.max(b0, max[1]), b2) + x2;

				
				b0 = min[0];
				b2 = min[2];
			
				min[0] = Math.min(min[0], min[1]) + x1;
				min[2] = Math.min(min[1], min[2]) + x3;
				min[1] = Math.min(Math.min(b0, min[1]), b2) + x2;

			}
		}
		System.out.print(Math.max(Math.max(max[0], max[1]), max[2]) + " ");
		System.out.println(Math.min(Math.min(min[0], min[1]), min[2]));
	}
}
