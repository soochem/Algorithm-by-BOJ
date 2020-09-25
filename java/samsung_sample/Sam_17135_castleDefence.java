package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


// ����
// 1. ���� ����� ��ã�� (���� ���� ����)
// 2. n�� �� m�� �� �� (�׷����� �÷��� ���Ī���� �־��)

//5 7 1
//1 0 1 0 1 1 1
//0 1 0 1 0 0 1 
//1 1 0 0 0 0 1
//0 0 0 1 1 1 1
//1 1 1 1 1 1 0

public class Sam_17135_castleDefence {
	static int n, m, d;
	static int max, enem;
	static int[][] map, visit;
	static int[] sor;  // �ü� ��ġ (j: m���� ����)
	static Eloc[] eloc;  // �� ��ġ
	
	static class Eloc implements Comparable<Eloc> {
		int i, j;
		Eloc (int i, int j){
			this.i = i;
			this.j = j;
		}
		
		@Override
		public int compareTo(Eloc e) {
			// TODO Auto-generated method stub
			if (this.j > e.j) return 1;  // ��������
			else if (this.j == e.j) {
				if (this.i < e.i) return 1; // ��������
			}
			return -1;
		}
	}
	
	static void dfs(int num, int depth) {
		if (depth == m) {  // �� num == 3??
			if (num != 3) return;  // �ü� 3 ����
			int ans = go();
			if (ans > max) max = ans;
			
//			for(int b:sor) System.out.print(b+" ");
//			System.out.println();
			
			return;
		}
		
		if (3-num <= m-depth)  // ��ǥġ  ������ (1 > 0 �̸� ������ �湮)
			dfs(num, depth+1);  // ���� x
		
		if (num >= 3) return;  // �갡 ����
		sor[num] = depth; // 0�� ����
		dfs(num+1, depth+1);
		sor[num] = 0;
	}
	
	static int go() {
		int ans = 0;
		int etmp = enem;
		
		int[][] dist = new int[3][enem];
		boolean[] dead = new boolean[enem];
		
		int[] copy = new int [enem];  // ��ü�� �����ص� ���� ���� ����
		for (int i=0; i<enem; i++) copy[i] = eloc[i].i;
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<enem; j++) {
				dist[i][j] = Math.abs(sor[i] - eloc[j].j) 
									+ Math.abs(n - eloc[j].i);  // n�� ��
			}
		}
		
		int[] dl = {1000, 1000, 1000};
		int time = 0;
		
		while (etmp > 0) {
			Arrays.fill(dl, 1000);
			// shooting
			for (int i=0; i<3; i++) {
				for (int di=0; di<=d; di++) {
					for (int j=0; j<enem; j++) {
						if (dead[j]) continue;
						if (dist[i][j]-time == di) {
							dl[i] = j;
							break;
						}
					}
					if (dl[i] != 1000) break;
				}
			}
			// ���� ��� ó�� & �ߺ�ó��
			for (int t : dl) {
				if (t == 1000) continue;
				if (dead[t]) continue;
				dead[t] = true;
				ans++;
				etmp--;
			}
			// moving
			for (int j=0; j<enem; j++) {
				if (dead[j]) continue;
				if (++copy[j] == n) {  // n�� ��........
					dead[j] = true;
					etmp--;
				}
			}
			time++;
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] s;
		
		s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		d = Integer.parseInt(s[2]);
		
		enem = 0;
		max = 0;
		
		map = new int[n][m];
		sor = new int[3];
		eloc = new Eloc[n*m];
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if (map[i][j] == 1) {
					eloc[enem] = new Eloc(i, j);
					enem++;
				}
			}
		}
		
		Arrays.sort(eloc, 0, enem);
		dfs(0, 0);
		
		bw.write(String.valueOf(max));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
