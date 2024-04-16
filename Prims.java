package com.sai.designPatterns.priorityQueue;

import com.sai.designPatterns.Edge;

import java.util.*;

public class Prims {
    public static List<List<Edge>> prim(List<Edge> edges, int n) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        List<List<Edge>> mst = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mst.add(new ArrayList<>());
        }

        visited[0] = true;
//        PriorityQueue in Java is a min-heap, meaning the element with the minimum value
//        (in this case, the edge with the minimum weight) will be at the front of the queue.
        for (Edge edge : edges) {
            if (edge.getSrc() == 0) {
                pq.add(edge);
            }
        }
//        add all edges with src as 0

        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();
            int u = minEdge.getDest();
            if (!visited[u]) {
                visited[u] = true;
                Edge temp = new Edge(minEdge.getDest(), minEdge.getSrc(), minEdge.getWeight());
                if (minEdge.getSrc() != 0) {
                    mst.get(minEdge.getDest()).add(temp);
                } else {
                    mst.get(0).add(temp);
                }
                for (Edge edge : edges) {
                    if (edge.getSrc() == u && !visited[edge.getDest()]) {
                        pq.add(edge);
                    }
                }
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 7, 8));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 7, 11));
        edges.add(new Edge(2, 3, 7));
        edges.add(new Edge(2, 5, 4));
        edges.add(new Edge(2, 8, 2));
        edges.add(new Edge(3, 4, 9));
        edges.add(new Edge(3, 5, 14));
        edges.add(new Edge(4, 5, 10));
        edges.add(new Edge(5, 6, 2));
        edges.add(new Edge(6, 7, 1));
        edges.add(new Edge(6, 8, 6));
        edges.add(new Edge(7, 8, 7));

        List<List<Edge>> mst = prim(edges, 9);

        System.out.println("Minimum Spanning Tree:");
        for (int i = 0; i < mst.size(); i++) {
            for (Edge edge : mst.get(i)) {
                System.out.println(edge.getSrc() + " - " + edge.getDest() + " : " + edge.getWeight());
            }
        }
    }
}
