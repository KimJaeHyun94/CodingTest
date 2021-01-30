package M0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
 * @Author AKKabiyo
 * @Explanation : 같은 수를 여러 번 골라도 되는 조합
 */
public class N과M_08 {
	static int N, M;
	static int arr[];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		makeCombination(0, 0, "");
		System.out.println(sb);
	}

	private static void makeCombination(int start, int i, String s) {
		if (i == M) {
			sb.append(s.substring(0, s.length() - 1)).append("\n");
			return;
		}
		for (int j = start; j < N; j++) {
			makeCombination(j, i + 1, s + arr[j] + " ");

		}
	}

}