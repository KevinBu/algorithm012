package cn.com.kevin.algorithm.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 105 从前序和中序遍历序列构造二叉树
 * 
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 
 * 注意: 你可以假设树中没有重复的元素。 
 * 
 * 例如，给出 
 * 前序遍历 preorder = [3,9,20,15,7] 
 * 中序遍历 inorder = [9,3,15,20,7] 
 * 
 * 返回如下的二叉树： 
 * 
 *      3 
 *     / \ 
 *     9 20 
 *      / \ 
 *     15 7
 */
public class BuildBinaryTree {

    private static Map<Integer, Integer> indexMap;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return createTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    /**
     * 创建树
     * @param preorder 前序
     * @param inorder 中序
     * @param preorderLeft 前序左子节点
     * @param preorderLight 前序右子节点
     * @param inorderLeft 中序左子节点
     * @param inorderRight 中序右子节点
     * @return
     */
    private static TreeNode createTree(
        int[] preorder, int[] inorder, 
        int preorderLeft, int preorderLight, 
        int inorderLeft, int inorderRight){
        if (preorderLeft > preorderLight) {
            return null;
        }
        // 前序遍历中的第一个节点就是根节点
        int preorderRoot = preorderLeft;
        // 在中序遍历中定位根节点
        int inorderRoot = indexMap.get(preorder[preorderRoot]);
        
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorderRoot]);
        // 得到左子树中的节点数目
        int sizeLeftSubtree = inorderRoot - inorderLeft;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = createTree(preorder, inorder, preorderLeft + 1, preorderLeft + sizeLeftSubtree, inorderLeft, inorderRoot - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = createTree(preorder, inorder, preorderLeft + sizeLeftSubtree + 1, preorderLight, inorderRoot + 1, inorderRight);
        return root;
    }

    public static void main(String[] args) {
        int [] preorder ={3,9,20,15,7}; 
        int [] inorder ={9,3,15,20,7};
        TreeNode treeNode = buildTree(preorder, inorder);
        System.out.println(treeNode);
    }
    
}