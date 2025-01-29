/**
 * Represents a linked list of Nodes.
 */
public class LinkedList {

	private Node head; // points to the first node in the list
	private Node tail; // points to the last node in the list
	private int length; // tracks the number of nodes in the list

	/**
	 * Creates a new empty list.
	 */
	public LinkedList() {
		head = null;
		tail = head;
		length = 0;
	}

	/**
	 * Retrieves the first node in the list.
	 * @return The first node.
	 */
	public Node getFirst() {
		return this.head;
	}

	/**
	 * Retrieves the last node in the list.
	 * @return The last node.
	 */
	public Node getLast() {
		return this.tail;
	}

	/**
	 * Gets the current size of the list.
	 * @return The size of the list.
	 */
	public int getSize() {
		return this.length;
	}

	/**
	 * Retrieves the node at a specified index.
	 *
	 * @param index The index of the node to fetch, between 0 and size.
	 * @throws IllegalArgumentException if the index is out of bounds.
	 * @return The node at the specified index.
	 */
	public Node getNode(int index) {
		if (index < 0 || index >= length) {
			throw new IllegalArgumentException("Index must be between 0 and size.");
		}
		ListIterator iterator = new ListIterator(head);
		for (int i = 0; i < index; i++) {
			iterator.next();
		}
		return iterator.current;
	}

	/**
	 * Adds a new node with the given memory block at a specified index.
	 * If the index is 0, it adds at the beginning; if it is equal to the list size, it adds at the end.
	 *
	 * @param block The memory block to add.
	 * @param index The index at which to insert the new node.
	 * @throws IllegalArgumentException if the index is invalid.
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > length) {
            throw new IllegalArgumentException("Index must be between 0 and size.");
        }

        Node newNode = new Node(block);

        if (index == 0) {
            addFirst(block);
            if (length == 1) {
                tail = head;
            }
        } else if (index == length) {
            addLast(block);
        } else {
            Node previousNode = getNode(index - 1);
            newNode.next = previousNode.next;
            previousNode.next = newNode;
            this.length++;
        }
	}

	/**
	 * Appends a new node with the given memory block to the end of the list.
	 *
	 * @param block The memory block to append.
	 */
	public void addLast(MemoryBlock block) {
		Node newNode = new Node(block);
		if (this.length == 0) {
		   this.head = newNode;
		   this.tail = newNode;
		} else {
		   this.tail.next = newNode;
		   this.tail = newNode;
		}
		++this.length;
	 }

	/**
	 * Inserts a new node with the given memory block at the start of the list.
	 *
	 * @param block The memory block to prepend.
	 */
	public void addFirst(MemoryBlock block) {
		Node newNode = new Node(block);
		if (this.length == 0) {
		   this.head = newNode;
		   this.tail = newNode;
		} else {
			newNode.next = this.head;
		   this.head = newNode;
		}
		++this.length;
	 }

	/**
	 * Retrieves the memory block at a specific index.
	 *
	 * @param index The index of the memory block.
	 * @return The memory block at the given index.
	 * @throws IllegalArgumentException if the index is out of bounds.
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index >= length) {
            throw new IllegalArgumentException("Index must be between 0 and size.");
        }
        if (length == 0) {
            throw new IllegalArgumentException("Index must be between 0 and size.");
        }
        return getNode(index).block;
	}

	/**
	 * Finds the index of a node containing the specified memory block.
	 *
	 * @param block The memory block to find.
	 * @return The index of the block, or -1 if not found.
	 */
	public int indexOf(MemoryBlock block) {
		for (int i = 0; i < this.length; i++) {
			if (getBlock(i).equals(block)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Removes a specified node from the list.
	 *
	 * @param node The node to remove.
	 */
	public void remove(Node node) {
		if (node == null || length == 0) {
            throw new NullPointerException();
        }

        if (node.equals(head)) {
            head = head.next;
            length--;
            if (length == 0) {
                tail = null;
            }
            if (length == 1) {
                tail = head;
            }
        } else if (node.equals(tail)) {
            tail = getNode(length - 2);
            getNode(length - 2).next = null;
            length--;
        } else {
            MemoryBlock block = node.block;
            int index = indexOf(block);
            if (index < 0) {
                throw new IllegalArgumentException("Index must be between 0 and size.");
            }
            getNode(index - 1).next = node.next;
            length--;
        }
	}

	/**
	 * Removes a node at the specified index from the list.
	 *
	 * @param index The index of the node to remove.
	 * @throws IllegalArgumentException if the index is invalid.
	 */
	public void remove(int index) {
		if (index < 0 || index >= length) {
            throw new IllegalArgumentException("Index must be between 0 and size.");
        }
        Node node = getNode(index);
        remove(node);
	}

	/**
	 * Removes the node containing the specified memory block.
	 *
	 * @param block The memory block to remove.
	 * @throws IllegalArgumentException if the block is not in the list.
	 */
	public void remove(MemoryBlock block) {
		int index = indexOf(block);
        if (index < 0) {
            throw new IllegalArgumentException("Index must be between 0 and size.");
        }
        Node node = getNode(index);
        remove(node);
	}

	/**
	 * Returns an iterator to traverse the list, starting from the first element.
	 */
	public ListIterator iterator() {
		return new ListIterator(head);
	}

	/**
	 * Provides a string representation of the list for debugging purposes.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = head;
		while (current != null) {
			sb.append(current.block).append(" ");
			current = current.next;
		}
		return sb.toString();
	}
}
