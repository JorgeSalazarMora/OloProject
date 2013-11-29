package com.domain;

import java.util.ArrayList;

public class Tarima {
	private long id;
    private int numeroTarima;
    private String tarimaOrigen;
    private String expediente;
    private int nRefetencias;
	private ArrayList<Referencia> referencias;

    //hacer constructior con la lista de referencias

	
	
	public long getId() {
		return id;
	}
	public int getNumeroTarima() {
		return numeroTarima;
	}
	public void setNumeroTarima(int numeroTarima) {
		this.numeroTarima = numeroTarima;
	}
	public String getExpediente() {
		return expediente;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getTarimaOrigen() {
		return tarimaOrigen;
	}
	public void setTarimaOrigen(String tarimaOrigen) {
		this.tarimaOrigen = tarimaOrigen;
	}
	public ArrayList<Referencia> getReferencias() {
		return referencias;
	}
	public void setReferencias(ArrayList<Referencia> referencias) {
		this.referencias = referencias;
	}
	
    public int getnRefetencias() {
			return nRefetencias;
	}
	public void setnRefetencias(int nRefetencias) {
			this.nRefetencias = nRefetencias;
		}

	}
    
    
