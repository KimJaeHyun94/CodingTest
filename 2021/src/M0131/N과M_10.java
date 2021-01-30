package M0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
 * @Author AKKabiyo
 * @Explanation : 조합(같은 수 중복 불가) && 조합 간 중복 불가
 */
public class N과M_10 {
	static int N, M;
	static int arr[];

	static HashSet<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		set = new HashSet<>();
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		boolean visited[] = new boolean[N];

		makeCombination(0, 0, new int[M]);
		
	}

	private static void makeCombination(int start, int i, int temp[]) {
		if (i == M) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < temp.length; j++) {
				sb.append(temp[j] + " ");
			}
			if (!set.contains(sb.toString())) {
				set.add(sb.toString());
				System.out.println(sb);
			}
			return;
		}
		for (int j = start; j < N; j++) {
			temp[i] = arr[j];
			makeCombination(j+1, i+1, temp);

		}
	}

}