package com.domain;

import java.util.ArrayList;
import java.util.Date;

public class HojaDespacho {
	 
	private long id;
    private int Ntarimas;
	private ArrayList<Tarima> tarimas;
    private String validadoPor;
    private String fichaEmpleado;
    private String pais;
    private String fecha;
    private String horaInicio;
    private String horaFinal;
    private String despacho;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNtarimas() {
		return Ntarimas;
	}
	public void setNtarimas(int ntarimas) {
		Ntarimas = ntarimas;
	}
	public ArrayList<Tarima> getTarimas() {
		return tarimas;
	}
	public void setTarimas(ArrayList<Tarima> tarimas) {
		this.tarimas = tarimas;
	}
	public String getValidadoPor() {
		return validadoPor;
	}
	public void setValidadoPor(String validadoPor) {
		this.validadoPor = validadoPor;
	}
	public String getFichaEmpleado() {
		return fichaEmpleado;
	}
	public void setFichaEmpleado(String fichaEmpleado) {
		this.fichaEmpleado = fichaEmpleado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}
	public String getDespacho() {
		return despacho;
	}
	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}
    
    
    
    
}
