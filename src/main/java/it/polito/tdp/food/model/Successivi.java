package it.polito.tdp.food.model;

public class Successivi implements Comparable<Successivi>{
	Food f;
	Double peso;
	
	public Successivi(Food f, Double peso) {
		super();
		this.f = f;
		this.peso = peso;
	}
	public Food getF() {
		return f;
	}
	public void setF(Food f) {
		this.f = f;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return f + " " + peso + "";
	}
	
	@Override
	public int compareTo(Successivi o) {
		// TODO Auto-generated method stub
		return Double.compare(this.getPeso(), o.getPeso());
	}
}
