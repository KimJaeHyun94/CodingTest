package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
 

public class 디저트까페sol1 {
    static int N; //map의 크기
    static int max; //디저트를 먹은 갯수
    static int sr; //시작좌표
    static int sc; //시작좌표
    static int[][] map;
    static boolean[][] visit; //방문처리 배열
    static HashSet<Integer> list = new HashSet<>(); //디저트 중복 처리
//                              우하 -> 좌하 -> 좌상 -> 우상
    static int[][] direction = {{1,1},{1,-1},{-1,-1},{-1,1}};
     
    public static void main(String[] args) throws NumberFormatException, IOException {
//      입력 처리
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(in.readLine());
         
        for (int t = 1; t<= TC; t++) {
//          max값을 0으로 초기화
            max = 0;
//          N을 입력 받아 N*N배열 생성
            N = Integer.parseInt(in.readLine());
            map = new int[N][N];
//          N*N boolean 타입의 visit 배열 생성
            visit = new boolean[N][N];
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
//                  set(중복확인)을 초기화
                    list.clear();
//                  visited 배열 초기화
                    for (int k = 0; k < N; k++) {
                        Arrays.fill(visit[k], false);
                    }
//                  dfs 탐색(i,j,d)
                    dfs(i, j, 0);
                }
            }       
//      출력 max==0? -1:max;
            System.out.println("#"+t+" "+(max==0?-1:max));
        }
    }
     
    private static void dfs(int r, int c, int dir) {
//      현재 노드에 대한 방문 표시
        visit[r][c] = true;
//      set에 노드 추가
        list.add(map[r][c]);
//      현재 방향에서 부터 <4 반복하면서
        for (int d = dir; d < 4; d++) {
            int nr = r + direction[d][0];
            int nc = c + direction[d][1];
            int cnt = list.size();
//          다음좌표 얻어오기 -> 다음좌표가 시작 좌표이고, cnt>=4인지 검사 : 한바퀴 탐색
            if(nr == sr && nc == sc && cnt >= 4) {
//              한바퀴 돌았으면 -> max 값 갱신(cnt과 max값 비교해서) 
                if(cnt > max) {
                    max = cnt;
                    return;
                }
            }
//          다음좌표가 경계내에 있고, 방문한 적이 없고, 처음 먹어보는 디저트라면
            if(nr > -1 && nr < N && nc > -1 && nc < N //경계 내에 있고
                    && !visit[nr][nc]   //방문한 적 없고
                    && !list.contains(map[nr][nc])) {   //처음 먹어보는 디저트인지 확인
//              다음 노드로 이동하기 => 재귀호출
                dfs(nr, nc, d);
            }
        }
//      visited 배열에서 현재 노드 대한 방문 표시한 것 해체
        visit[r][c] = false;
//      set에서 현재 노드를 제거
        list.remove(map[r][c]);
    }
}
