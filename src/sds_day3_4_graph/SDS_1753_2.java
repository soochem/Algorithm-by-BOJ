// ������
// d[now.v] != Integer.MAX_VALUE && ������ �Է¾��ϸ�
// ������ �׷������� inf �Ѵ� �� ����

// �������� start(�Է°�), adjArr ũ�� ����
//4 3
//4
//1 2 2
//4 2 3
//4 1 1

package sds_day3_4_graph;

import java.io.*;
import java.util.*;

public class SDS_1753_2 {
	public static int[] d;
	public static boolean[] visited;
	
	public static class Edge {
		int u, v, c;
		Edge(int v, int c){
			this.v = v;
			this.c = c;
		}
		Edge(int u, int v, int c){
			this.u = u;
			this.v = v;
			this.c = c;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int n, m;
		int start;
		int u, v, c; u=v=c=0;
		// �Է�
		ArrayList<ArrayList<Edge>> adjArr = new ArrayList<>();
		ArrayList<Edge> edgeArr = new ArrayList<>();
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new Comparator<Edge>() {
			public int compare (Edge e1, Edge e2) {
				return (e1.c >= e2.c)? 1:-1;
			}
		});

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		
		d = new int[n+1];
		visited = new boolean[n+1];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[start] = 0;
		
		for (int i=0; i<=n+1; i++) {
			adjArr.add(new ArrayList<Edge>());
		}
		for (int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			adjArr.get(u).add(new Edge(v, c));
		}

		// ���ͽ�Ʈ��
		Edge now, next;
		pq.add(new Edge(start, 0));  // �Է��� start!!!
		
		while (!pq.isEmpty()) {
			now = pq.poll();
			if (!visited[now.v]) {
				visited[now.v] = true;
				for (int i=0; i<adjArr.get(now.v).size(); i++) {
					next = adjArr.get(now.v).get(i);
					if (d[now.v] != Integer.MAX_VALUE && d[next.v] > d[now.v] + next.c) {  // ���Ѵ� �ڽ�Ʈ�� ������ ŭ
						d[next.v] = d[now.v] + next.c;
						pq.add(new Edge(next.v, d[next.v]));
					}
				}
			}
		}
		
		// ���
		for (int i=1; i<=n; i++) {
			if (d[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(d[i]);  // ���پ� ����� �Ǵ°ǰ�? ����ʰ� (BW)
		}
	}
}
