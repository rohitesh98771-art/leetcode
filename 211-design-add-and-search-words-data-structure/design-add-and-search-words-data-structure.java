class WordDictionary {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null)
                node.children[i] = new TrieNode();
            node = node.children[i];
        }

        node.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, TrieNode node) {
        if (node == null) return false;

        if (index == word.length())
            return node.isEnd;

        char c = word.charAt(index);

        if (c == '.') {
            for (TrieNode child : node.children) {
                if (dfs(word, index + 1, child))
                    return true;
            }
            return false;
        }

        return dfs(word, index + 1, node.children[c - 'a']);
    }
}