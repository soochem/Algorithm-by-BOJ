package sds_day3_4_graph;

// 3176 ���� ��Ʈ��ũ

import java.io.*;
import java.util.*;

public class SDS_3176_roadNetwork {
	static int n, m, max_d;
	static int[] depth;
	static int[][] dp;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> adjArr;
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
//		int u, v; u=v=0;
//		
//		adjArr = new ArrayList<>();
//		n = Integer.parseInt(br.readLine());
//		for (int i=0; i<=n+1; i++) {
//			adjArr.add(new ArrayList<Integer>());
//		}
//
//		max_d = (int) Math.floor(log2(n));  // 0����~2^k�� -1�ϸ� �ȵ�~
//		depth = new int[n+1]; // ���̴� 0���� max_d����
//		dp = new int[n+1][max_d+1];  // i�� ����� 2^j��° ����
//		
//		for (int i=0; i<n-1; i++) {
//			st = new StringTokenizer(br.readLine());
//			u = Integer.parseInt(st.nextToken());
//			v = Integer.parseInt(st.nextToken());
//			adjArr.get(u).add(v);
//			adjArr.get(v).add(u);
//		}
		
		bw.flush();
		bw.close();
	}
}
