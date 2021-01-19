package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ6539상범빌딩 {
	static int L, R, C;
	static char map[][][];
	static int cnt;
	static int startX, startY, startZ;
	static int [][] dirs= {{1,0,0},{0,1,0},{-1,0,0},{0,-1,0},{0,0,1},{0,0,-1}};   //방향 배열
	static int visited[][][];
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if (L == 0 && R == 0 && C == 0)
				break;
			cnt = 0;
			map = new char[L][R][C];
			visited = new int[L][R][C];
			flag = false;
			for (int i = 0; i <L; i++) {
				for (int j = 0; j < R; j++) {
					String str = br.readLine();
					for (int j2 = 0; j2 < C; j2++) {
						map[i][j][j2] =str.charAt(j2);
						if(map[i][j][j2]=='S') {          //처음에 출발할 장소를 넣어줌
							startX = i;
							startY = j;
							startZ = j2;
						}
					}
				}
				br.readLine();			//마지막 띄어쓰기
			}
			BFS();
			if(flag) {
				System.out.println("Escaped in "+cnt+" minute(s).");
			}
			else {
				System.out.println("Trapped!");
			}
		}
	}
	
	private static void BFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(startX,startY,startZ,0));
		visited[startX][startY][startZ]=0;
		while(!q.isEmpty()) {
			Node temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int z = temp.z;
			int d = temp.d;
			if(map[x][y][z]=='E') {
				flag= true;  //함정인지 아닌지 구별하기 위해서 
				cnt = d;     //답이 맞다면 그 높이가 정답이 됨
				return;
			}
			for (int i = 0; i < dirs.length; i++) {
				int dx = x+dirs[i][0];
				int dy = y+dirs[i][1];
				int dz = z+dirs[i][2];
				int dd = d+1;
				if(isOK(dx,dy,dz)) {
					if(visited[dx][dy][dz]==0 && map[dx][dy][dz]!='#') {
						q.add(new Node(dx,dy,dz,dd));
						visited[dx][dy][dz]=visited[x][y][z]+1;
					}
				}
			}
		}
		return;  	//while문 돌 동안 끝난다면 그냥 빠져나가기 (끝내기)
	}

	private static boolean isOK(int dx, int dy, int dz) {
		return dx>=0&&dx<L&&dy>=0&&dy<R&&dz>=0&&dz<C;
	}

	static class Node{
		int x;
		int y;
		int z;
		int d;
		public Node(int x, int y, int z, int d) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.d = d;
		}
	}
}
