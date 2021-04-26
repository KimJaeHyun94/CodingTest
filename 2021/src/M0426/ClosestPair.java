package M0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ClosestPair {
	static int N, M;
	static int c1, c2;
	static ArrayList<Integer> listn;
	static ArrayList<Integer> listm;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		listn = new ArrayList<>();
		listm = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		c1 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());

		int miny = Math.abs(c1 - c2);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			listn.add(n);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int n = Integer.parseInt(st.nextToken());
			listm.add(n);
		}

		Collections.sort(listn);
		Collections.sort(listm);

		int left = 0;
		int right = 0;
		int cnt = 0;
		int ret = Integer.MAX_VALUE;

		while (left < N && right<M) {
			int temp = Math.abs(listn.get(left) - listm.get(right));
			if (temp < ret) {
				ret = temp;
				cnt = 0;
			}
			if (ret == temp) {
				cnt++;
			}
			if (listn.get(left) < listm.get(right))
				left++;
			else
				right++;
		}

		System.out.println(ret + miny + " " + cnt);
	}

}
