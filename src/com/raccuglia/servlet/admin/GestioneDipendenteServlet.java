package com.raccuglia.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raccuglia.DB.DBMS;
import com.raccuglia.model.Utente;
import com.raccuglia.utils.InvioEmail;
import com.raccuglia.utils.LidoUtil;

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
			String id = request.getParameter("id");
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			if(LidoUtil.checkInput(id)) {
				int idUtente = Integer.parseInt(id);
				DBMS.deleteDipendente(idUtente);
				status = "{\"CANCELLAZIONE\" : \"true\", \"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Account eliminato correttamente.\"}";
			}else {
				status = "{\"CANCELLAZIONE\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Impossibile eliminare l'account dell'utente selezionato.\"}";
			}
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
			if(LidoUtil.checkInput(nome) && LidoUtil.checkInput(cognome) && LidoUtil.checkInput(cellulare) && LidoUtil.checkInput(email) && LidoUtil.checkInput(ruolo)) {
				if(DBMS.verificaUtente(email, cellulare)) {
					status = "{\"INSERIMENTO\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Account gi&agrave; presente.\"}";
				}else {
					String password = LidoUtil.genPassword(8);
					String oggetto = "ACCOUNT AZIENDALE MARRAKECH BEACH";
					String messaggio = "Benvenuto nella famiglia di Marrakech Beach, queste sono le tue credenziali per accedere al tuo account aziendale. Email: '" + email + "' - Password: '" + password + "'. Per ragioni di sicurezza è consigliabile cambiare la password dopo l'accesso.";
					DBMS.registrazioneDipendente(nome, cognome, cellulare, email, password, ruolo);
					Thread th = new Thread(() -> {
						try {
							InvioEmail.sendEmail(email, messaggio, oggetto);
						}catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} 
					});
					th.start();
					status = "{\"INSERIMENTO\" : \"true\", \"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Account registrato correttamente.\"}";
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
}
