import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 캐슬디펜스 {
	static int N,M,D;//D:유효거리
	static int ans = 0;
	
	static class Enemy{
		int r,c;
		Enemy(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();//행
		M = sc.nextInt();//열
		D = sc.nextInt();//유효거리
		
		//입력받은 적군의 위치를 객체리스트로 저장
		ArrayList<Enemy> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(sc.nextInt() == 1) {
					list.add(new Enemy(i, j));
				}
			}
		}
		
		//궁수조합:가능한 모든 궁수 조합에서 계산해 봄.
		for(int i = 0; i < M; i++) {			
			for(int j = i+1; j < M; j++) {				
				for(int k = j+1; k < M; k++) {	
					
					//새로운 궁수  조합시 마다 원본 사용해야 하므로 복사해서 사용함
					ArrayList<Enemy> list2 = new ArrayList<>();					
					for(Enemy e :list) {
						list2.add(new Enemy(e.r, e.c));
					}
					
					//게임 시작. killed: 궁수 조합당 한회에 죽인 적군수
					//{i,j,k}: 3명의 궁수 위치 조합
					int killed = shot(list2, new int[] {i,j,k});					
					ans = Math.max(killed, ans);
				}
			}
		}
		System.out.println(ans);
	}
	
	//archers:궁수 위치 배열	
	//매 궁수위치 조합마다 반복 실행: 궁수 3명이 모두 쏜 후 죽은 적군의 숫자 리턴
	static int shot(List<Enemy> list, int[] archers) {
		int cnt = 0;
		
		//3명의 궁수가 정해진 자리에서 적군이 모두 사라질때까지 반복해서 쏨
		while(list.size() != 0) {
			
			//죽일놈 리스트(궁수 3명이 모두 쏜 후 죽일 놈들 여기에 저장함)
			List<Enemy> tmp = new ArrayList<>();
			
			//궁수 한사람씩 꺼내서 그 궁수에서 제일 가까운 적 위치를 찾아 죽일놈 리스트에 추가
			for(int position : archers) {
				//target: 궁수로부터 젤 가까운 적군의 위치를 찾아
				int target = findNear(list, position);
								
				if(target != -1) {//있으면 원리스트에서 알아내서 죽일놈 리스트에 추가					
					tmp.add(list.get(target));
				}
			}//3명의 궁수가 모두 죽일 놈들 선정 완료!(궁수 3명이 모두 쏨)
			
			//죽일놈들을 리스트에서 삭제
			for(Enemy e : tmp) {
				if(list.remove(e))
					cnt++;
			}	
			
			//적군 하강 -> 적군리스트 조정됨(성벽 만나면 삭제)
			EnemyDown(list);
		}
		
		return cnt;
	}
	
	//p:궁수 row 위치
	//리스트에서 적을 하나씩 꺼내서 궁수와의 거리 계산
	//유효한 범위의 적 들 중
	static int findNear(List<Enemy> list, int position) {
		int distance = Integer.MAX_VALUE;//제일 가까운 적과의 거리 (초기값)
		int col = 50;//제일 가까운 적의 컬럼값.같은 거리의 적이라면 왼편의 적을 선택해야 하므로 적군의 열값 알고 있어야 함
		int pos = -1;//쏘기로 결정한 적의 (리스트 안의) 인덱스 
		
		for(int i = 0; i < list.size(); i++) {//모든 적군 다 조사
			Enemy e = list.get(i);
			
			//거리계산: 궁수는 N행 p열에 있음
			int d = N - e.r + Math.abs(position - e.c);
			
			//거리 초과되는 적군은 무시
			if(d > D)
				continue;
			
			//알고있던 거리(dist)보다 더 가까운 거리(d)의 적이 나타나면
			if(d < distance) {
				//적군과의 거리와 그 적군의 가로좌표를 저장
				distance = d;//계산한 거리값을 distance에 저장
				col = e.c;//지금 적의 컬럼값을 최소 가로 값으로 저장 
				pos = i;//리스트상에서의 적군의 위치
			}
			
			//알고있는 가까운 적과 같은 거리를 갖는 적군이 발견되면 가로 좌표값 비교
			else if(d == distance) {//거리가 같으면
				if(col > e.c) {//가로좌표 비교
					//더 작은 가로 좌표값을 기억
					col = e.c;
					pos = i;
				}
			}
		}
		
		return pos;
	}
	
	static void EnemyDown(List<Enemy> list) {
		for(int i = 0; i < list.size(); i++) {
			Enemy e = list.get(i);
			e.r++;
			
			if(e.r == N) {
				list.remove(i);
				i--;//뒤에 있던 애가 앞으로 오니까
			}
		}
	}
}

