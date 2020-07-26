package cn.com.kevin.algorithm.tree;

import java.util.ArrayList;
import java.util.List;



/**
 * LeetCode 236
 * 二叉树的最近公共祖先
 * 
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * 
 * 示例 1:
 *      输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *      输出: 3
 *      解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 
 * 示例 2:
 *      输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 *      输出: 5
 *      解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * 
 * 说明:
 *      所有节点的值都是唯一的。
 *      p、q 为不同节点且均存在于给定的二叉树中。
 * 
 */
public class LowestCommonAncestor {

    private static TreeNode parentNode;

    /**
     * 
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p == null || q==null){
            return null;
        }
        dfs(root, p, q);
        return parentNode;
        
    }

    private static boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return false;
        }
        // 判断左左子树是否包含指定的节点
        boolean lson = dfs(root.left, p, q);
        // 判断右子树是否包含自定的节点
        boolean rson = dfs(root.right, p, q);

        if((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))){
            parentNode = root;
        }

        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    private static TreeNode createBinaryTreeNode(Integer [] arr, int i){
        if(arr == null || arr.length == 0){
            return null;
        }
        if(i >= arr.length){
            return null;
        }
        Integer val = arr[i];
        
        if(val == null){
            return null;
        }
        TreeNode rootNode = null;
        rootNode = new TreeNode(arr[i]);
        rootNode.left = createBinaryTreeNode(arr, 2 * i + 1);
        rootNode.right = createBinaryTreeNode(arr, 2 * i + 2);
        return rootNode;
    }

    /**
     * 前序遍历（根-左-右）
     * 获得指定value 的节点
     * 通过递归实现
     * @param node
     */
    private static void preOrderRecursion(TreeNode node, int val, List <TreeNode> nodeList){
        //如果结点为空则返回
        if(node == null) {
            return;
        }
        if(node.val == val){
            nodeList.add(node);
        }else{
            preOrderRecursion(node.left, val, nodeList);//访问左孩子
            preOrderRecursion(node.right, val, nodeList);//访问右孩子
        }
    }

    public static void main(String[] args) {
        Integer [] root = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode rootNode = createBinaryTreeNode(root, 0);
        List <TreeNode> pNodeList = new ArrayList<>();
        preOrderRecursion(rootNode, 5, pNodeList);
        List <TreeNode> qNodeList = new ArrayList<>();
        preOrderRecursion(rootNode, 4, qNodeList);
        TreeNode pNode = pNodeList.size() > 0 ? pNodeList.get(0) : null;
        TreeNode qNode = qNodeList.size() > 0 ? qNodeList.get(0) : null;
        lowestCommonAncestor(rootNode, pNode, qNode);
    }
    
}