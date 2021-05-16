package M0514;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class 시리얼번호 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<String> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			list.add(br.readLine());
		}
		
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(o1.length()==o2.length()) {
					int sum1 = summit(o1);
					int sum2 = summit(o2);
					
					if(sum1==sum2) {
						return o1.compareTo(o2);
					}else
						return sum1-sum2;
					
				}
				return o1.length()-o2.length();
			}

			private int summit(String str) {
				int sum = 0;
				for (int i = 0; i < str.length(); i++) {
					int ch = str.charAt(i);
					if(ch>='0' && ch<='9') {
						sum+=ch-'0';
					}
				}
				return sum;
			}
		});
			
		for (String string : list) {
			System.out.println(string);
		}
	}
	
}
	

