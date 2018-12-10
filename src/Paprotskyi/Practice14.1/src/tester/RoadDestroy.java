package tester;

import helping.Edge;
import helping.EdgeWeightedGraph;
import helping.MinPQ;

import java.io.File;

import ua.princeton.lib.In;

public class RoadDestroy {
	private EdgeWeightedGraph graph;
	private int V;

	public RoadDestroy(File file) {
		In in = new In(file);
		int numberOfTests = in.readInt();
		while (numberOfTests != 0) {
			V = in.readInt();
			int E = in.readInt();
			graph = new EdgeWeightedGraph(V);
			for (int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				double weight = in.readDouble();
				graph.addEdge(new Edge(v, w, weight));

			}

			MinPQ<Double> pq = new MinPQ<Double>();
			for (Edge w : graph.edges()) {
				double edgeWeight = 0;
				int firstVertex = w.either();
				int secondVertex = w.other(firstVertex);

				double firstVertexWeight = w.weight();
				double secondVertexWeight = w.weight();
				for (Edge v : graph.edges()) {
					if ((v.either() == firstVertex && v.other(v.either()) != secondVertex)
							|| (v.other(v.either()) == firstVertex)
							&& v.either() != secondVertex) {
						edgeWeight += v.weight();
						firstVertexWeight += v.weight();
					} else if ((v.either() == secondVertex && v.other(v
							.either()) != firstVertex)
							|| (v.other(v.either()) == secondVertex)
							&& v.either() != firstVertex) {
						edgeWeight += v.weight();
						secondVertexWeight += v.weight();
					}
				}
				pq.insert(firstVertexWeight);
				pq.insert(secondVertexWeight);
				pq.insert(edgeWeight);
			}
			System.out.println(pq.delMin());
			numberOfTests--;
		}
	}

	public static void main(String[] args) {
		RoadDestroy test = new RoadDestroy(new File("road.txt"));

	}
}
