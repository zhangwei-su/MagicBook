package MagicBook.leetcode;
/*
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, 
 * and there exists one unique longest palindromic substring.
 */
/*Manacher's Algorithm*/
public class L005_Longest_Palindromic_Substring {
    static String convert(String S) {
    	StringBuilder ret = new StringBuilder();
    	ret.append('$');
    	for(char c:S.toCharArray()) {
    		ret = ret.append('#').append(c);
    	}
    	ret.append('#');
    	return ret.toString();
    }
	static String longestPS(String S) {
		String opS = convert(S);
		int max = 0;
		int po = 0;
		int len = opS.length();
		int P[] = new int[len];
		int start=0,end=0, longest=0;
		for (int i = 1; i < len; i++) {
			P[i] = (max > i) ? Math.min(P[2*po - i], max-i) : 1;
			po = i;
			for (int edgeIdx = i+P[i];edgeIdx < len; edgeIdx++) {
				System.out.printf("i=%d,edgeIdx=%d, p[i]=%d, max=%d, po=%d\n" , i, edgeIdx, P[i], max, po);
				int mirrorIdx = 2*po - edgeIdx;
				if (mirrorIdx >= 0 && opS.charAt(edgeIdx) == opS.charAt(mirrorIdx)) {
					max = edgeIdx;
					P[i] = edgeIdx - po;
					if (P[i] > longest) {
						start = mirrorIdx/2;
						end = start + P[i];
						longest = P[i];
					}
				} else {
					break;
				}
			}
		}
		return S.substring(start, end);
	}
	static String longestPS_fromDoc(String S) {
		String t = "$#";
	    for (int i = 0; i < S.length(); ++i) {
	        t += S.charAt(i);
	        t += "#";
	    }
	    System.out.printf("t=%s\n" , t);
		int p[] = new int[t.length()];
		int mx = 0, id = 0, resLen = 0, resCenter = 0;
	    for (int i = 1; i < t.length(); ++i) {
	        p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
	        System.out.printf("i=%d,p[i]=%d, mx=%d, id=%d\n" , i, p[i], mx, id);
	        while (i + p[i] < t.length() && i - p[i] >= 0 && t.charAt(i + p[i]) == t.charAt(i - p[i])) ++p[i];
	        if (mx < i + p[i]) {
	            mx = i + p[i];
	            id = i;
	        }
	        if (resLen < p[i]) {
	            resLen = p[i];
	            resCenter = i;
	        }
	    }
		return S.substring((resCenter - resLen) / 2, resLen - 1);
	}
	public static void main(String[] args) {
		String S = "aaab";
		System.out.println("Longest_Palindromic_Substring is " + longestPS_fromDoc(S));
	}

}
