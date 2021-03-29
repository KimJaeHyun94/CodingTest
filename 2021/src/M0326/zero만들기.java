package M0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class zero만들기 {
	static int N;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (int t = 0; t < TC; t++) {
			N = Integer.parseInt(br.readLine());

			String str = "1"; // 첫 시작은 1
			DFS(0, 1, 1, 1, str);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void DFS(int sum, int sign, int num, int now, String str) {	//합계, 
		if(now==N) {
			sum = sum+(num*sign);
			if(sum==0) {
				sb.append(str).append("\n");
			}
			return;
		}
		
		DFS(sum, sign, num*10 + (now+1), now+1, str + " "+String.valueOf(now+1));
		DFS(sum + (sign* num), 1, now+1, now+1, str + "+" + String.valueOf(now+1));
		DFS(sum + (sign*num), -1, now+1, now+1, str + "-" + String.valueOf(now+1));
	}

}
