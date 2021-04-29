package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contact {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			String str = br.readLine();
			String ptn = "(100+1+|01)+";
			if(str.matches(ptn)) {
				sb.append("YES").append("\n");
			}else
				sb.append("NO").append("\n");
		}
		System.out.println(sb);
	}

}
