package com.raccuglia.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Ordine implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idOrdine;
	private Timestamp data;
	private double totale;
	private boolean preparato;
	private boolean ritirato;
	private boolean pagato;
	private List<Prodotto> prodotti;
	private List<Integer> quantita;
	
	public Ordine() {
		super();
	}
	
	public Ordine(int idOrdine, Timestamp data, double totale, boolean preparato, boolean ritirato, boolean pagato, List<Prodotto> prodotti, List<Integer> quantita) {
		this.setIdOrdine(idOrdine);
		this.setData(data);
		this.setTotale(totale);
		this.setPreparato(preparato);
		this.setRitirato(ritirato);
		this.setPagato(pagato);
		this.setProdotti(prodotti);
		this.setQuantita(quantita);
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public boolean isPreparato() {
		return preparato;
	}

	public void setPreparato(boolean preparato) {
		this.preparato = preparato;
	}

	public boolean isRitirato() {
		return ritirato;
	}

	public void setRitirato(boolean ritirato) {
		this.ritirato = ritirato;
	}

	public boolean isPagato() {
		return pagato;
	}

	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public List<Integer> getQuantita() {
		return quantita;
	}

	public void setQuantita(List<Integer> quantita) {
		this.quantita = quantita;
	}
}
