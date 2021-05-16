package M0512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 문자열게임2 {
	static List<Integer> alphabet[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			String str = br.readLine();
			int K = Integer.parseInt(br.readLine());

			alphabet = new List[26];

			for (int i = 0; i < 26; i++) {
				alphabet[i] = new ArrayList<>();
			}

			for (int i = 0; i < str.length(); i++) {
				int n = str.charAt(i) - 'a';
				alphabet[n].add(i); // 각각 몇번째에 이 문자가 나오는지 체크
			}

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			boolean flag = false;
			for (int i = 0; i < 26; i++) {
				if (alphabet[i].size() < K)
					continue; // K보다 작은건 건너뛰기
				flag = true;
				for (int c = 0; c < alphabet[i].size() - K + 1; c++) { // K번씩 건너뛰면서 계산
					int n = alphabet[i].get(c + K - 1) - alphabet[i].get(c)+1;

					min = Math.min(min, n);
					max = Math.max(max, n);
				}

			}

			if (!flag) {
				sb.append(-1).append("\n");
			} else
				sb.append(min + " " + max).append("\n");
		}
		System.out.println(sb);
	}
}
