package M0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 * 
 * @author 김재현
 * @See : https://sdesigner.tistory.com/63
 */
public class 부분수열의합2 {
	static int N, S;
	static int arr[];
	static ArrayList<Integer> llist, rlist;
	static int idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

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
		Collections.sort(rlist); // 중간에서 만나기

		int left = 0;
		int right = rlist.size() - 1;

		
		long ans = 0;
		while (left < llist.size() && right >= 0) {
			
			int lsum = llist.get(left);
			int rsum = rlist.get(right);
			
			if (lsum + rsum == S) {
				long lcnt = 0;

				while (left < llist.size() && llist.get(left) == lsum) {
					lcnt++;
					left++;
				}

				long rcnt = 0;
				while (right >= 0 && rlist.get(right) == rsum) {
					rcnt++;
					right--;
				}

				ans += lcnt * rcnt;
			}
			if (lsum + rsum > S) {
				right--;
			} else if (lsum + rsum < S) {
				left++;
			}
		}
        if (S == 0) {
			System.out.println(ans - 1);
		} else {
			System.out.println(ans);
		}
	}

	private static void left(int i, int sum) {

		if (i == N / 2) {
			llist.add(sum);
			return;
		}
		left(i + 1, sum + arr[i]);
		left(i + 1, sum);
	}

	private static void right(int i, int sum) {

		if (i == N) {
			rlist.add(sum);
			return;
		}
		right(i + 1, sum + arr[i]);
		right(i + 1, sum);
	}
}