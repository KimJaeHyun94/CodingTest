package M0113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 부분수열의합 {
	static int arr[];
	static int N;
	static boolean visited[];
	static HashSet<Integer> set;
	static int max;
	static int ans=-1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		set = new HashSet<>();
		arr = new int[N];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		subset(0);
		for (int i = 1; i < max; i++) {
			if(!set.contains(i)) {
				System.out.println(i);
				System.exit(0);
				break;
			}
		}
		System.out.println(max+1);
	}
	

	private static void subset(int r) {
		if (r == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i])
					sum += arr[i];
			}
			max = Math.max(max, sum);
			set.add(sum);
			return;
		} else {
			visited[r] = true;
			subset(r + 1);
			visited[r] = false;
			subset(r + 1);
		}
	}

}
