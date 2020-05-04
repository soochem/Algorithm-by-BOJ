// ������
// Ʋ�� ��

package sds_day7_8_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SDS_1102_plant2 {
	static int n, p;
	static int MAX = (int) 1e9;
	static int[][] cost;
	static int[] d;
	
	static int run (int cnt, int from, int state) {
		if (cnt == p) return 0;  // ��������, �߰� ����� 0�̴�.
		
		if (d[state] != MAX) return d[state];
		
		// from�� ��ǲ�� �ƴ϶� ��� 1�� ������ ����غ�
//		for (int from=0; from<n; from++) {
//			if ((1<<from & state) == 1) {
				for (int i=0; i<n; i++) {
					if ((1<<i & state) == 0) {  // i���� ����
						System.out.println(from+" "+i);
						System.out.println(cost[from][i]);
						d[state] = Math.min(d[state], cost[from][i]+run(cnt+1, i, (1<<i)|state));
					}
				}
//			}
//		}
		
		return d[state];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringTokenizer st;
		String[] s;
		
		n = Integer.parseInt(br.readLine());
		
		cost = new int[n][n];  // �־��� ���
		d = new int[1 << n];
		Arrays.fill(d, MAX);
		// ������ ���¿� ���� �߰� ���
		// �� : 100 �� ���� �߰���� (0 -> +??)
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<n; j++) {
				cost[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		int start = -1;
		int state = 0;
		int cnt = 0;
		String ss = br.readLine();
		for (int j=0; j<n; j++) {
			if (ss.charAt(j) == 'Y') {
				if (start == -1) start = j;
				state |= 1<<j;
				cnt++;
			}			
		}
		
		p = Integer.parseInt(br.readLine());  // ��ǥ p, �ּ� ����� ��ǥ�ϱ� p���� ������Ű�� ��
		
//		d[state] = 0;  // ��Ʈ��ŷ �ÿ� �ʿ����
		run(cnt, start, state);
		
		for (int dd: d) System.out.print(dd+" ");
		System.out.println();
		
		bw.flush();
		bw.close();
	}
}
