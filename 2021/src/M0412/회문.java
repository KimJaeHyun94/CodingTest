package M0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 회문 {
	static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < T; i++) {
			String line = br.readLine();
			StringBuilder sb2 = new StringBuilder(line);
			if (isPalin(line)) {
				sb.append("0").append("\n");
			} else {
				int left = 0;
				int right = line.length() - 1;

				while (left < right) {
					if (line.charAt(left) != line.charAt(right))
						break;
					left++;
					right--;
				}
				StringBuilder sb3 = new StringBuilder(sb2);
				String lremove = sb2.deleteCharAt(left).toString();
				String rremove = sb3.deleteCharAt(right).toString();
				
				if(isPalin(lremove) || isPalin(rremove)) {
					sb.append("1").append("\n");
				}else {
					sb.append("2").append("\n");
				}
				
			}

		}
		System.out.println(sb);

	}

	private static boolean isPalin(String line) {
		StringBuilder sb = new StringBuilder(line);
		String reverse = sb.reverse().toString();
		if (line.equals(reverse)) {
			return true;
		} else
			return false;
	}
}
