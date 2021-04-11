package M0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 알파틱택토 {
	
	static int map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[3][3];
		int zero = 0;

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					zero++;
			}
		}
		int start;
		if (zero % 2 == 0) // 0이 짝수면 2부터 시작
			start = 2;
		else // 0이 홀수면 1부터 시작
			start = 1;

		int win = game(start);
		if (win == 1)
			System.out.println("W");
		else if (win == 0)
			System.out.println("D");
		else
			System.out.println("L");
	}

	private static int game(int start) {
		int ans = 2;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(map[i][j]==0) {
					map[i][j]= start;
					if(TicTac(start)) {
						ans = -1;
					}
					ans = Math.min(ans,  game(start==1? 2:1));
					map[i][j]=0;
				}
			}
		}
		if(ans==1) {
			return -1;	
		}else if(ans==0 || ans==2) {
			return 0;
		}else
			return 1;
	}

	private static boolean TicTac(int start) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i][j] != start) {
					break;
				}
				if (j == 2)
					return true;
			}
		}

		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 3; i++) {
				if (map[i][j] != start) {
					break;
				}
				if (i == 2)
					return true;
			}
		}
		
			for (int i = 0; i < 3; i++) {
				if (map[i][i] != start) {
					break;
				}
				if (i == 2)
					return true;
			}

		for (int i = 0; i < 3; i++) {
			if (map[i][2 - i] != start) {
				break;
			}
			if (i == 2)
				return true;
		}
		return false;
	}
}
