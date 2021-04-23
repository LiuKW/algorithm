package tree.mytest;

import tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化与反序列化
 */
public class SerialAndDeserialTree {


    /**
     * 服务器都有宕机的可能性
     * 所以需要序列化内存中的数据
     * 二叉树序列化就是将空指也占位
     * 1, 2, 4, null, null, 5, null, null, 3, null, null
     */
    static Queue<String> serialList = new LinkedList<>();
    public static void treeSerialize(TreeNode head) {
        if(head == null) {
            serialList.add(null);
        }
        else {
            serialList.add(String.valueOf(head.val));
            treeSerialize(head.left);
            treeSerialize(head.right);
        }
    }

    /**
     * 反序列化则会根据空值判断，构建二叉树
     */
    public static TreeNode buildTree(Queue<String> serialList) {
        if(serialList.isEmpty())
            return null;
        String poll = serialList.poll();
        if(poll == null)
            return null;
        TreeNode head = new TreeNode(Integer.parseInt(poll));
        head.left = buildTree(serialList);
        head.right = buildTree(serialList);
        return head;
    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);

        treeSerialize(head);
        System.out.println(serialList);
        TreeNode treeNode = buildTree(serialList);
        PrintTreeWithoutRecursion.levelOrder(treeNode);
    }

}
