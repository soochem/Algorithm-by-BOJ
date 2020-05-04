// ������

package sds_day7_8_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SDS_1102_plant {
	static int n, p;
	static int MAX = (int) 1e9;
	static int[][] cost;
	static int[] d;
	
	static int run (int cnt, int state) {
		if (cnt == p) return 0;  // ��������, �߰� ����� 0�̴�.
		
		if (d[state] != MAX) {  // �̹� �ִ°� ������ �ּ��ΰ�??
			System.out.println(state+" �̹� �־�");
			return d[state];
		}
		
		// from�� ��ǲ�� �ƴ϶� ��� 1�� ������ ����غ�
		for (int from=0; from<n; from++) {
			if ((1<<from & state) != 0) {  // 1�� �ƴ϶� 0 �̾ƴ�����
				for (int i=0; i<n; i++) {
					if ((1<<i & state) == 0) {  // i���� ����
//						System.out.println(from+" "+i);
//						System.out.println(cost[from][i]);
						d[state] = Math.min(d[state], cost[from][i]+run(cnt+1, (1<<i)|state));
					}
				}
			}
		}
		
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
		
//		int start = -1;
		int state = 0;
		int cnt = 0;
		String ss = br.readLine();
		for (int j=0; j<n; j++) {
			if (ss.charAt(j) == 'Y') {
				state |= 1<<j;
				cnt++;
			}			
		}
		
		p = Integer.parseInt(br.readLine());  // ��ǥ p, �ּ� ����� ��ǥ�ϱ� p���� ������Ű�� ��
		
//		d[state] = 0;  // ��Ʈ��ŷ �ÿ� �ʿ����
		
		if (p == 0) {
			bw.write(String.valueOf(0));
		}
		else if (state == 0) {
			bw.write(String.valueOf(-1));
		}
		else if (cnt >= p) {
			bw.write(String.valueOf(0));
		}
		else {
			run(cnt, state);
			if (d[state] == MAX) bw.write(String.valueOf(-1));
			else bw.write(String.valueOf(d[state]));
		}
		
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
