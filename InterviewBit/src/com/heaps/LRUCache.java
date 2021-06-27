package com.heaps;

import java.util.HashMap;

public class LRUCache {

	class DLL {
		int key;
		int val;
		DLL prev;
		DLL next;

		DLL(int key, int val) {
			this.key = key;
			this.val = val;
			this.prev = null;
			this.next = null;
		}
	}

	final int n;
	int size = 0;
	DLL head, tail;
	HashMap<Integer, DLL> nodeMap;

	public LRUCache(int capacity) {
		n = capacity;
		nodeMap = new HashMap<Integer, LRUCache.DLL>();
	}

	private void removeFromMiddle(DLL node) {
		if (tail == node)
			return;

		if (head == node) {
			head = head.next;
			head.prev = null;
			addToRear(node);
		} else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node.next = node.prev = null;
			addToRear(node);
		}

	}

	private void addToRear(DLL node) {

		if (tail == node) {
			return;
		}

		if (head == null) {
			head = tail = node;
			return;
		}

		tail.next = node;
		node.prev = tail;
		tail = node;

	}

	private void removeFromFront() {

		if (head == tail) {
			head = tail = null;
			return;
		}
		head = head.next;
		if (head != null)
			head.prev = null;
	}

	public int get(int key) {
		DLL node = nodeMap.get(key);
		if (node == null)
			return -1;
		removeFromMiddle(node);

		return node.val;
	}

	public void set(int key, int value) {
		DLL ifNodeExists = nodeMap.get(key);
		if (ifNodeExists != null) {
			ifNodeExists.val = value;
			removeFromMiddle(ifNodeExists);
			return;
		}

		if (size == n) {
			nodeMap.remove(head.key);
			removeFromFront();
			size--;
		}

		DLL newNode = new DLL(key, value);
		addToRear(newNode);
		nodeMap.put(key, newNode);
		size++;

	}

	public static void main(String[] args) {
		LRUCache obj = new LRUCache(2);
		obj.set(2, 1);
		obj.set(1, 1);
		obj.set(2, 3);
		obj.set(4, 1);
		System.out.println(obj.get(1));
		System.out.println(obj.get(2));
	}
}
