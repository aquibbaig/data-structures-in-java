import java.util.ArrayList;

class Trie {
   int count=0;
   static class Node {
    char character;
    ArrayList<Node> children;
    boolean isComplete = false;
    public Node(char c) {
       this.character = c;
       this.children = new ArrayList<Node>();
       this.isComplete = false;
      }
    }
  Node root;
  /** Initialize your data structure here. */
  public Trie() {
      root=new Node('*');
  }

  public void print(Node curr) {
      if (curr.isComplete == true) {
          System.out.println(curr.character);
      } else {
          if (curr.children.size() > 0) {
              System.out.println(curr.character);
              // traverse all children recursively.
              for (int i = 0; i < curr.children.size(); i++) {
                  print(curr.children.get(i));
              }
          } else {
              return;
          }
      }
  }

  public Node insertIntoTrie(char word, Node curr) {
      Node newNode = new Node(word);
      if (curr.children.size() == 0) {
          curr.children.add(newNode);
          return newNode;
      } else {
          int equal = -1;
          // Check for equality and return the node.
          for (int i = 0; i < curr.children.size(); i++) {
              if (word == curr.children.get(i).character) {
                  equal = i;
              }
          }
          if (equal != -1) {
              return curr.children.get(equal);
          } else {
              curr.children.add(newNode);
              return newNode;
          }
          // never reaches here.
      }
  }
  /** Inserts a word into the trie. */
  public void insert(String word) {
      char[] wordArr = word.toCharArray();
      Node curr = this.root;
      for (int i = 0; i < wordArr.length; i++) {
          curr = insertIntoTrie(wordArr[i], curr);
      }
      // insert * at last
      curr.isComplete = true;
  }

  public Node searchInTrie(char w, Node c) {
    if (c.children.size() == 0) {
      return new Node('%');
    } else {
      // check for equality.
      int equal = -1;
      for (int i=0; i < c.children.size(); i++) {
        if (w == c.children.get(i).character) {
          equal = i;
        }
      }
      if (equal != -1) {
          return c.children.get(equal);
      } else {
        return new Node('%');
      }
    }
  }
  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    char[] wordArray = word.toCharArray();
    Node curr = this.root; 
    for (int i=0; i < wordArray.length; i++) {
        curr = searchInTrie(wordArray[i], curr);
    }
    if (curr.character == wordArray[wordArray.length-1] && curr.isComplete) {
        System.out.println("Exists" + word);
        return true;
    } else {
      System.out.println("Does not exist" + word);
      return false;
    }
  }

  public Node startsWithInTrie(char w, Node c) {
    if (c.children.size() == 0) {
      return new Node('%');
    } else {
        int equal = -1;
        for (int i=0; i < c.children.size(); i++) {
            if (c.children.get(i).character == w) {
                equal = i;
            }
        }
        if (equal != -1) {
          this.count += 1;
          return c.children.get(equal);
        } else return new Node('%');
    }
  }
  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    char[] wordArr = prefix.toCharArray();
    Node curr = this.root;
    // start case.
    if (curr.children.size() == 0) {
        return false;
    }
    // Set count variable.
    this.count = 0;
    for (int i=0; i < wordArr.length; i++) {
      curr = startsWithInTrie(wordArr[i], curr);
      if (curr.character == '%') {
        break;
      }
    }
    if (this.count < prefix.toCharArray().length || this.count == 0) {
      System.out.println("Does not start with" + prefix);
      return false;
    }
    System.out.println("Starts with" + prefix);
    return true;
  }

  public static void main(String args[]) {
    Trie trie = new Trie();

    trie.insert("hello");
    trie.search("hell"); 
    trie.search("helloa");
    trie.search("hello");
    trie.startsWith("hell");
    trie.startsWith("helloa");
    trie.startsWith("hello");
  }
}

/**
* Your Trie object will be instantiated and called as such:
* Trie obj = new Trie();
* obj.insert(word);
* boolean param_2 = obj.search(word);
* boolean param_3 = obj.startsWith(prefix);
*/