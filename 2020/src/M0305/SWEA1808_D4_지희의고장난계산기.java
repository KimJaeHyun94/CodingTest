package M0305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1808_D4_지희의고장난계산기 {
	static int X;
	static int min;
	static boolean[] btn;

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			// min값을 초기화
			min = Integer.MAX_VALUE;
			btn = new boolean[10]; // 10개의 button 생성
			StringTokenizer st = new StringTokenizer(in.readLine().trim(), " ");
			// btn 1이면 true로 설정
			int num;
			for (int i = 0; i < 10; i++) {
				num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					btn[i] = true;
				}
			}
			X = Integer.parseInt(in.readLine().trim());
			// 처리 dfs => 재귀함수
			find(X, 0);
			// 출력
			min=min == Integer.MAX_VALUE ? -1 : min;
			System.out.println("#"+ tc+" "+min);
		}
	}

	private static int find(int x, int cnt) {
		// basis case => 종료조건
		if (isMake(x + "")) {
			// x값이 주어진 모든 수를 누를 수 있는지 검사
			// x 길이를 리턴
			if(cnt==0) {
				min=len(x)+1;
			}
			return len(x)+1;
		}
		// 처리
		//result 값을 -1로 초기화
		int result=-1;
		//2 ~ 제곱근까지 반복(i)
		for (int i = 2, end=(int) Math.sqrt(x)+1; i <end; i++) {
//			i는 x의 약수, 모든 수를 누를 수 있는지 검사 
			if(x%i==0 && isMake(i+"")) {
//				i의 길이를 구해내고
				int len1=len(i)+1; //곱하기 버튼
//				몫이 x의 약수, 모든 수로 누를 수 있는지 검사 ==> 재귀 호출				
				int len2=find(x/i, cnt+1);
//				몫이 -1이 아니면 => x의 약수, 모든 수를 누를 수				
				if(len2>-1) {
//					i의 길이와 몫의 길이를 + * =
					result=len1+len2;
					if(result<min && x == X) {
//						결과가 min 비교해서 갱신
						min=result;	
					}

				}
			}
		}
		return result;

	}
	private static int len(int x) {
		return (int) Math.log10(x)+1;
	}
	private static boolean isMake(String x) {
		for (char c : x.toCharArray()) {
			if(!btn[c-'0'])
				return false;
		}
		return true;   //다 누를 수 있으므로
	}
}
