package tree.mytest;

import tree.TreeNode;

/**
 * 给定一棵二叉树的头节点，返回这颗二叉树是不是平衡二叉树
 * 平衡二叉树的定义，任何字数的左树和右树高度相差都不大于 1
 *
 * 我们可以使用递归套路的写法，
 * 在这个例子中，每个节点都需要问它的左子树是不是的高度是多少，是不是平衡的，这两个信息
 * 所以我们可以建立一个Info的结构体，收集需要询问的信息，
 * 然后每一层再根据字数收集回来的信息判断，构建新的信息返回给上一层
 */
public class IsBalanceTree {

    public static boolean isBalanceTree(TreeNode head) {
        return helper(head).isBalanced;
    }

    // 需要向左右子树询问的信息，就是height和isBalanced
    public static class Info {
        int height;
        boolean isBalanced;
        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static Info helper(TreeNode head) {
        // Base Case;
        if(head == null)
            return new Info(0 + 1, true);

        Info leftInfo = helper(head.left);
        Info rightInfo = helper(head.right);

        int max = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;

        if(!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }
        return new Info(max, isBalanced);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(  4);
        head.left.right = new TreeNode(5);

        boolean balanceTree = isBalanceTree(head);
        System.out.println(balanceTree);

    }


}
