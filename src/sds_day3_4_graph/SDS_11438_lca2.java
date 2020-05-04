package sds_day3_4_graph;

import java.io.*;
import java.util.*;

public class SDS_11438_lca2 {
	static final int MAX = 20;  // �ִ� ���� �뷫 ��� 1E6����
	static int n, m, max_d;
	static int[] par, depth;
	static int[][] dp;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> adjArr;
	
	private static void dfs(int cur, int cnt) {
		depth[cur] = cnt;
		visited[cur] = true;
		
		// dp ä���
		for (int i=1; i<=max_d; i++) {
			dp[cur][i] = dp[dp[cur][i-1]][i-1];
		}
		for (int next: adjArr.get(cur)){
			if (!visited[next]) {
				dp[next][0] = cur;
				dfs(next, cnt+1);
			}
		}
		
		return;
	}
	
	private static double log2(int n) {
		return Math.log10(n)/Math.log10(2);
	}
	
	private static int lca(int u, int v) {
		int i = 0;
		if (depth[u] < depth[v]) {  // u�� depth�� �� ����
			int tmp = v;
			v = u;
			u = tmp;
		}
		for (i=max_d; i>=0; i--) {
			if (depth[v] <= depth[dp[u][i]])
				u = dp[u][i]; // u�� ��� u�� 2^i��° �������� ������Ʈ
		}
		if (u == v) {
			return u;
		}
		for (i=max_d; i>=0; i--) {  // ���� �����ε� ������ �ٸ�? -> ���� ���� �ø�
			if (dp[u][i] != dp[v][i]) {
				u = dp[u][i]; v = dp[v][i];
			}
		}
		return dp[u][0];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int u, v; u=v=0;
		
		adjArr = new ArrayList<>();
		n = Integer.parseInt(br.readLine());
		for (int i=0; i<=n+1; i++) {
			adjArr.add(new ArrayList<Integer>());
		}

		max_d = (int) Math.floor(log2(n))-1;
		depth = new int[n+1];
		dp = new int[n+1][MAX];  // i�� ����� 2^j��° ����
		
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			
			adjArr.get(u).add(v);
			adjArr.get(v).add(u);
		}
		
		// dfs - Ʈ�� �����ϱ�, ��Ʈ�� 1
		visited = new boolean[n+1];
		dfs(1, 0);

		for (int i=0; i<=n; i++) {
			for (int j=0; j<=n; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		
		// lca - �� ������ ���� ����?
		m = Integer.parseInt(br.readLine());
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf(lca(u, v)));
			bw.write("\n");
		}
		
		bw.flush(); bw.close();
	}
}
