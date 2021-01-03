package MagicBook.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class BFS126_Word_Ladder_II {

	//BFS
    static void dfs_children(HashMap<String, ArrayList<String>> children, String start, String endWord,
                      List<String> cur, List<List<String>> ret) {
        if (start.equals(endWord)) {
            ret.add(new ArrayList(cur));
            return;
        }
        for(String n: children.get(start)) {
            cur.add(n);
            dfs_children(children, n, endWord, cur, ret);
            cur.remove(cur.size()-1);
        }
    }
    static List<List<String>> bfs(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ret = new ArrayList();
        HashSet<String> wordSet = new HashSet();
        HashMap<String, ArrayList<String>> children = new HashMap();
        for (String s:wordList) {
            wordSet.add(s);
            children.put(s, new ArrayList());
        }
        if (!wordSet.contains(endWord)) return ret;
        
        wordSet.remove(beginWord);
        children.put(beginWord, new ArrayList());
        HashSet<String> curEnding = new HashSet();
        curEnding.add(beginWord);
        boolean found = false;
        while (!curEnding.isEmpty() && !found) {
            HashSet<String> tmp = new HashSet();
            for (String e:curEnding) {
                char[] chArray = e.toCharArray();
                for (int i=0; i<e.length(); i++) {
                    char c = chArray[i];
                    for (int j=0; j<26; j++) {
                        char ac = (char)('a' + j);
                        if (ac == c) continue;
                        chArray[i] = ac;
                        String as = String.valueOf(chArray);
                        if (wordSet.contains(as)) {
                            tmp.add(as);
                            children.get(e).add(as);
                        }
                        if (as.equals(endWord)) {
                            found = true;
                            break;   //break earlier is OK for endWord checking, But not work when BiBFS, since that is checking on set
                        }
                    }
                    chArray[i] = c;
                }
            }
            for (String s:tmp) {
                wordSet.remove(s);
            }
            curEnding = tmp;
        }
        if (!found) return ret;
        List<String> cur = new ArrayList();
        cur.add(beginWord);
        dfs_children(children, beginWord, endWord, cur, ret);
        return ret;
    }
    //Bidirectional BFS
    static List<List<String>> biBfs(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ret = new ArrayList();
        HashSet<String> wordSet = new HashSet();
        HashMap<String, ArrayList<String>> children = new HashMap();
        for (String s:wordList) {
            wordSet.add(s);
            children.put(s, new ArrayList());
        }
        children.put(beginWord, new ArrayList());
        if (!wordSet.contains(endWord)) return ret;
        
        wordSet.remove(beginWord);
        wordSet.remove(endWord);
        
        HashSet<String> q1 = new HashSet();
        q1.add(beginWord);
        HashSet<String> q2 = new HashSet();
        q2.add(endWord);
        boolean found = false;
        boolean forward = true;
        while (!q1.isEmpty() && !q2.isEmpty() && !found) {
            HashSet<String> tmp = new HashSet();
            for (String e:q1) {
                char[] chArray = e.toCharArray();
                for (int i=0; i<e.length(); i++) {
                    char c = chArray[i];
                    for (int j=0; j<26; j++) {
                        char ac = (char)('a' + j);
                        if (ac == c) continue;
                        chArray[i] = ac;
                        String as = String.valueOf(chArray);
                        if (q2.contains(as) || wordSet.contains(as)) {
                            tmp.add(as);
                            if (forward) {
                                children.get(e).add(as);
                            } else {
                                children.get(as).add(e);
                            } 
                        }
                        if (q2.contains(as)) {
                            found = true;
                        }
                    }
                    chArray[i] = c;
                }
            }
            for (String s:tmp) {
                wordSet.remove(s);
            }
            q1 = q2;
            q2 = tmp;
            forward =!forward;
        }
        if (!found) return ret;
        List<String> cur = new ArrayList();
        cur.add(beginWord);
        dfs_children(children, beginWord, endWord, cur, ret);
        return ret;    
    }
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //return bfs(beginWord, endWord, wordList);
    	return biBfs(beginWord, endWord, wordList);
    }
	public static void main(String[] args) {
		String[] words = {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
		List<List<String>> ret = findLadders("qa", "sq", Arrays.asList(words));
        System.out.printf("ret=%s\n", ret.toString());
        /*
         * [["qa","ba","be","se","sq"],["qa","ba","bi","si","sq"],["qa","ba","br","sr","sq"],["qa","ca","cm","sm","sq"],["qa","ca","co","so","sq"],["qa","la","ln","sn","sq"],["qa","la","lt","st","sq"],["qa","ma","mb","sb","sq"],["qa","pa","ph","sh","sq"],["qa","ta","tc","sc","sq"],
         * ["qa","fa","fe","se","sq"],["qa","ga","ge","se","sq"],["qa","ha","he","se","sq"],["qa","la","le","se","sq"],["qa","ma","me","se","sq"],["qa","na","ne","se","sq"],["qa","ra","re","se","sq"],["qa","ya","ye","se","sq"],["qa","ca","ci","si","sq"],["qa","ha","hi","si","sq"],
         * ["qa","la","li","si","sq"],["qa","ma","mi","si","sq"],["qa","na","ni","si","sq"],["qa","pa","pi","si","sq"],["qa","ta","ti","si","sq"],["qa","ca","cr","sr","sq"],["qa","fa","fr","sr","sq"],["qa","la","lr","sr","sq"],["qa","ma","mr","sr","sq"],["qa","fa","fm","sm","sq"],
         * ["qa","pa","pm","sm","sq"],["qa","ta","tm","sm","sq"],["qa","ga","go","so","sq"],["qa","ha","ho","so","sq"],["qa","la","lo","so","sq"],["qa","ma","mo","so","sq"],["qa","na","no","so","sq"],...
         */
	}

}
