package string;

import java.io.*;
import java.util.ArrayList;

// trie �ڷᱸ��

class Node {
	//public int val;
	public boolean end;
	public Node[] child;
	Node() {
		//this.val = next;
		this.end = false;
		this.child = new Node[10];
	}
}

class Trie {
	Node root = new Node();
	Trie() {
		this.root.child = new Node[10];
	}
	
	boolean search(String s) {
		int i, j;
		j = 0;
		Node now = this.root;
		boolean flag = true;
		
		for (i = 0; i < s.length(); i++) {
			j = s.charAt(i) - '0';
			
			if (now.end) return true;  // Ʈ���� �� & ���ڿ� ��
			if (now.child[j] == null) {
				// Ʈ���� ��, ���� ����
//				System.out.println("���� "+now+" "+j);
				flag = false;
				insert(now, j);
			}
			now = now.child[j];
		}
		now.end = true;  // ������ �ſ� ������ ǥ��
//		if (now != null) return true;  // ���ڿ� �����µ� Ʈ���̴� �� x
		return flag;
	}
	
	void insert(Node n, int next) {
		n.child[next] = new Node();
	}
}

public class BOJ_5052_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String s;
		int tc, i, j, n;
		Trie t;
        boolean flag;
		
		tc = Integer.parseInt(br.readLine());
		
		for (i = 0; i < tc; i++) {
			t = new Trie();
			n = Integer.parseInt(br.readLine());
			flag = false;
			
			for (j = 0; j < n; j++) {
				s = br.readLine();
				if (t.search(s)) {
					flag = true;
				}
			}
			
			sb.append((flag)? "NO" : "YES");
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
