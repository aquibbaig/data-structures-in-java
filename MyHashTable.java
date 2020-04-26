class Node<T> {
  T data;
  String key;
  Node<T> next;
  Node(String k, T d) {
    this.key = k;
    this.data = d;
  }
}

class HashTable<T> {
  Node<T> arr[];
  HashTable() {
    // constructor
    this.arr = new Node[8];
  }
  public void add(String key, T value) {
    // grow the hashtable (Table doubling)
    int len = 0;
    for (int i=0; i < this.arr.length; i++) {
      if (this.arr[i] != null) len++;
    }
    if(len >= this.arr.length/2) {
      Node newArr[] = new Node[this.arr.length*2];
      for (int i=0; i < this.arr.length; i++) {
        if (this.arr[i] != null) {
          // move it to the new array
          int hashCode = this.arr[i].key.hashCode();
          newArr[hashCode] = new Node<T>(this.arr[i].key, this.arr[i].data);
        };
      }
      this.arr = newArr;
    }
    
    // Inserts a value into the HashTable
    int hashcode = key.hashCode() % this.arr.length;
    if (this.arr[hashcode] == null) {
      this.arr[hashcode] = new Node<T>(key, value);
    } else {
      // traverse the set of nodes
      Node<T> nodeAtPos = arr[hashcode];
      Node<T> curr = nodeAtPos;
      while (curr != null) {
        curr = curr.next;
      }
      curr = new Node<T>(key, value);
    }
  }

  public void get(String key) {
    int hashcode = key.hashCode() % this.arr.length;
    if (this.arr[hashcode] == null) {
      System.out.println("Key doesn't exist");
    } else {
      // traverse the linked list
      Node<T> nodeAtPos = this.arr[hashcode];
      Node<T> curr = nodeAtPos;
      // Traversal starts
      while(curr != null) {
        if (curr.key == key) {
          System.out.println("Your data is: " + curr.data);
        }
        curr = curr.next;
      }
      // Traversal ends
    }
  }
}

class MyHashTable {
  public static void main(String args[]) {
    HashTable<Integer> ht = new HashTable<Integer>();
    ht.add("Aquib", 20);
    ht.get("Aquib");
    // ht.add("Aquib", "Baig");
  }
}