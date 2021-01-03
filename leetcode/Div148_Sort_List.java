package MagicBook.leetcode;

import java.util.Arrays;

public class Div148_Sort_List {

	public static class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	}
	//up-bottom way
    public static ListNode sortList_1(ListNode head) {
        if (head == null || head.next == null) return head;
        //it is to find mid,
        //Since We need slow stop on preNode of mid, when two nodes, not update slow, when > two, update slow,
        //so, fast initial with head.next;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        return merge_1(sortList_1(head), sortList_1(mid));
    }
    public static ListNode merge_1(ListNode link1, ListNode link2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (link1 != null && link2 != null) {
            if (link1.val < link2.val) {
                tail.next = link1;
                link1 = link1.next;
            } else {
                tail.next = link2;
                link2 = link2.next;
            }
            tail = tail.next;
        }
        tail.next = link1 == null ? link2 : link1;
        return dummy.next;
    }
    
    //Bottom-up way
    public static ListNode cutNextK(ListNode head, int k) {
    	if (head == null) return null;
        ListNode ret = null;
        int i = 1;
        while (i<k && head.next!=null) {
            i++;
            head = head.next;
        }
        if (head.next !=null) {
            ret =  head.next;
            head.next = null;
        }
        return ret;
    }
    public static ListNode[] merge_2(ListNode link1, ListNode link2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        System.out.println("l=" + listNodeToString(link1) + ", r=" + listNodeToString(link2));
        while (link1 != null && link2 != null) {
            if (link1.val < link2.val) {
                tail.next = link1;
                link1 = link1.next;
            } else {
                tail.next = link2;
                link2 = link2.next;
            }
            tail = tail.next;
        }
        tail.next = link1 == null ? link2 : link1;
        while (tail.next != null) tail = tail.next;
        ListNode[] ret = {dummy.next, tail};
        return ret;
    }
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        int len=0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (head != null) {
            len++;
            head = head.next;
        }
        
        for (int k=1; k<=len; k=k<<1) {
            ListNode tail = dummy;
            ListNode curr = dummy.next;
            System.out.println("K=" + k + listNodeToString(dummy.next));
            while (tail.next != null) {
                ListNode l = curr;
                ListNode r = cutNextK(l, k);
                curr = cutNextK(r, k);
                ListNode[] mergeInfo = merge_2(l, r);
                tail.next = mergeInfo[0];
                tail = mergeInfo[1];
                mergeInfo[1].next = curr;
            }
        }
        return dummy.next;       
    }
    public static String listNodeToString(ListNode head) {
    	StringBuilder sb = new StringBuilder("[");
    	while(head != null) {
    		sb.append(head.val).append(',');
    		head = head.next;
    	}
    	sb.append(']');
    	return sb.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int[] test = {-1,5,3,4,0};
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i:test) {
        	curr.next = new ListNode(i);
        	curr = curr.next;
        }
        dummy.next = sortList(dummy.next);
        System.out.println(listNodeToString(dummy.next));
	}

}
