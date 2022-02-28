package com.zym.newleetcode.数组.滑动窗口之快慢指针;

/*
链表
 */
public class leetcode83 {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            int currentVal = cur.val;
            int nextVal = cur.next.val;
            if(currentVal == nextVal) {
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return head;
    }
}



class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }
}
