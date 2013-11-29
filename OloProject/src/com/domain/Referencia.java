package com.domain;

import java.util.ArrayList;

public class Referencia {
	private long id;
	private String ref;
	private int bultos;
	private int empaque;
	private int unidades;
	private double peso;
	private double pesototal;
	private int numeroTarimas;
	
 
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public int getNumeroTarimas() {
		return numeroTarimas;
	}
	public void setNumeroTarimas(int numeroTarimas) {
		this.numeroTarimas = numeroTarimas;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getBultos() {
		return bultos;
	}
	public void setBultos(int bultos) {
		this.bultos = bultos;
	}
	public int getEmpaque() {
		return empaque;
	}
	public void setEmpaque(int empaque) {
		this.empaque = empaque;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getPesototal() {
		return pesototal;
	}
	public void setPesototal(double pesototal) {
		this.pesototal = pesototal;
	}
	
	

}
