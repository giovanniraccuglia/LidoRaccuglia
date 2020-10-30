package com.raccuglia.model;

import java.io.Serializable;

public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idUtente;
	private String nome;
	private String cognome;
	private String cellulare;
	private String email;
	private String password;
	private String ruolo;
	
	public Utente() {
		super();
	}
	
	public Utente(int idUtente, String nome, String cognome, String cellulare, String email, String password, String ruolo) {
		this.setIdUtente(idUtente);
		this.setNome(nome);
		this.setCognome(cognome);
		this.setCellulare(cellulare);
		this.setEmail(email);
		this.setPassword(password);
		this.setRuolo(ruolo);
	}
	
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	
	public int getIdUtente() {
		return idUtente;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	
	public String getCellulare() {
		return cellulare;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	public String getRuolo() {
		return ruolo;
	}
}
