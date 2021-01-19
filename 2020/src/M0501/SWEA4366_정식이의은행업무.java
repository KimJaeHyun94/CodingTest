package M0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class SWEA4366_정식이의은행업무 {

	static Long ans;
	static long two[];
	static long three[];
	static String binary, tenary;
	static ArrayList<Long> bin;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			binary = br.readLine();
			tenary = br.readLine();
			bin = new ArrayList<>();
			
			two = new long[binary.length()];
			three = new long[tenary.length()];

			ans = (long) 0;
			for (int i = 0; i < two.length; i++) {
				two[i] = binary.charAt(i) - '0';
			}
			for (int i = 0; i < three.length; i++) {
				three[i] = tenary.charAt(i) - '0';
			}
			Solution();
			sb.append("#" + tc + " " + ans+"\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static void Solution() {
		long twoclone[] = two.clone();
		long threeclone[] = three.clone();

		for (int i = 0; i < twoclone.length; i++) {
			twoclone = two.clone();
			if (two[i] == 0)
				twoclone[i] = 1;
			else
				twoclone[i] = 0;
			bin.add(sol(twoclone, 2));
		}

		for (int i = 0; i < tenary.length(); i++) {
		
			threeclone = three.clone();
			
			for (int j = 0; j < 2; j++) {
				
				if (threeclone[i] == 0)
					
					threeclone[i] = 1;
				
				else if (threeclone[i] == 1)
					
					threeclone[i] = 2;
				
				else
					threeclone[i] = 0;
				
				long temp = sol(threeclone, 3);
				if (bin.contains(temp)) {
					ans = temp;
					return;
				}
			}
		}
	}

	public static long sol(long[] twoclone, int baseType) {
		// 최종 반환값
		long ret = 0;

		for (int i = 0; i < twoclone.length; i++) {
			ret += Math.pow(baseType, twoclone.length - 1 - i) * twoclone[i];
		}
		return ret;
	}
}
