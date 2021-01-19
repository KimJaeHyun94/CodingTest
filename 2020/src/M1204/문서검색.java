package M1204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문서검색 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String document = br.readLine();
		String eng = br.readLine();
		int ans = 0;

		char arr[] = document.toCharArray();
		char check[] = eng.toCharArray();

		for (int c = 0; c <= arr.length - check.length; c++) {
			boolean flag = true;
			for (int d = 0; d < check.length; d++) {
				if (arr[c + d] != check[d]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				c += check.length - 1;
				ans++;
			}
		}

		System.out.println(ans);
	}
}
