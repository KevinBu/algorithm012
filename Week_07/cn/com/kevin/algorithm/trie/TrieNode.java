package cn.com.kevin.algorithm.trie;

/** 字典树节点对象 */
public class TrieNode {
  // 存储的字符
  public char val;
  public boolean isWord;
  // 初始化 26 个字母的节点对象数组
  public TrieNode[] children = new TrieNode[26];
  // 无参数构造函数
  public TrieNode() {}

  public TrieNode(char c) {
    TrieNode node = new TrieNode();
    node.val = c;
  }
}
