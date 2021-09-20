When thinking of Trie, you may try HashMap first (just need more space)

Trie (Prefix Tree):TrieNode child[26],  isWord/fullWord,  addWord(String)

node = root;

for (char c:string) {if (!node.child[c]) new node on son[c]; node =son[c] }