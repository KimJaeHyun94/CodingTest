package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* visit 1차원으로 수정
 * set 사용 x
 * */

public class 디저트까페sol2 {
	static int N; // map의 크기
	static int max; // 디저트를 먹은 갯수
	static int sr; // 시작좌표
	static int sc; // 시작좌표
	static int[][] map;
	static boolean[] visit; // 방문처리 배열
//                              우하 -> 좌하 -> 좌상 -> 우상
	static int[][] direction = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
//      입력 처리
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());

		for (int t = 1; t <= TC; t++) {
//          max값을 0으로 초기화
			max = 0;
//          N을 입력 받아 N*N배열 생성
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
//          N*N boolean 타입의 visit 배열 생성
			visit = new boolean[101];
//          데이터 읽어오기
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

//      탐색(dfs)
//          N*N을 돌면서 i,j번째의 카페 부터 투어 시작
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
//              시작 좌표를 i,j로 설정 해줘야함.
					sr = i;
					sc = j;
//                  visited 배열 초기화
					Arrays.fill(visit, false);

//                  dfs 탐색(i,j,d)
					dfs(i, j, 0, 1);
				}
			}
//      출력 max==0? -1:max;
			System.out.println("#" + t + " " + (max == 0 ? -1 : max));
		}
	}

	private static void dfs(int r, int c, int dir, int cnt) {
//      현재 노드에 대한 방문 표시
		visit[map[r][c]] = true;

//      현재 방향에서 부터 <4 반복하면서
		for (int d = dir; d < 4; d++) {
			int nr = r + direction[d][0];
			int nc = c + direction[d][1];

//          다음좌표 얻어오기 -> 다음좌표가 시작 좌표이고, cnt>=4인지 검사 : 한바퀴 탐색
			if (nr == sr && nc == sc && cnt >= 4) {
//              한바퀴 돌았으면 -> max 값 갱신(cnt과 max값 비교해서) 
				if (cnt > max) {
					max = cnt;
					return;
				}
			}
//          다음좌표가 경계내에 있고, 방문한 적이 없고, 처음 먹어보는 디저트라면
			if (nr > -1 && nr < N && nc > -1 && nc < N // 경계 내에 있고
					&& !visit[map[nr][nc]]) {
//              다음 노드로 이동하기 => 재귀호출
				dfs(nr, nc, d, cnt + 1);
			}
		}
//      visited 배열에서 현재 노드 대한 방문 표시한 것 해체
		visit[map[r][c]] = false;

	}
}