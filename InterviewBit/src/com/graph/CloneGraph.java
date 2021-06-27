package com.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CloneGraph {
	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	};

	HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

	private UndirectedGraphNode helper(UndirectedGraphNode src) {
		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		queue.add(src);
		map.put(src, new UndirectedGraphNode(src.label));

		while (!queue.isEmpty()) {
			UndirectedGraphNode node = queue.poll();
			for (UndirectedGraphNode x : node.neighbors) {
				if (!map.containsKey(x)) {
					queue.add(x);
					map.put(x, new UndirectedGraphNode(x.label));
				}
				map.get(node).neighbors.add(map.get(x));
			}
		}
		return map.get(src);
	}

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode src) {
		return helper(src);
	}
}
