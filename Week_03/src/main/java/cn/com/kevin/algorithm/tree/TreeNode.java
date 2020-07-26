package cn.com.kevin.algorithm.tree;
/**
 * 树节点的Java实现
 */
public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}