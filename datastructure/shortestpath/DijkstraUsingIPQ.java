package com.java.datastructure.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class DijkstraUsingIPQ {

	class Node {
		
		int node;
		double cost;
		
		public Node(int node, double cost) {
			this.node = node;
			this.cost = cost;
		}
	}
	
	private int len;
	private List<List<Node>> adj;
	
	public DijkstraUsingIPQ(int len) { 
		this.len = len; 
		createEmptyGraph();
	}
	
	private void createEmptyGraph() {
		adj = new ArrayList<List<Node>>();
		
		for (int i = 0; i < len; i++) adj.add(new ArrayList<Node>());
	}
	
	public void addEdge(int from, int to, double cost) {
		adj.get(from).add(new Node(to, cost));
	}
	
	public double[] dijkstra(int start, int end) {
		
		boolean[] visited = new boolean[len];
		
		double[] dist = new double[len];
		for (int i = 0; i < len; i++) dist[i] = Double.POSITIVE_INFINITY;
		dist[start] = 0.0;
		
		int[] prevNode = new int[len];
		prevNode[start] = -1;
		
		IndexedPriorityQueue<Double> ipq = new IndexedPriorityQueue<>(len);
		ipq.insert(start, 0.0);
		
		for (int i = 0; i < len; i++) {
			
			int index = ipq.pollMinKeyIndex();
			visited[index] = true;
			
			//double minValue = ipq.pollMinValue();
			
			for (Node edge : adj.get(index)) {

				if (visited[edge.node]) continue;
				
				double newDist = dist[index] + edge.cost;
				if (newDist < dist[edge.node]) {
	
					dist[edge.node] = newDist;
					prevNode[edge.node] = index;
					
					if (!ipq.contains(edge.node)) ipq.insert(edge.node, newDist);
					else ipq.decrese(edge.node, newDist);
				}
			}
			System.out.println("\nvalue: " + Arrays.toString(dist));
		    System.out.println("parent: " + Arrays.toString(prevNode));
			if (index == end) return dist;
		}
		//System.out.println("\nvalue: " + Arrays.toString(dist));
	    //System.out.println("parent: " + Arrays.toString(prevNode));
		return dist;
	}
	
	@SuppressWarnings("unchecked")
	public class IndexedPriorityQueue<T extends Comparable<T>> {

		int LEN;
		int sz;
		private int[] pm, im;
		private Object[] values;
		
		public IndexedPriorityQueue(int len) {
			this.LEN = len;
			
			pm = new int[len];
			im = new int[len];;
			values = new Object[len];
			
			for (int i = 0; i < len; i++) {
				pm[i] = -1;
				im[i] = -1;
			}
		}
		
		public int size() {
			return sz; 
		}
		
		public boolean isEmpty() {
			return size() == 0;
		}
		
		public boolean contains(int ki) {
			keyInBoundOrThrow(ki);
			
			return pm[ki] != -1;
		}
		
		public int peekMinKeyIndex() {
			
			isNotEmptyOrThrow();
			return im[0];
		}
		
		public int pollMinKeyIndex() {
			
			int minki = peekMinKeyIndex();
			delete(minki);
			return minki;
		}
		
		public T peekMinValue() {
			
			isNotEmptyOrThrow();
			return (T) values[im[0]];
		}
		
		public T pollMinValue() {
			
			T minValue = peekMinValue();
			delete(peekMinKeyIndex());
			return minValue;
		}
		
		public void insert(int ki, T value) {

			if (contains(ki)) throw new IllegalArgumentException("index already exists; received: " + ki);
			
			valueNotNullOrThrow(value);
			
			pm[ki] = sz;
			im[sz] = ki;
			values[ki] = value;
			
			swim(sz++);
		}
		
		public T valueOf(int ki) {
			
			keyExistsOrThrow(ki);
			return (T) values[ki];
		}
		
		public T delete(int ki) {
			
			keyExistsOrThrow(ki);
			
			final int i = pm[ki];
			swap(i, --sz);
			
			sink(i);
			
			T value = (T) values[ki];
			values[ki] = null;
			pm[ki] = -1;
			im[sz] = -1;
			
			return value;
		}
		
		public T update(int ki, T value) {
	
			keyExistsOrThrow(ki);
			valueNotNullOrThrow(value);
			
			final int i = pm[ki];
			T oldValue = (T) values[ki];
			
			values[i] = value;
			
			sink(i);
			swim(i);
			
			return oldValue;
		}
		
		public void decrese(int ki, T value) {

			keyExistsOrThrow(ki);
			valueNotNullOrThrow(value);
			
			if (less(value, values[ki])) {
				
				values[ki] = value;
				swim(pm[ki]);
			}
		}
		
		public void increase(int ki, T value) {
			
			keyExistsOrThrow(ki);
			valueNotNullOrThrow(value);
			
			if (less(values[ki], value)) {
				
				values[ki] = value;
				sink(pm[ki]);
			}
		}
		
		private boolean less(int i, int j) {
			return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
		}
		
		private boolean less(Object obj1, Object obj2) {
			return ((Comparable<? super T>) obj1).compareTo((T) obj2) <0;
		}
		
		private void swap(int i, int j) {
			
			pm[im[i]] = j;
			pm[im[j]] = i;
			
			int temp = im[i];
			im[i] = im[j];
			im[j] = temp;
		}
		
		private void swim(int i) {
			int parent = (i - 1) / 2;
			
			while (i > 0 && less(i, parent)) {
				
				swap(i, parent);
				i = parent;
				parent = (i - 1) / 2;
			}
		}
		
		private void sink(int i) {
			
			int left, right, smallest = 0;
			
			while (true) {
				
				left = (2 * i) + 1;
				right = (2 * i ) + 2;
				smallest = left;
				
				if (right < sz && less(right, smallest)) smallest = right;
				
				if (left >= sz || less(i, smallest)) break;
				
				swap(smallest, i);
				i = smallest;
			}
		}
		
		private void isNotEmptyOrThrow() {
			if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		}
		
		private void valueNotNullOrThrow(Object value) {
			if (value == null) throw new IllegalArgumentException("value cannot be null"); 
		}
		
		private void keyExistsOrThrow(int ki) {
			if (!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
		}
		
		private void keyInBoundOrThrow(int ki) {
			if (ki < 0 || ki >= LEN) throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
		}
		
		public void show() {
			for (int i = 0; i < LEN; i++) System.out.print(pm[i] + ", ");
			
			System.out.println();
			
			for (int i = 0; i < LEN; i++) System.out.print(im[i] + ", ");
			
			System.out.println();
			
			for (int i = 0; i < LEN; i++) System.out.print(values[i] + ", ");
			
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
	
		int len = 5;
		
		DijkstraUsingIPQ dijk = new DijkstraUsingIPQ(len);
		
		List<List<Node>> adj = new ArrayList<List<Node>>();
		
		for (int i = 0; i < len; i++) {
			List<Node> node = new ArrayList<Node>();
			adj.add(node);
		}
		
		dijk.addEdge(0, 1, 4);
		dijk.addEdge(0, 2, 1);
		
		dijk.addEdge(1, 3, 1);
  
		dijk.addEdge(2, 1, 2);
		dijk.addEdge(2, 3, 5);
        
		dijk.addEdge(3, 4, 3);
		
		System.out.println(Arrays.toString(dijk.dijkstra(0, 4)));
	}
}
