package M0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문자열생성 {
	static int N, cnt = 0, left, right;
	static char arr[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new char[N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().charAt(0);
		}

		left = 0;
		right = N - 1;
		while (cnt < N) {
			char le = arr[left];
			char ri = arr[right];
			if (le < ri) {
				sb.append(le);
				left++;
			} else if (le > ri) {
				sb.append(ri);
				right--;
			} else {
				int l = left + 1;
				int r = right - 1;
				while (l < r) {
					if (arr[l] != arr[r]) {
						break;
					}
					l++;
					r--;
				}
				if (arr[l] < arr[r]) {
					sb.append(arr[left]);
					left++;
				} else {
					sb.append(arr[right]);
					right--;
				}

			}
			if (++cnt % 80 == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

}