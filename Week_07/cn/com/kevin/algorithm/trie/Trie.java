package cn.com.kevin.algorithm.trie;

/**
 * LeetCode 208 实现trie前缀树
 *
 * <p>实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * <p>示例:
 *
 * <p>Trie trie = new Trie();
 *
 * <p>trie.insert("apple");
 *
 * <p>trie.search("apple"); // 返回 true
 *
 * <p>trie.search("app"); // 返回 false
 *
 * <p>trie.startsWith("app"); // 返回 true
 *
 * <p>trie.insert("app");
 *
 * <p>trie.search("app"); // 返回 true
 *
 * <p>说明:
 *
 * <p>你可以假设所有的输入都是由小写字母 a-z 构成的。
 *
 * <p>保证所有输入均为非空字符串。
 */
public class Trie {
  private TrieNode root; // 定义根节点

  public Trie() {
    root = new TrieNode();
    root.val = ' ';
  }

  /** @param word */
  public void insert(String word) {
    TrieNode ws = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (ws.children[c - 'a'] == null) {
        ws.children[c - 'a'] = new TrieNode(c);
      }
      ws = ws.children[c - 'a'];
    }
    ws.isWord = true;
  }

  public boolean search(String word) {
    TrieNode ws = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (ws.children[c - 'a'] == null) {
        return false;
      }
      ws = ws.children[c - 'a'];
    }
    return ws.isWord;
  }

  public boolean startWith(String prefix) {
    TrieNode ws = root;
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      if (ws.children[c - 'a'] == null) {
        return false;
      }
      ws = ws.children[c - 'a'];
    }
    return true;
  }
}
