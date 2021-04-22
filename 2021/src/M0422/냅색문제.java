package M0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 냅색문제 {
	static int N, C;
	static int arr[];
	static ArrayList<Integer> llist, rlist;
	static int idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		llist = new ArrayList<>();
		rlist = new ArrayList<>();
		left(0, 0);
		right(N / 2, 0);
		Collections.sort(llist);
		Collections.sort(rlist);
		int ans = 0;
		for (int i = 0; i < llist.size(); i++) {
			ans += BinarySearch(0, rlist.size() - 1, llist.get(i));
		}

		System.out.println(ans);
	}

	private static void left(int i, int sum) {
		if (sum > C) {
			return;
		}
		if (i == N / 2) {
			llist.add(sum);
			return;
		}
		left(i + 1, sum + arr[i]);
		left(i + 1, sum);
	}

	private static void right(int i, int sum) {
		if (sum > C) {
			return;
		}
		if (i == N) {
			rlist.add(sum);
			return;
		}
		right(i + 1, sum + arr[i]);
		right(i + 1, sum);
	}

	static int BinarySearch(int start, int end, int target) {
		while (start <= end) {
			int mid = (start + end) >> 1;

			if (rlist.get(mid) + target <= C) {
				start = mid + 1;
			} else
				end = mid - 1;
		}
		return start;
	}

}