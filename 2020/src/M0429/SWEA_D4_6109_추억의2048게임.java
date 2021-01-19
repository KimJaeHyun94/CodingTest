package M0429;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 1. 블록 몰기
 * 2. 같은 숫자 합치기
 * 3. 다시 블록 몰기
 * */

public class SWEA_D4_6109_추억의2048게임 {
   static int[][] map;
   static int N;
   static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
   static String d;
   
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      
      StringBuilder sb = new StringBuilder();
      
      for (int t = 1; t <= T; t++) {
         sb.append("#").append(t).append("\n");
         StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         d = st.nextToken();
         map = new int[N][N];
         
         for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
               map[i][j] = Integer.parseInt(st.nextToken());
            }
         }
         
         move(d);
         merge(d);
         move(d);
         
         
         
         //sb에 정답 추가
         for (int i = 0; i <N; i++) {
            for (int j = 0; j < N; j++) {
               sb.append(map[i][j]+" ");
            }
            sb.append("\n");
         }
         
      }
      System.out.println(sb);
      
   }

   private static void merge(String d) {
      if(d.equals("up")) {
         for (int i = 0; i < N; i++) {
            int index = 0;
            while(index+1<N) {
               if(map[index][i]==map[index+1][i]) {
                  map[index][i] += map[index+1][i];
                  map[index+1][i] = 0;
                  index +=2;
               }else {
                  index++;
               }
            }   
         }
         
      }else if(d.equals("down")) {
         for (int i = 0; i < N; i++) {
            int index = N-1;
            while(index-1>=0) {
               if(map[index][i]==map[index-1][i]) {
                  map[index][i] += map[index-1][i];
                  map[index-1][i] = 0;
                  index -=2;
               }else {
                  index--;
               }
            }   
         }
      }else if(d.equals("right")) {
         for (int i = 0; i < N; i++) {
            int index = N-1;
            while(index-1>=0) {
               if(map[i][index]==map[i][index-1]) {
                  map[i][index] += map[i][index-1];
                  map[i][index-1] = 0;
                  index -=2;
               }else {
                  index--;
               }
            }   
         }
      }else if(d.equals("left")) {
         for (int i = 0; i < N; i++) {
            int index = 0;
            while(index+1<N) {
               if(map[i][index]==map[i][index+1]) {
                  map[i][index] += map[i][index+1];
                  map[i][index+1] = 0;
                  index +=2;
               }else {
                  index++;
               }
            }   
         }
      }
   }

   private static void move(String d) {
      if(d.equals("up")) {
         for (int i = 0; i < N; i++) {
            int index = 0;
            int[] temp = new int[N];
            for (int j = 0; j < N; j++) {
               if(map[j][i]!=0){
                  temp[index] = map[j][i];
                  index++;
               }
            }
            for (int j = 0; j < index; j++) {
               map[j][i] = temp[j];
            }
            for (int j = index; j < N; j++) {
               map[j][i] = 0;
            }
            
         }
         
      }else if(d.equals("down")) {
         for (int i = 0; i < N; i++) {
            int index = N-1;
            int[] temp = new int[N];
            for (int j = N-1; j >= 0; j--) {
               if(map[j][i]!=0){
                  temp[index] = map[j][i];
                  index--;
               }
            }
            for (int j = N-1; j > index; j--) {
               map[j][i] = temp[j];
            }
            for (int j = index; j >= 0; j--) {
               map[j][i] = 0;
            }
            
         }
         
      }else if(d.equals("right")) {
         for (int i = 0; i < N; i++) {
            int index = N-1;
            int[] temp = new int[N];
            for (int j = N-1; j >= 0; j--) {
               if(map[i][j]!=0){
                  temp[index] = map[i][j];
                  index--;
               }
            }
            for (int j = N-1; j > index; j--) {
               map[i][j] = temp[j];
            }
            for (int j = index; j >= 0; j--) {
               map[i][j] = 0;
            }
            
         }
      }else if(d.equals("left")) {
         for (int i = 0; i < N; i++) {
            int index = 0;
            int[] temp = new int[N];
            for (int j = 0; j < N; j++) {
               if(map[i][j]!=0){
                  temp[index] = map[i][j];
                  index++;
               }
            }
            for (int j = 0; j < index; j++) {
               map[i][j] = temp[j];
            }
            for (int j = index; j < N; j++) {
               map[i][j] = 0;
            }
            
         }
      }
   }
   
}