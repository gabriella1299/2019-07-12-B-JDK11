package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {

	private FoodDao dao;
	private Graph<Food,DefaultWeightedEdge> grafo;
	private Map<Integer,Food> map;
	
	
	public Model() {
		this.dao=new FoodDao();
	}
	public void creaGrafo(int p) {
		this.grafo=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		this.map=new HashMap<>();
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(p, map));
		
		List<Arco> archi=this.dao.getArchi(map);
		for(Arco a:archi) {
			if(this.grafo.containsVertex(a.getF1()) && this.grafo.containsVertex(a.getF2())) {
				if(a.getPeso()>0) {
					//arco da f1 a f2
					Graphs.addEdgeWithVertices(this.grafo, a.getF1(), a.getF2(), a.getPeso());
				}
				else if(a.getPeso()<0) {
					//arco da f2 a f1
					Graphs.addEdgeWithVertices(this.grafo, a.getF2(), a.getF1(), a.getPeso());
				}
			}
			
		}
		
	}
	
	public List<Successivi> getDifferenzaMin(Food f){
		List<Successivi> c=new ArrayList<>();
		List<Food> vicini=Graphs.successorListOf(this.grafo, f);
		List<Successivi> result=new ArrayList<>();
		
		for(Food ff:vicini) {
			double peso=this.grafo.getEdgeWeight(this.grafo.getEdge(f, ff));
			c.add(new Successivi(ff, peso));
		}
		Collections.sort(c);
		int i=0;
		for(Successivi cc:c) {
			if(i<5) {
				result.add(cc);
				i++;
			}
		}
		return result;
	}
	
	public List<Food> getVertici(){
		List<Food> vertici=new ArrayList<>(this.grafo.vertexSet());
		return vertici;
	}
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}

}
