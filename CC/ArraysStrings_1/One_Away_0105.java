package MagicBook.CC.ArraysStrings_1;

public class One_Away_0105 {

	public boolean OneAway1(String src, String dst) {
		int diff = src.length() - dst.length();
		if (Math.abs(diff) > 1) return false;
		String shortOne = (diff > 0) ? dst: src;
		String longOne = (diff > 0) ? src: dst;
		int diffCount = 0;
		for (int i=0; i < shortOne.length(); i++) {
			if (shortOne.charAt(i) == longOne.charAt(i)) continue;
			if (diffCount == 0) {
				if (shortOne.charAt(i) == longOne.charAt(i+1)) {
					diffCount++;
					continue;
				} else if (diff == 0){
					diffCount++;
					continue;
				}
			}
			return false;
		}
		return true;
	}

}
