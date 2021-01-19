package M0521;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class BJ1062_가르침 {
    static int N,K;
    static int max = 0;
    static boolean visited[] = new boolean[26];
    static String[] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new String[N];
       
        if(K<5) {
            System.out.println(0);
            return;
        }else if(K==26) { 
            System.out.println(N);
            return;
        }else {
            for(int n=0; n<N; n++) {
                String str = br.readLine();
                map[n] = str.replaceAll("[antic]", "");
            }
            K-=5;
            visited['a'-97]=true;
            visited['n'-97]=true;
            visited['t'-97]=true;
            visited['i'-97]=true;
            visited['c'-97]=true;
            dfs(0, 0);
            System.out.println(max);
        }
        
    }
    private static void dfs(int start, int cnt) {
        // TODO Auto-generated method stub
        if(cnt == K) {
            int count = 0;
            for(int i=0; i<N; i++) {
                boolean flag = true;
                for(int j=0; j<map[i].length(); j++) {
                    if(!visited[map[i].charAt(j)-97]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    count++;
                }
            }
            max = Math.max(max, count);
            return;
        }
        
        for(int i=start; i<26; i++) {
            if(!visited[i]) {
                visited[i]=true;
                dfs(i, cnt+1);
                visited[i]=false;
            }
        }
    }
}
