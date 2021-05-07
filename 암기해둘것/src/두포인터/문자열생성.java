package 두포인터;

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
		
		while (true) {
			if (left == right) {
				cnt++;
				add(arr[left]);
				break;
			}
			add(sol(arr[left], arr[right]));
		}
		System.out.println(sb);
	}

	private static void add(char c) {
		sb.append(c);
		cnt++;

		if (cnt % 80 == 0) {
			sb.append("\n");
		}
	}

	static char sol(char le, char ri) {
		char result;
		if (le < ri) {
			left++;
			return le;
		} else if (le > ri) {
			right--;
			return ri;
		} else {
			if (arr[left + 1] < arr[right - 1]) {
				left++;
				return le;
			} else if (arr[left + 1] > arr[right - 1]) {
				right--;
				return ri;
			} else {
				int l = left;
				int r = right;
				int cnt = 0;

				result = arr[left];
				while (l + cnt <= r - cnt) {
					if (arr[l + cnt] < arr[r - cnt]) {
						left++;
						return result;
					} else if (arr[l + cnt] > arr[r - cnt]) {
						result = arr[right];
						right--;
						return result;
					}
					cnt++;
				}
				left++;
			}
		}
		return result;
	}
}