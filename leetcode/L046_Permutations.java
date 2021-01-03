package MagicBook.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class L046_Permutations {
/*
Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */
//////////////////////////////////////////////////////	
	static ArrayList<ArrayList<Integer>> permutations_visit(Integer[] numbers) {
		Set<Integer> visited = new HashSet<Integer>();
		ArrayList<Integer> current = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if (numbers.length == 0) return ret;
		permutations_v(numbers, visited, current, ret);
		return ret;
	}
	static void permutations_v(Integer[] numbers, 
			Set<Integer> visited,
			ArrayList<Integer> current, 
			ArrayList<ArrayList<Integer>> ret) {
		System.out.printf("visited=%s, current=%s\n", visited, current);
		if (visited.size() == numbers.length) {
			ret.add(new ArrayList<Integer>(current));
			return;
		}
		for (int i=0; i<numbers.length; i++) {
			if (visited.contains(numbers[i])) continue;
			visited.add(numbers[i]);
			current.add(numbers[i]);
			permutations_v(numbers, visited, current, ret);
			visited.remove(numbers[i]);
			current.remove(numbers[i]);
		}
		
	}
////////////////////////////////////////////////////////
	static void swap(Integer[] numbers, int idx1, int idx2) {
		Integer temp = numbers[idx1];
		numbers[idx1] = numbers[idx2];
		numbers[idx2] = temp;
	}
	static ArrayList<ArrayList<Integer>> permutations_wrap(Integer[] numbers) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if (numbers.length == 0) return ret;
		permutations_sw(numbers, 0,  ret);
		return ret;
	}
	static void permutations_sw(Integer[] numbers, 
			int start,
			ArrayList<ArrayList<Integer>> ret) {
		if (start == numbers.length) {
			return;
		}
		ret.add(new ArrayList<Integer>(Arrays.asList(numbers)));
		//HERE,when i=start, will cause dupped list in ret. but if i=start+1, will miss some permutation
		for (int i=start; i<numbers.length; i++) {
			swap(numbers, start, i);
			permutations_sw(numbers, start+1, ret);
			swap(numbers, start, i);
		}
	}
/////////////////////////////////////////////////////////
	static ArrayList<ArrayList<Integer>> permutations_insert(Integer[] numbers) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if (numbers.length == 0) return ret;
		ArrayList<Integer> newItem = new ArrayList<Integer>();
		newItem.add(numbers[0]);
		ret.add(newItem);
		int index = 1;
		while (index < numbers.length) {
			int oldLen = ret.size();
			for (int i=0; i<oldLen; i++) {
				ArrayList<Integer> item = ret.get(i);
				for (int j=0; j<=item.size(); j++) {
					newItem = new ArrayList<Integer>(item);
					newItem.add(j, numbers[index]);
					ret.add(newItem);
				}
			}
			for (int i=0; i<oldLen; i++) {
				ret.remove(0);
			}
			index++;
		}
		return ret;
	}
/////////////////////////////////////////////////////////
	public static void main(String[] args) {
		Integer[] numbers = {1,2,3};
		ArrayList<ArrayList<Integer>> ret = permutations_insert(numbers);
		System.out.println("///////////RESULT//////////");
        for (ArrayList<Integer> item: ret) {
			System.out.println(item.toString());
		}
	}

}
