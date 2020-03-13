package app;

public class Solution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 链表逆转
     */
    // 循环实现
    public static ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode current = head;
        ListNode next = head.next;

        while (current.next != null) {
            current.next = prev;

            prev = current;
            current = next;
            next = next.next;
        }
        current.next = prev;
        return current;
    }

    //
    public static ListNode reverseNode(ListNode current, ListNode next) {
        ListNode newNext = next.next;
        if (newNext == null) {
            return next;
        }
        next.next = current;
        current = next;
        return reverseNode(current, newNext);
    }

    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newListNode = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newListNode;
    }

    public static void testHasCycle() {
        ListNode head = new ListNode(100);
        ListNode node1 = new ListNode(100);
        head.next = node1;
        ListNode node2 = new ListNode(100);
        node1.next = node2;
        ListNode node3 = new ListNode(100);
        node2.next = node3;
        System.out.println("hasCycle:" + (hasCycle(head) ? "true˝" : "fasle"));
        node3.next = node1;
        System.out.println("hasCycle:" + (hasCycle(head) ? "true˝" : "fasle"));
    }

    /**
     * 链表是否有环
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /*
     * 删除链表的节点
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            if (current.val == val) {
                break;
            }

            prev = current;
            current = current.next;
        }
        if (prev == null) {
            return current.next;
        } else {
            prev.next = current.next;
        }
        return head;
    }

    /*
     * 链表的中间结点
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*
     * 删除排序链表中的重复元素
     */
    public ListNode deleteDuplicates(ListNode head) {

        ListNode current = head;
        while (current != null&&current.next !=null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            }else{
                current = current.next;
            } 
        }
       
        return head;
    }
}
