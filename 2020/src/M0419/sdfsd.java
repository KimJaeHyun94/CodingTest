package M0419;

import java.io.*;
import java.util.StringTokenizer;

public class sdfsd {
    static int N, L, result = 0;
    static int[][] map1, map2;
    static int[] slop;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map1 = new int[N][N];
        map2 = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map2[j][i] = map1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            checkBuild(i, map1);
          checkBuild(i, map2);
        }

        System.out.println(result);
    }

    private static void checkBuild (int index, int[][] arr) {
        slop = new int[N];
        for (int i = 0; i < N - 1; i++) {
            // 다음것과 같지 않은 경우에만 체크해준다.
            if (arr[index][i] != arr[index][i + 1]) {
                int diff = arr[index][i] - arr[index][i + 1];
                if (diff != -1 && diff != 1) return; // 차이가 1보다 크면 지나갈 수 없는 길이다.

                if (diff == -1) {
                    // 왼쪽 경사로
                    for (int j = 0; j < L; j++) {
                        if (i - j < 0 || slop[i - j] == 1) return;

                        if (arr[index][i] == arr[index][i - j]) slop[i - j] = 1;
                        else return;
                    }
                } else {
                    // 오른쪽 경사로
                    for (int j = 1; j <= L; j++) {
                        if (i + j >= N || slop[i + j] == 1) return;

                        if (arr[index][i] - 1 == arr[index][i + j]) slop[i + j] = 1;
                        else return;
                    }
                }
            }
        }
        result++;
    }
}