package com.raccuglia.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raccuglia.DB.DBMS;
import com.raccuglia.model.Utente;
import com.raccuglia.utils.InvioEmail;

/**
 * Servlet implementation class GestioneDipendenteServlet
 */
@WebServlet(name = "GestioneDipendenteServlet", urlPatterns = {"/gestioneDipendente"})
public class GestioneDipendenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente == null) {
			response.sendRedirect(request.getContextPath());
		}else if(utente.getRuolo().equals("Admin")) {
			response.sendRedirect(request.getContextPath() + "/areaUtente");
		}else {
			response.sendError(401);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente == null) {
			response.sendRedirect(request.getContextPath());
		}else if(utente.getRuolo().equals("Admin")) {
			registraDipendente(request, response);
		}else {
			response.sendError(401);
		}
	}
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente == null) {
			response.sendRedirect(request.getContextPath());
		}else if(utente.getRuolo().equals("Admin")) {
			cancellaDipendente(request, response);
		}else {
			response.sendError(401);
		}
	}
	
	private void cancellaDipendente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idUtente = Integer.parseInt(request.getParameter("id"));
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			DBMS.deleteDipendente(idUtente);
			String status = "{\"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Account eliminato correttamente.\"}";
			pr.write(status);
		}catch(Exception e) {
			response.sendError(400);
		}
	}
	
	private void registraDipendente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String cellulare = request.getParameter("cellulare");
			String email = request.getParameter("email").toLowerCase();
			String ruolo = request.getParameter("ruolo");
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			if(nome != null && cognome != null && cellulare != null && email != null && ruolo != null) {
				if(DBMS.verificaUtente(email, cellulare)) {
					status = "{\"INSERIMENTO\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Account già presente.\"}";
				}else {
					String password = genPassword(5);
					String oggetto = "ACCOUNT AZIENDALE";
					String messaggio = "Benvenuto nella famiglia di Marrakech Beach, queste sono le tue credenziali per accedere al tuo account aziendale. Email: '" + email + "' - Password: '" + password + "'. Per ragioni di sicurezza è consigliabile cambiare la password dopo l'accesso.";
					DBMS.registrazioneDipendente(nome, cognome, cellulare, email, password, ruolo);
					InvioEmail.sendEmail(email, messaggio, oggetto);
					int idDipendente = DBMS.getUtente(email).getIdUtente();
					status = "{\"INSERIMENTO\" : \"true\", \"ID\" : \"" + idDipendente + "\", \"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Account registrato correttamente.\"}";
				}
			}else {
				status = "{\"INSERIMENTO\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Inserire i dati nei relativi campi.\"}";
			}
			pr.write(status);
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
	
	private String genPassword(int len) { //Soluzione provvisoria
    	final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    	final String chars1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	final String chars2 = "abcdefghijklmnopqrstuvwxyz";
    	final String chars3 = "0123456789";
    	SecureRandom random = new SecureRandom();
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < len; i++) {
    		int randomIndex = random.nextInt(chars.length());
    		sb.append(chars.charAt(randomIndex));
    	}
    	int randomIndex1 = random.nextInt(chars1.length());
    	int randomIndex2 = random.nextInt(chars2.length());
    	int randomIndex3 = random.nextInt(chars3.length());
		sb.append(chars1.charAt(randomIndex1));
		sb.append(chars2.charAt(randomIndex2));
		sb.append(chars3.charAt(randomIndex3));
    	return sb.toString();
    }
}
