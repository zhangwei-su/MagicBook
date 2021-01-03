package MagicBook.CC.ArraysStrings_1;

public class Is_Unique_0101 {

	//assume using basic ASCII char, and no supply data space limitation
	public boolean IsUnique1(String src) {
		if (src.length() > 128) return false;
		boolean[] checker = new boolean[128];
		for (int i=0; i < src.length(); i++) {
			int index = src.charAt(i);
			if (checker[index]) return false;
			checker[index] = true;
		}
		return true;
	}
    //assume all lowcase chars, and limit supply data space to O(1)
	public boolean IsUnique2(String src) {
		int checker = 0;
		for (int i=0; i < src.length(); i++) {
			int index = src.charAt(i);
			int mark = 1 << (index - 'a');
			if ((checker & mark) != 0) return false;
			checker |= mark;
		}
		return true;
	}
}
