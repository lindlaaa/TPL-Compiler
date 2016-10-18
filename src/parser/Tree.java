package src.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class Tree<T> {

  /**
   *
   */
  private T head;

  /**
   *
   */
  private ArrayList<Tree<T>> leafs = new ArrayList<Tree<T>>();

  /**
   *
   */
  private Tree<T> parent = null;

  /**
   *
   */
  private HashMap<T, Tree<T>> locate = new HashMap<T, Tree<T>>();


  public Tree(T head) {
    this.head = head;
    locate.put(head, this);
  }

  /**
   *  [addLeaf description]
   *  @param  [description]
   *  @param  [description]
   */
  public void addLeaf(T root, T leaf) {
    if (locate.containsKey(root)) {
      locate.get(root).addLeaf(leaf);
    } else {
      addLeaf(root).addLeaf(leaf);
    }
  }

  /**
   *  [addLeaf description]
   *  @param  leaf [description]
   *  @return      [description]
   */
  public Tree<T> addLeaf(T leaf) {
    Tree<T> t = new Tree<T>(leaf);
    leafs.add(t);
    t.parent = this;
    t.locate = this.locate;
    locate.put(leaf, t);
    return t;
  }

  /**
   *  [setAsParent description]
   *  @param  parentRoot [description]
   *  @return            [description]
   */
  public Tree<T> setAsParent(T parentRoot) {
    Tree<T> t = new Tree<T>(parentRoot);
    t.leafs.add(this);
    this.parent = t;
    t.locate = this.locate;
    t.locate.put(head, this);
    t.locate.put(parentRoot, t);
    return t;
  }

  /**
   *  [getHead description]
   *  @return [description]
   */
  public T getHead() {
    return head;
  }

  /**
   *  [getTree description]
   *  @param  element [description]
   *  @return         [description]
   */
  public Tree<T> getTree(T element) {
    return locate.get(element);
  }

  /**
   *  [getParent description]
   *  @return [description]
   */
  public Tree<T> getParent() {
    return parent;
  }

  /**
   *  [getSuccessors description]
   *  @param  root [description]
   *  @return      [description]
   */
  public Collection<T> getSuccessors(T root) {
    Collection<T> successors = new ArrayList<T>();
    Tree<T> tree = getTree(root);
    if (null != tree) {
      for (Tree<T> leaf : tree.leafs) {
        successors.add(leaf.head);
      }
    }
    return successors;
  }

  /**
   *  [getSubTrees description]
   *  @return [description]
   */
  public Collection<Tree<T>> getSubTrees() {
    return leafs;
  }

  /**
   *  [getSuccessors description]
   *  @param   [description]
   *  @param   [description]
   *  @return  [description]
   */
  public static <T> Collection<T> getSuccessors(T of, Collection<Tree<T>> in) {
    for (Tree<T> tree : in) {
      if (tree.locate.containsKey(of)) {
        return tree.getSuccessors(of);
      }
    }
    return new ArrayList<T>();
  }

  /**
   *  [toString description]
   *  @return [description]
   */
  @Override
  public String toString() {
    return printTree(0);
  }

  /**
   *
   */
  private static final int indent = 2;

  /**
   *  [printTree description]
   *  @param  increment [description]
   *  @return           [description]
   */
  private String printTree(int increment) {
    String s = "";
    String inc = "";
    for (int i = 0; i < increment; ++i) {
      inc = inc + " ";
    }
    s = inc + head;
    for (Tree<T> child : leafs) {
      s += "\n" + child.printTree(increment + indent);
    }
    return s;
  }
}
