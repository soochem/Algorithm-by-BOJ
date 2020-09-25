package samsung_sample;

import java.io.*;

public class Sam_17281_baseball {
	static int n, max;
	static int[][] map;
	static int[] order;
	static boolean[] visited;
	
	static void perm (int depth) {
		// �̴׿� ������� ������
		// order : index ������ ������ �迭
		if (depth == 4) depth++;
		if (depth == 10) {
			// ���� �� �湮
			go();
			return;
		}
		
		for (int i = 2; i < 10; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[depth] = i;
				perm(depth+1);
				visited[i] = false;
			}
		}
		
	}

	static void go () {
		// state : 111, 110, ... (bit state, 1-3 ��)
		int out = 0;
		int idx = 1;
		int cmd = -1;
		int ans = 0;
		int state = 0;
		
		for (int num=1; num<=n; num++) {
			while (true) {
				if (idx >= 10) // �ƿ� 3�̸�, �̴׵� �� x
					idx = 1;
				if (out == 3) {
					// �̴� ++, Ÿ�ڴ� ���� �ϰ� �ִ�
					out = 0;
					state = 0;
//					num++;
					break;
				}
				
				cmd = map[num][order[idx]];  // order�� ��� ������ ��
				
				if (cmd == 0) {
					out++;
				}
				else if (cmd == 4) {
					for (int i=1; i<=4; i=i<<1) {
						if ((state & i) != 0) ans++;
					}
					ans++;
					state = 0;
				}
				else {  // 1~3
					for (int i=1<<(3-cmd); i<=4; i=i<<1) {  // 100 10 1
						if ((state & i) != 0) ans++;
					}
					state = (state << cmd) % 8 + (1 << (cmd-1)); // ���� ���ƾ���
				}
				idx++;
			}
		}
		
		// �̴׵� �� ���Ҵ�, ���������� �����ؾ� ��
		if (max < ans) max = ans;
		return;
	}
	
	// �Լ� ��, dfs
//	static void dfs (int ans, int out, int state, int depth, int num) {
//		// state : 111, 110, ... (bit state, 1-3 ��)
//		if (num > n) {
//			// �̴׵� �� ���Ҵ�, ���������� �����ؾ� ��
//			if (max < ans) max = ans;
//			return;
//		}
//		if (depth >= 10) // �ƿ� 3�̸�, �̴׵� �� x
//			depth = 1;
//		if (out == 3) {
//			// �̴� ++, Ÿ�ڴ� ���� �ϰ� �ִ�
//			dfs (ans, 0, 0, depth, num+1);
//			return;
//		}
//		
//		int cmd = map[num][order[depth]];  // order�� ��� ������ ��
//		
//		if (cmd == 0) {
//			dfs(ans, out+1, state, depth+1, num);
//			// ������ dfs ���� x
//			return;
//		}
//		else if (cmd == 4) {
//			for (int i=1; i<=4; i=i<<1) {
//				if ((state & i) != 0) ans++;
//			}
//			ans++;
//			state = 0;
//		}
//		else {  // 1~3
//			for (int i=1<<(3-cmd); i<=4; i=i<<1) {  // 100 10 1
//				if ((state & i) != 0) ans++;
//			}
////			state = (state << cmd) % 8 + 1;
//			state = (state << cmd) % 8 + (1 << (cmd-1)); // ���� ���ƾ���
//		}
//		dfs(ans, out, state, depth+1, num);
//		return;
//	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] s;
		
		n = Integer.parseInt(br.readLine()); // �̴�
		
		map = new int[n+1][10];
		order = new int[10];
		visited = new boolean[10];
		for (int j = 1; j <= n; j++) {
			s = br.readLine().split(" ");
			for (int i = 0; i < 9; i++) {
				map[j][i+1] = Integer.parseInt(s[i]);
			}
		}
		
		order[4] = 1;
		perm(1);
		
		bw.write(String.valueOf(max));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
