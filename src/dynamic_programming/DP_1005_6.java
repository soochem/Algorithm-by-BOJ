package dynamic_programming;

import java.io.*;
import java.util.*;

public class DP_1005_6 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test, n, k, w;
		int[] memo, time, inedge;
		
		test = Integer.parseInt(br.readLine());
		
		for (int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			memo = new int [n+1];  // DP ���
			time = new int [n+1];  // �Ǽ� �ð�
			inedge = new int [n+1];  // ���԰���
			List<Integer>[] adj = new ArrayList[n+1];
			for (int i=1; i<=n; i++) {
				adj[i] = new ArrayList<>();
			}
			// �ǹ� ���� �� �ɸ��� �ð� D
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=n; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			// �Ǽ� ��Ģ k��
			for (int i=1; i<=k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				adj[x].add(y);
				inedge[y]++;
			}
			
			w = Integer.parseInt(br.readLine()); // ��ǥ
			
			// ��������
			Queue<Integer> q = new LinkedList<Integer>();
			for (int i=1; i<=n; i++) {
				if (inedge[i] == 0) {
					//System.out.println(i);
					memo[i] = time[i];
					q.offer(i);
				}
			}
			while (!q.isEmpty()) {
				int cur = q.poll();
				for (int item : adj[cur]) {
					//System.out.println(item);
					inedge[item]--;
					if (inedge[item] == 0) {
						//System.out.println("dd "+item);					
						q.offer(item);
					}
					if (memo[item] < memo[cur]+time[item]) {
						memo[item] = memo[cur]+time[item];
					}
				}
			}
			
			System.out.println(memo[w]);
		}
	}
}
