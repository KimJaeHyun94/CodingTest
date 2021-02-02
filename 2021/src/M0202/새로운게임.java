package M0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * @See https://buddev.tistory.com/29?category=835285
 * @Author : AKKabiyo
 * @Helper : Beaverbae
 */
public class 새로운게임 {
   static int N, K;
   static int chess[][];
   static ArrayList<horse> list;
   static int dirs[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // 오른, 왼, 위, 아래
   static LinkedList<Node>[][] board;
   static int ans;
   static boolean end = false;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      chess = new int[N][N];
      list = new ArrayList<>();
      board = new LinkedList[N][N];
      for (int i = 0; i < N; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 0; j < N; j++) {
            chess[i][j] = Integer.parseInt(st.nextToken());
         }
      }

      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            board[i][j] = new LinkedList<>();
         }
      }

      for (int i = 0; i < K; i++) {
         st = new StringTokenizer(br.readLine());
         int r = Integer.parseInt(st.nextToken()) - 1;
         int c = Integer.parseInt(st.nextToken()) - 1;
         int d = Integer.parseInt(st.nextToken()) - 1;
         list.add(new horse(r, c));

         board[r][c].add(new Node(i, d));
      }
      boolean flag = true;
      while (true) {
         if (ans > 1000) {
            flag = false;
            break;
         }
         if (end) {
            break;
         }
         
         game();
         ans++;
      }
      if (flag) {
         System.out.println(ans);
      } else {
         System.out.println(-1);
      }
   }

   private static void game() {
      for (int i = 0; i < K; i++) {
         horse temp = list.get(i);
         int r = temp.r;
         int c = temp.c;
         int d = board[r][c].get(0).dir;
     
         if (board[r][c].get(0).num != i) { // 말이 없는 경우
            continue;
         }

         int nr = r + dirs[d][0];
         int nc = c + dirs[d][1];

         if (!isOK(nr, nc) || chess[nr][nc] == 2) { // 맵 밖을 벗어나거나 파란색인 경우 방향을 바꿔준다.
            d = turning(d); // 방향을 바꾼다
            ////// ADD BY BEAVEBAE //////
            board[r][c].get(0).dir = d;// 맨아래 말판 방향 면경
            nr = r + dirs[d][0];// 바뀐 방향으로 이동하려는 칸 변경
            nc = c + dirs[d][1];// 바뀐 방향으로 이동하려는 칸 변경
            /////////////////////////////
            if (!isOK(nr, nc) || chess[nr][nc] == 2) { // 또 그러면 걍 무시한다.
               continue;
            } else { // 돌아왔다면
               if (move(r, c, nr, nc)) {
                  end = true;
                  return;
               }
            }
         } else {
            if (move(r, c, nr, nc)) {
               end = true;
               return;
            }
         }
      }
   }

   private static boolean move(int r, int c, int nr, int nc) {
      if (chess[nr][nc] == 0) { // 흰색 일때는 이미 있는 것에 맨 위로 올려놓는다
         while (board[r][c].size() > 0) {
            Node first = board[r][c].pollFirst();
            list.get(first.num).r = nr;
            list.get(first.num).c = nc;
            board[nr][nc].add(first);
         }
      } else { // 빨간색 일때는 말의 쌓여있는 순서를 반대로 바꾼다
         while (board[r][c].size() > 0) {
            Node last = board[r][c].pollLast();
            list.get(last.num).r = nr;
            list.get(last.num).c = nc;
            board[nr][nc].add(last);
         }
      }

      if (board[nr][nc].size() >= 4)
         return true;
      else
         return false;
   }

   private static int turning(int d) {
      switch (d) {
      case 0:
         return 1;
      case 1:
         return 0;
      case 2:
         return 3;
      default:
         return 2;
      }
   }

   private static boolean isOK(int nr, int nc) {
      return nr >= 0 && nr < N && nc >= 0 && nc < N;
   }

   static class Node {
      int num, dir;

      public Node(int num, int dir) {
         this.num = num;
         this.dir = dir;
      }

      @Override
      public String toString() {
         return "Node [num=" + num + ", dir=" + dir + "]";
      }
   }

   static class horse {
      int r;
      int c;

      public horse(int r, int c) {
         this.r = r;
         this.c = c;
      }

      @Override
      public String toString() {
         return "horse [r=" + r + ", c=" + c + "]";
      }
   }
}