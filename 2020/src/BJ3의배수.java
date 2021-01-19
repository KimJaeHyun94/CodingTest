import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3의배수 {
	private static int N;
	private static int M;
	private static int arr[];
	private static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		if (N < 9) {
			System.out.println(0);
		} else if (N == 9)
			System.out.println(1);
		else {
			M = N / 3;
			arr = new int[M - 2];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = i + 1;
			}
			int check=N-=9;
			check/=3;
			M-=check;
			nextCombination(0, 0, new int[2]);
			System.out.println(cnt);
		}

	}

	private static void nextCombination(int r, int start, int[] temp) {
		int sum = 0;
		if (r == 2) {
			for (int i = 0; i < temp.length; i++) {
				sum += temp[i];
			}
			if (sum == M)
				cnt++;
		} else {
			for (int i = start; i < arr.length; i++) {
				temp[r] = arr[i];
				nextCombination(r + 1, i, temp);
			}
		}
	}
}
