
/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
	/**
	 * Construct an empty LinkedList.
	 */
	public void swap(MyLinkedList<AnyType> list, int firstIndex, int secondIndex) {
		Node<AnyType> firstIndexNode = beginMarker.next;
		Node<AnyType> secondIndexNode = beginMarker.next;
		if (firstIndex < 0 || firstIndex > list.size() || secondIndex < 0 || secondIndex > list.size()) {
			throw new IndexOutOfBoundsException(
					"index: " + firstIndex + "; second index" + secondIndex + "; size: " + size());
		}
		if (firstIndex == secondIndex) {
			return;
		}
		if (secondIndex < firstIndex) {
			int temp = firstIndex;
			firstIndex = secondIndex;
			secondIndex = temp;
		}

		for (int i = 0; i < firstIndex; i++) {
			firstIndexNode = firstIndexNode.next;
		}
		for (int i = 0; i < secondIndex; i++) {
			secondIndexNode = secondIndexNode.next;
		}
		if (firstIndexNode.next == secondIndexNode) {
			firstIndexNode.next = secondIndexNode.next;
			secondIndexNode.prev = firstIndexNode.prev;

			if (firstIndexNode.next != null)
				firstIndexNode.next.prev = firstIndexNode;

			if (secondIndexNode.prev != null)
				secondIndexNode.prev.next = secondIndexNode;

			secondIndexNode.next = firstIndexNode;
			firstIndexNode.prev = secondIndexNode;
		} else {
			Node<AnyType> previousSecondIndeNode = secondIndexNode.prev;
			Node<AnyType> nextSecondIndexNode = secondIndexNode.next;
			secondIndexNode.prev = firstIndexNode.prev;
			secondIndexNode.next = firstIndexNode.next;
			firstIndexNode.prev = previousSecondIndeNode;
			firstIndexNode.next = nextSecondIndexNode;
			if (secondIndexNode.next != null) {
				secondIndexNode.next.prev = secondIndexNode;
			}
			if (secondIndexNode.prev != null) {
				secondIndexNode.prev.next = secondIndexNode;
			}
			if (firstIndexNode.next != null) {
				firstIndexNode.next.prev = firstIndexNode;
			}
			if (firstIndexNode.prev != null) {
				firstIndexNode.prev.next = firstIndexNode;
			}
		}
		modCount++;
	}

	public void shift(MyLinkedList<AnyType> list, int index) {
		Node<AnyType> currentPointer;
		if (index >= theSize) {
			index = index % theSize;
		}
		if (index < 0) {
			index = theSize + index;
		}
		for (int i = 0; i < index; i++) {
			currentPointer = beginMarker.next;
			beginMarker.next = currentPointer.next;
			beginMarker.next.prev = beginMarker;
			currentPointer.next = endMarker;
			currentPointer.prev = endMarker.prev;
			currentPointer.prev.next = currentPointer;
			endMarker.prev = currentPointer;
		}
		modCount++;
	}

	public void erase(MyLinkedList<Integer> list, int index, int totalNumber) {
		Node<AnyType> start = getNode(index);
		Node<AnyType> end = getNode(index + totalNumber);
		start.prev.next = end;
		end.prev = start.prev;
		modCount++;
	}

	public void insertList(MyLinkedList<AnyType> lst, int index) {
		Node<AnyType> currentMarker = beginMarker;
		Node<AnyType> newListStart = lst.beginMarker.next;
		Node<AnyType> newListEnd = lst.endMarker.prev;
		for (int i = 0; i < index; i++)
			currentMarker = currentMarker.next;
		newListEnd.next = currentMarker.next;
		newListEnd.next.prev = newListEnd;
		currentMarker.next = newListStart;
		newListStart.prev = currentMarker;
		modCount++;
	}

	public MyLinkedList() {
		doClear();
	}

	private void clear() {
		doClear();
	}

	/**
	 * Change the size of this collection to zero.
	 */
	public void doClear() {
		beginMarker = new Node<>(null, null, null);
		endMarker = new Node<>(null, beginMarker, null);
		beginMarker.next = endMarker;

		theSize = 0;
		modCount++;
	}

	/**
	 * Returns the number of items in this collection.
	 * 
	 * @return the number of items in this collection.
	 */
	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Adds an item to this collection, at the end.
	 * 
	 * @param x any object.
	 * @return true.
	 */
	public boolean add(AnyType x) {
		add(size(), x);
		return true;
	}

	/**
	 * Adds an item to this collection, at specified position. Items at or after
	 * that position are slid one position higher.
	 * 
	 * @param x   any object.
	 * @param idx position to add at.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size(),
	 *                                   inclusive.
	 */
	public void add(int idx, AnyType x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	/**
	 * Adds an item to this collection, at specified position p. Items at or after
	 * that position are slid one position higher.
	 * 
	 * @param p Node to add before.
	 * @param x any object.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size(),
	 *                                   inclusive.
	 */
	private void addBefore(Node<AnyType> p, AnyType x) {
		Node<AnyType> newNode = new Node<>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}

	/**
	 * Returns the item at position idx.
	 * 
	 * @param idx the index to search in.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx    the index to change.
	 * @param newVal the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public AnyType set(int idx, AnyType newVal) {
		Node<AnyType> p = getNode(idx);
		AnyType oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * 
	 * @param idx index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1,
	 *                                   inclusive.
	 */
	private Node<AnyType> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx   index to search at.
	 * @param lower lowest valid index.
	 * @param upper highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException if idx is not between lower and upper,
	 *                                   inclusive.
	 */
	private Node<AnyType> getNode(int idx, int lower, int upper) {
		Node<AnyType> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());

		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
		} else {
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}

		return p;
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param idx the index of the object.
	 * @return the item was removed from the collection.
	 */
	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Removes the object contained in Node p.
	 * 
	 * @param p the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private AnyType remove(Node<AnyType> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;

		return p.data;
	}

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");

		for (AnyType x : this)
			sb.append(x + " ");
		sb.append("]");

		return new String(sb);
	}

	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * 
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This is the implementation of the LinkedListIterator. It maintains a notion
	 * of a current position and of course the implicit reference to the
	 * MyLinkedList.
	 */
	private class LinkedListIterator implements java.util.Iterator<AnyType> {
		private Node<AnyType> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;

		public boolean hasNext() {
			return current != endMarker;
		}

		public AnyType next() {
			if (modCount != expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if (!hasNext())
				throw new java.util.NoSuchElementException();

			AnyType nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (modCount != expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();

			MyLinkedList.this.remove(current.prev);
			expectedModCount++;
			okToRemove = false;
		}
	}

	/**
	 * This is the doubly-linked list node.
	 */
	private static class Node<AnyType> {
		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
			data = d;
			prev = p;
			next = n;
		}

		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
	}

	private int theSize;
	private int modCount = 0;
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;
	
	public static void main(String[] args) {
		MyLinkedList<Integer> lst = new MyLinkedList<>();
		MyLinkedList<Integer> newList = new MyLinkedList<>();
		for (int i = 0; i < 10; i++) {
			lst.add(i);
			newList.add(2 * i);
		}
		for (int i = 20; i < 30; i++)
			lst.add(0, i);
		// Demonstrating Swap
		System.out.println("List before swapping: " + lst);
		lst.swap(lst, 2, 4);
		System.out.println("List After Swapping nodes at index 2 and 4: " + lst);
		// Demonstrating shift
		System.out.println("List before shifting: "+lst);
		lst.shift(lst, 1);
		System.out.println("List after shifting by 1: "+lst);
		lst.shift(lst, -1);
		System.out.println("List after shifting by -1: "+lst);
		// Demonstrating erase
		System.out.println("List before erasing: "+lst);
		lst.erase(lst, 2, 3);
		System.out.println("List after erasing 3 nodes from index 2: "+lst);
		//Demonstrating insertList
		System.out.println("List before inserting new list: "+lst);
		System.out.println("List to be inserted: "+newList);
		lst.insertList(newList, 3);
		System.out.println("List after inserting new list at index 3: "+lst);
	}
}
