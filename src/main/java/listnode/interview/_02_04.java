package listnode.interview;

import listnode.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 02.08. 环路检测
 * https://leetcode-cn.com/problems/linked-list-cycle-lcci/
 *
 * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 */

/**
 * 解题方法：
 * 一、哈希表
 * 1、首先最能想到的肯定就是用哈希表记录了，但这种方法的效率不高
 *
 * 二、快慢指针
 * 1、快指针每次走两步，慢指针每次走一步。
 * 当两指针相遇时，将快指针挪到表头，两指针一步一步走，当他们再次相遇，该节点即为第一个入环节点
 */


public class _02_04 {

    public ListNode detectCycle(ListNode head) {
        if(head == null)
            return null;
        if(head.next == null)
            return null;
        Map<ListNode, Integer> map = new HashMap<>();
        int pos = 0;
        ListNode cur = head;
        int targetPos = -1;
        while(cur != null) {
            if(map.containsKey(cur)) {
                targetPos = map.get(cur);
                break;
            }
            map.put(cur, pos++);
            cur = cur.next;
        }
        if(targetPos == -1)
            return null;
        cur = head;
        while(targetPos-- != 0) {
            cur = cur.next;
        }
        return cur;
    }


    public static ListNode detectCycle1(ListNode head) {

        if(head == null || head.next == null)
            return null;

        // 最少两个节点
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 快慢指针相遇
            if(slow == fast)
                break;
        }

        // 快慢指针相遇了或快指针遍历到尾节点了
        // 无环
        if(fast == null || fast.next == null) {
            return null;
        }

        // 有环
        fast = head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    public static void printList(ListNode head) {
        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }


    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = l1;
        ListNode listNode = detectCycle1(l1);
        System.out.println(listNode.val);

    }


}
