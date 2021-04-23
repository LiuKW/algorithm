package tree.mytest;

import tree.TreeNode;

/**
 * 给定一棵二叉树的头节点，任何两个节点之间都存在距离
 * 返回整棵二叉树的最大距离
 * 经过一个节点距离+1
 * 相邻两个节点的距离为2
 *
 * 最远距离的意思就是，左树上离自己最远的点 + 右树上离自己最远的点 + 自身的距离。离自己最远就是高度最高呗
 * maxDistance = leftHeightest + rightHeightest + 1  / 叶子节点时，通过高度构建
 */

public class FindTreeMaxDistance {

    public static int findTreeMaxDistance(TreeNode head) {
        return helper(head).maxDistance;
    }

    // 需要向左右子树询问的信息结构体
    public static class Info {
        int maxDistance;
        int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static Info helper(TreeNode head) {
        // Base Case
        if(head == null) {
            return new Info(0, 0);
        }

        Info leftInfo = helper(head.left);
        Info rightInfo = helper(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                leftInfo.height + rightInfo.height + 1);
        return new Info(maxDistance, height);
    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        System.out.println(findTreeMaxDistance(head));
    }


}