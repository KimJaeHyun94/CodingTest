package M0125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수이어쓰기2반복문 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int len = 1; // 자릿수
		long num = 0;
		long end = 9; // 자릿수의 마지막 숫자
		long tmp = k; // 찾아야 될 숫자

		while (tmp > len * end) { // 자릿수 벗어나면 나가기
			tmp -= (len * end); // 자릿수로 빼주기
			num += end;
			len++; // 자릿수 증가
			end *= 10; // 마지막 자리숫자 증가
		}

		num = (num + 1) + (tmp - 1) / len; // num -> 마지막 숫자들 더해놓은거(작은것들) tmp -> 자릿수 빼준것들 len -> 자릿수 (길이)
		String str = String.valueOf(num);
		int idx = (int)(tmp-1) % len;	//나머지를 통해 자릿수 정하기 
		if (num > N) {	//구할 수 없는 숫자이므로
			System.out.println(-1);
		}else {
			System.out.println(str.charAt(idx));   
		}  

	}

}
