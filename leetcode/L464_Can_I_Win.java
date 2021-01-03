package MagicBook.leetcode;

import java.util.Arrays;

public class L464_Can_I_Win {

	static boolean canIWin(boolean[] used, int desiredTotal, boolean isMe) {
        if (desiredTotal<=0) return false;
        for (int i =0; i<used.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            if (!canIWin(used, desiredTotal-(i+1), !isMe)) {
            	System.out.printf("%s win : %s\n", (isMe) ? "I " : "You", Arrays.toString(used));
                used[i] = false;
                return true;
            } else {
                used[i] = false;
            }
        }
        return false;
    }
    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) return true;
        boolean[] used = new boolean[maxChoosableInteger];
        return canIWin(used, desiredTotal, true);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        boolean win = canIWin(4, 6);
        System.out.printf("canIWin %b\n", win);
	}

}
