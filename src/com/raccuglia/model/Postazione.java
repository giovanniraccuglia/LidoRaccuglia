package com.raccuglia.model;

import java.io.Serializable;

public class Postazione implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idPostazione;
	private double prezzo;
	
	public Postazione() {
		super();
	}

	public Postazione(int idPostazione, double prezzo) {
		this.setIdPostazione(idPostazione);
		this.setPrezzo(prezzo);
	}
	
	public int getIdPostazione() {
		return idPostazione;
	}

	public void setIdPostazione(int idPostazione) {
		this.idPostazione = idPostazione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
}