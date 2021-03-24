package M0320;

import java.util.Arrays;

public class 라인3 {
	public static void main(String[] args) {
		int[] enter = { 1,4,2,3 };
		int[] leave = { 2,1,4,3 };

		System.out.println(Arrays.toString(solution(enter, leave)));
	}

	public static int[] solution(int[] enter, int[] leave) {
		int[] answer = {};
		answer = new int[enter.length];

		for (int i = 0; i < enter.length; i++) {
			int f = enter[i];	

			for (int j = 0; j < leave.length; j++) {
				if (leave[j] == f) {
					if (j == 0) { // 가장 먼저 퇴실한다면
						answer[f - 1] = i;
					} else { // 가장 먼저 퇴실하지 않는다면
						if (i == enter.length - 1) {
							if (j == leave.length) {
								answer[f - 1] = 0;
							} else {
								answer[f - 1] = leave.length - j - 1;
							}
						}

						else {
							if (j == leave.length - 1) {
								if (i != enter.length - 1) {
									if (i == 0) {
										answer[f - 1] = answer.length - 1;
									} else {
										answer[f - 1] = answer.length - 1;
										for (int k = 0; k < i; k++) {
											for (int k2 = 0; k2 < j; k2++) {
												if (enter[k] == leave[k2] && k == k2) {
													answer[f - 1]--;
												}
											}
										}
									}
								}
							} else {
								int c = 0;
								for (int k = i + 1; k < enter.length; k++) { // 내 뒤에 입실한 사람들 중에서
									for (int k2 = 0; k2 < j; k2++) {
										if (enter[k] == leave[k2]) {
											c = k;
										}
									}
								}
								answer[f - 1] = c - i;
								System.out.println(answer[f-1]+" "+f);
								int d = 0;
								for (int k = 0; k < i; k++) {
									for (int k2 = 0; k2 < j; k2++) {
										if (enter[k] == leave[k2]) {
											d=k+1;
										}
									}
								}

								answer[f-1] += d;
							}
						}
					}
				}
			}
		}
		return answer;
	}
}
