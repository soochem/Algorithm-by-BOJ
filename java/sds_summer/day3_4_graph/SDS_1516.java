// Ʋ�� �κ�
// if (d[ne] < d[a] + c[ne]) d[ne] = d[a] + c[ne];  // �� ū�ɷ� ����
// �̰� indegree 0�϶��� �߰��ϸ� �ȵ���..			

package sds_summer.day3_4_graph;

import java.io.*;
import java.util.*;

public class SDS_1516 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int n, m, a, b, prev; a=b=0;
		int[] indeg;
		//int ans;
		int max; // ���� �ִ밪?
//		ArrayList<Integer> ans;
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
		int[] d, c;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
//		m = Integer.parseInt(st.nextToken()); 
		
		indeg = new int[n+1];
		d = new int[n+1];
		c = new int[n+1];
		//ans = new ArrayList<Integer>();
		for (int i=0; i<n+1; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c[i] = a;
			indeg[i] = b;
			for (int j=0; j<b; j++) {
				prev = Integer.parseInt(st.nextToken());
				adjList.get(prev).add(i);
			}
			//adjList.get(a).add(b);
			//indeg[b]++;
		}
		
		for (int i=1; i<n+1; i++) {
			if (indeg[i] == 0) {
				q.add(i);
				d[i] = c[i];
			}
		}
		
		boolean[] visited = new boolean[n+1];
		
		while (!q.isEmpty()) {
			a = q.poll();
			//if (!visited[a])
			//visited[a]
			for (int ne:adjList.get(a)) {
				if (d[ne] < d[a] + c[ne]) d[ne] = d[a] + c[ne];  // �� ū�ɷ� ����
				if (--indeg[ne] == 0) {
					q.add(ne);
				}
			}
		}
		
		//ans = 0;
		max = -1;
		for (int i:d) if (i > max) max = i;
		
		System.out.println(max);
		
		bw.flush(); bw.close();
	}
}

