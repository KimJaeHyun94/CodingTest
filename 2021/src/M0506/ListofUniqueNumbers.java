package M0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ListofUniqueNumbers {
	static int N;
	static int arr[];
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		visited = new boolean[100001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = 0;
		long result = 0;
		while (right < N) {
			int val = arr[right];
			while (visited[val]) {
				result += (right - left);
				visited[arr[left++]] = false;
			}
			visited[val] = true;
			right++;
		}
		result +=(long)(right-left)*(right-left+1)/2;
		System.out.println(result);
	}
}
