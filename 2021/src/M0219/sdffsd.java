package M0219;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class sdffsd {
    static ArrayList<Point> arrList = new ArrayList<>();
    static char chArr[] = {'|','-', '+', '1', '2', '3', '4'};
    static int moveX[] = {0,1,0,-1};
    static int moveY[] = {-1,0,1,0};
    static int R,C;
    static char arr[][];
    static boolean visit[][];
    static Point M,Z,tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        visit = new boolean[R][C];
        for(int r=0; r<R; r++) {
            String str = br.readLine();
            for(int c=0; c<C; c++) {
                char ch = str.charAt(c);
                if(ch == 'M')
                    M = new Point(c,r);
                else if(ch == 'Z')
                    Z = new Point(c,r);
                
                
                if(ch != '.' && ch != 'M' && ch!= 'Z')
                    arrList.add(new Point(c,r));
                
                arr[r][c] = ch;
            }
        }
        bfs();
        
        Point first = new Point(tmp);
        
        first.x = tmp.x;
        first.y = tmp.y;
        
        for(int d=0; d<7; d++) {
            arr[first.y][first.x] = chArr[d];
            visit = new boolean[R][C];
            Point second;
            bfs();
            second = new Point(tmp);
            if(second.x == -1 && second.y == -1) {
                if(checkAll()) {
                    System.out.println((first.y+1)+" "+(first.x+1)+ " "+arr[first.y][first.x]);
                    return;
                }
            }
        }
    }
    
    private static void bfs() {
        // TODO Auto-generated method stub
        Queue<Three> queue = new LinkedList<Three>();
        Three current = new Three(M.x, M.y, 0);
 
        boolean isTrue = false;
        queue.add(current);
        
        while(!queue.isEmpty()) {
            
            if(isTrue)
                break;
            
            Three t = queue.poll();
            
            int x = t.x;
            int y = t.y;
            int dir = t.dir;
            
            if(arr[y][x] == 'M') {
                for(int d=0; d<4; d++) {
                    int newX = x + moveX[d];
                    int newY = y + moveY[d];
 
                    if(0<=newX && newX<C && 0<=newY && newY<R) {
                        if(arr[newY][newX] == 'Z') {
                            continue;
                        }
                        if(arr[newY][newX] != '.') {
                            dir = getDir(d, arr[newY][newX]);
                            if(dir != -1) {
                                queue.add(new Three(newX, newY, dir));
                                if(!visit[newY][newX])
                                    visit[newY][newX] = true;
                            }
                        }
                    }
                }
   
                    
                
            }else {
                int newX = x + moveX[dir];
                int newY = y + moveY[dir];
                
                if(0<=newX && newX<C && 0<=newY && newY<R) {
                    if(arr[newY][newX] == '.') {
                        tmp = new Point(newX,newY);
                        return;
                    }
                    else if(arr[newY][newX] == 'Z') {
                        tmp = new Point(-1,-1);
                        return;
                    }
                    
                    dir = getDir(dir, arr[newY][newX]);
                    if(dir == -1) {
                        tmp = new Point(newX,newY);
                        return; 
                    }
                    if(!visit[newY][newX])
                        visit[newY][newX] = true;
                    
                    queue.add(new Three(newX,newY,dir));
                }
            }
        }
    }
 
    public static boolean checkAll() {
        for(int i=0; i<arrList.size(); i++) {
            int x = arrList.get(i).x;
            int y = arrList.get(i).y;
            
            if(visit[y][x] != true)
                return false;
        }
        return true;
    }
 
    public static int getDir(int dir, char ch) {
        int d=-1;
        if(ch == '|') {
            if(dir==0 || dir==2)
                d = dir;
        }else if(ch == '-') {
            if(dir==1 || dir==3)
                d = dir;
        }else if(ch =='+') {
            d = dir;
        }else if(ch == '1') {
            if(dir == 3) {
                d = 2;
            }else if(dir == 0) {
                d = 1;
            }
        }else if(ch == '2') {
            if(dir == 3) {
                d = 0;
            }else if(dir == 2) {
                d = 1;
            }
        }else if(ch == '3') {
            if(dir == 1) {
                d = 0;
            }else if(dir ==2) {
                d = 3;
            }
        }else if(ch == '4') {
            if(dir == 1) {
                d = 2;
            }else if(dir == 0) {
                d = 3;
            }
        }
        return d;
    }
    
    public static class Three{
        int x;
        int y;
        int dir;
        public Three(int x, int y, int dir) {
            this.x=x;
            this.y=y;
            this.dir=dir;
        }
    }
}