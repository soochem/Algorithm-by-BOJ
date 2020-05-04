package bipartite_matching;

import java.util.*;
import java.io.*;

public class Chuksa_2188 {
	public static int n, m;
	public static int[] cow, chuksa;
	public static int[] visit;
	public static ArrayList<ArrayList<Integer>> adj;
	
	static boolean dfs (int a) {
		visit[a] = 1;
		for (int b : adj.get(a)) {
			// �ݴ����� ��Ī���� �ʾҰų� (B�� �ʱ�ȭ ��)
			// ��Ī�Ǿ� ������ ���� ��Ī�Ǿ� �ִ� ����(B[b])�� �ٸ� ������ ��Ī(dfs==true)�� �� ������ ����
			if (chuksa[b] == 0 || visit[chuksa[b]] == 0 && dfs(chuksa[b])) {  // visit[B[b]] == 0
				cow[a] = b;
				chuksa[b] = a;
				return true;
			}
		}
		return false;  // ��Ī ����
	}
	static void print (int[] a) {
		for (int t: a) {
			System.out.print(t+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// �ʱ�ȭ
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cow = new int[n+1];
		chuksa = new int[m+1];
		visit = new int[n+1];
		adj = new ArrayList<>();
		for (int i=0; i<n+1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		
		// ��ǲ
		for (int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int j=0; j<size; j++) {
				adj.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// �̺� ��Ī
		int cnt = 0;
		for (int i=1; i<n+1; i++) {
			visit = new int[n+1];  // �ͱ�??
			if (dfs(i)) cnt++;
			//print(A); print(B);
		}
		System.out.println(cnt);
	}
	
}
