package samsung_sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 17142 ������3
// �ð��� ���� �ø��ų� (�̹� �� �������� �߰� x)
// ���̷��� ��� ���� 0/1/2�� ���� ��� ó���� ���ΰ�
// 0������ ���� ���ų� (�ȼ��°� ���� ����)

public class Sam_17142_Lab3 {
	static final int MAX = 10000;
	static int N, M, min, tot;
	static boolean[][] vis;
	static Queue<Integer> qx, qy;
	static int[][] dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};
	
	static void comb(boolean[] visit, int x, int depth, 
			int vnum, int[][] varr, int[][] map) {
		if (x == M) {
			int time = go(visit, vnum, varr, map);
			min = (time < min)? time : min;
			return;
		}
		if (depth == vnum) return;
		visit[depth] = true;
		comb(visit, x+1, depth+1, vnum, varr, map);
		visit[depth] = false;
		comb(visit, x, depth+1, vnum, varr, map);
	}
	
	static int go(boolean[] visit, int vnum, int[][] varr, int[][] map) {
		int time = 0;
		int i, j;
		int tmp = tot;
		
		// ��� ��ĭ�� �۶߸���
		for (boolean[] v : vis) Arrays.fill(v, false);
		qx.clear(); qy.clear();

		int nx, ny, cx, cy, size;
		for (i = 0; i < vnum; i++) {
			if (!visit[i]) continue;
//			System.out.print(varr[i][0]+" "+varr[i][1]+" ");
			cx = varr[i][0]; cy = varr[i][1];
			qx.add(cx); qy.add(cy);
			vis[cx][cy] = true;
		}
//		System.out.println();
		
		while (!qx.isEmpty()) {
//			if (tmp == 0) break;  // �̹� 0�� �� ó���ϰ� 2�� ���� ���
			size = qx.size();
			for (i = 0; i < size; i++) {
				cx = qx.poll(); cy = qy.poll();
				if (map[cx][cy] == 0) tmp--;
				
				for (j = 0; j < 4; j++) {
					nx = cx + dir[j][0];
					ny = cy + dir[j][1];
					if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
						// ���̰ų� ���̷����̸� �߰��ϸ� x
						// �湮�� �� ���� �� ĭ�϶�
						// ���̷����� �� x, Ȱ��ȭ�� �� �ִ�.
						if (!vis[nx][ny] && (map[nx][ny] == 0
								|| map[nx][ny] == 2)) {
							vis[nx][ny] = true;
							qx.add(nx); qy.add(ny);
						}
					}
				}
			}
			if (tmp != 0) time++; // ó���� �ʱⰪ��
			else break;
		}
		return time = (tmp == 0)? time: MAX;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int i, j, tmp, vnum;
		String[] input;
		
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		int[][] map = new int[N][N];
		int[][] virus = new int[10][2];
		vis = new boolean[N][N];
		qx = new LinkedList<>();
		qy = new LinkedList<>();
		vnum = 0;
		min = MAX;
		
		for (i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (j = 0; j < N; j++) {
				tmp = Integer.parseInt(input[j]);
				map[i][j] = tmp;
				if (tmp == 2) {
					virus[vnum][0] = i;
					virus[vnum++][1] = j;
				}
				if (tmp == 0) tot++;
			}
		}
		
		comb(new boolean[10], 0, 0, vnum, virus, map);
//		System.out.println(min = (min == MAX)? -1: min);
		sb.append(min = (min == MAX)? -1: min);
		sb.append("\n");
		System.out.println(sb.toString());
	}
}
