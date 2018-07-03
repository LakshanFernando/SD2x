package week_II.homework_IV;

/*
* This assignment is about using and modifying a binary search tree implementation in order to determine if the tree is
* balanced. It introduces a self-balancing implementation that is different from the Red-Black Tree.
*
*/
public class BinarySearchTree<E extends Comparable<E>> {

	// A nested node class for the binary tree.
	class Node {
		E value;
		Node leftChild = null;
		Node rightChild = null;
		Node(E value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof BinarySearchTree.Node))
				return false;
			@SuppressWarnings("unchecked")
			Node other = (BinarySearchTree.Node)obj;
			// for equality the value, the left & right children of this node must be equal to those of the node
			// being compared.
			return other.value.compareTo(value) == 0 &&
					other.leftChild == leftChild && other.rightChild == rightChild;
		}
	}
	
	protected Node root = null;
	
	protected void visit(Node n) {
		System.out.println(n.value);
	}
	
	public boolean contains(E val) {
		return contains(root, val);
	}
	
	protected boolean contains(Node n, E val) {
		if (n == null) return false;
		
		if (n.value.equals(val)) {
			return true;
		} else if (n.value.compareTo(val) > 0) {
			return contains(n.leftChild, val);
		} else {
			return contains(n.rightChild, val);
		}
	}
	
	public boolean add(E val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}
		return add(root, val);
	}
	
	protected boolean add(Node n, E val) {
		if (n == null) {
			return false;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return false; // this ensures that the same value does not appear more than once
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				n.leftChild = new Node(val);
				return true;
			} else {
				return add(n.leftChild, val);
			} 	
		} else {
			if (n.rightChild == null) {
				n.rightChild = new Node(val);
				return true;
			} else {
				return add(n.rightChild, val);
			} 	
		}
	}	
	
	public boolean remove(E val) {
		return remove(root, null, val);
	}
	
	protected boolean remove(Node n, Node parent, E val) {
		if (n == null) return false;

		if (val.compareTo(n.value) < 0) {
				return remove(n.leftChild, n, val);
		} else if (val.compareTo(n.value) > 0) {
				return remove(n.rightChild, n, val);
		} else {
			if (n.leftChild != null && n.rightChild != null){
				n.value = maxValue(n.leftChild);
				remove(n.leftChild, n, n.value);
			} else if (parent == null) {
				root = n.leftChild != null ? n.leftChild : n.rightChild;
			} else if (parent.leftChild == n){
				parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
			} else {
				parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
			}
			return true;
		}
	}

	protected E maxValue(Node n) {
		if (n.rightChild == null) {
			return n.value;
	    } else {
	       return maxValue(n.rightChild);
	    }
	}


	/*
	 IMPLEMENT THE METHODS BELOW!
	 */


	/**
	 * Given a value stored in the binary in the BST, it returns the corresponding {Node} that holds it.
	 * @param val: the value whose node is being looked for
	 * @return the node holding the value.
	 */
	public Node findNode(E val) {
		if (val == null) return null;

		return findNode(root, val);
	}

	/*
	* Takes the value whose node is to be found and an entry point into the tree.
	* if the value at the entry node is equal to {val} returns the entry node.
	* else if {val} is less than the value at the entry point, it searches the left child of the entry point recursively
	* else it searches the right recursively.
	*/
	private Node findNode(Node node, E val) {
		if (node == null) return null;

		if (node.value.equals(val)) return node;
		else if (node.value.compareTo(val) > 0) return findNode(node.leftChild, val);
		else return findNode(node.rightChild, val);
	}

	/**
	 * @param val
	 * @return
	 */
	protected int depth(E val) {
		if (val == null) return -1;

		Node node = root;
		int depth = 0;

		while (true) {
			if (node.value.equals(val)) return depth;
			else if (node.value.compareTo(val) > 0) node = node.leftChild;
			else node = node.rightChild;

			depth++;
			if (node == null) return -1;
		}
	}

	/**
	 * @param val
	 * @return
	 */
	protected int height(E val) {
		if (val == null) return -1;

		// checks if the node of the val exists in the tree, if not returns -1
		Node node = findNode(val);
		if (node == null) return -1;

		return height(node);
	}

	private int height(Node node) {
		if (node == null) return -1;

		if (node.leftChild == null && node.rightChild == null) return 0;

		return 1 + Math.max(height(node.rightChild), height(node.leftChild));
	}


	/**
	 * @param n
	 * @return
	 */
	protected boolean isBalanced(Node n) {
		if (n == null || !contains(n.value)) return false;

		// A node in a BTS is balanced if he different btw it left and right children is less than 2
		return Math.abs(height(n.rightChild) - height(n.leftChild)) < 2;
	}

	/**
	 * @return
	 */
	public boolean isBalanced() {
		return isBalancedTo(root);
	}

	private boolean isBalancedTo(Node node) {
		if (node == null) return false;

		boolean result = isBalanced(node);

		if (node.leftChild != null) result = result && isBalanced(node.leftChild);
		if (node.rightChild != null) result = result && isBalanced(node.rightChild);

		return result;
	}

}
