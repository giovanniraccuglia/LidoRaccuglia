package com.raccuglia.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Prenotazione implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idPrenotazione;
	private Timestamp data;
	private Date dataPrenotazione;
	private double totale;
	private boolean pagato;
	private boolean rimborsato;
	private List<Postazione> postazioni;
	
	public Prenotazione() {
		super();
	}
	
	public Prenotazione(int idPrenotazione, Timestamp data, Date dataPrenotazione, double totale, boolean pagato, boolean rimborsato, List<Postazione> postazioni) {
		this.setIdPrenotazione(idPrenotazione);
		this.setData(data);
		this.setDataPrenotazione(dataPrenotazione);
		this.setTotale(totale);
		this.setPagato(pagato);
		this.setRimborsato(rimborsato);
		this.setPostazioni(postazioni);
	}

	public int getIdPrenotazione() {
		return idPrenotazione;
	}

	public void setIdPrenotazione(int idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public Date getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(Date dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public boolean isPagato() {
		return pagato;
	}

	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}

	public boolean isRimborsato() {
		return rimborsato;
	}

	public void setRimborsato(boolean rimborsato) {
		this.rimborsato = rimborsato;
	}

	public List<Postazione> getPostazioni() {
		return postazioni;
	}

	public void setPostazioni(List<Postazione> postazioni) {
		this.postazioni = postazioni;
	}
}
