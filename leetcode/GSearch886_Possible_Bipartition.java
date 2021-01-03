package MagicBook.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class GSearch886_Possible_Bipartition {
	public static boolean possibleBipartition(int N, int[][] dislikes) {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap();
        for(int[] item : dislikes) {
            //Remember this way:computeIfAbsent with add
            adj.computeIfAbsent(item[0], k -> new ArrayList<Integer>()).add(item[1]); 
            adj.computeIfAbsent(item[1], k -> new ArrayList<Integer>()).add(item[0]); 
        }
        int[] colors = new int[N+1];
        for (int i=1; i<N+1; i++) {
            if (colors[i] != 0) continue;
            //if (!dfs(adj, i, 1, colors)) return false;
            if (!bfs(adj, i, 1, colors)) return false;
        }
        return true;
    }
    static boolean bfs(HashMap<Integer, ArrayList<Integer>> adj, int start, int color, int[] colors) {
        ArrayDeque<Integer> q = new ArrayDeque();
        colors[start] = color;
        q.add(start);
        while (!q.isEmpty()) {
            int curr = q.removeFirst();
            ArrayList<Integer> nlist = adj.get(curr);
            if (nlist == null) continue;
            for (Integer next: nlist) {
                if (colors[next] == 0) {
                    colors[next] = -colors[curr];
                    q.add(next);
                } else if (colors[next] == colors[curr]) {
                    return false;
                }
            }
        }
        return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] dislikes = {{1,2},{1,3},{2,3}};
		System.out.printf("ret=%b\n", possibleBipartition(3, dislikes));
	}

}
