package M0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 오름세 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNext()) {
			int N = sc.nextInt();
			List<Integer> list = new ArrayList<>();
			list.add(0);
			for (int i = 0; i < N; i++) {
				int value = sc.nextInt();
				if (list.get(list.size() - 1) < value) {
					list.add(value);
				} else {
					int left = 1;
					int right = list.size() - 1;

					while (left <= right) {
						int mid = (left + right) >> 1;
						if (list.get(mid) < value) {
							left = mid + 1;
						} else {
							right = mid - 1;
						}
					}
					list.set(left, value);
				}
			}
			int ans = list.size() - 1;
			System.out.println(ans);
		}
	}
}
