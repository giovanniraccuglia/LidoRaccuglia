package com.raccuglia.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raccuglia.DB.DBMS;
import com.raccuglia.model.Utente;

/**
 * Servlet implementation class GestioneProdotto
 */
@WebServlet(name = "GestioneProdottoServlet", urlPatterns = {"/gestioneProdotto"})
public class GestioneProdottoServlet extends HttpServlet {
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
			inserisciProdotto(request, response);
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
			cancellaProdotto(request, response);
		}else {
			response.sendError(401);
		}
	}
	
	private void cancellaProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idProdotto = Integer.parseInt(request.getParameter("id"));
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			DBMS.deleteProdotto(idProdotto);
			status = "{\"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Prodotto eliminato correttamente.\"}";
			pr.write(status);
		}catch(Exception e) {
			response.sendError(400);
		}
	}
	
	private void inserisciProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome = request.getParameter("nome");
			String descrizione = request.getParameter("descrizione");
			Double prezzo = Double.parseDouble(request.getParameter("prezzo"));
			String categoria = request.getParameter("categoria");
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			if(nome != null && descrizione != null && prezzo != null && categoria != null) {
				if(DBMS.verificaProdotto(nome)) {
					status = "{\"AGGIUNTO\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Prodotto già presente.\"}";
				}else {
					DBMS.aggiungiProdotto(nome, descrizione, prezzo, categoria);
					int idProdotto = DBMS.getProdotto(nome).getIdProdotto();
					status = "{\"AGGIUNTO\" : \"true\", \"ID\" : \"" + idProdotto + "\", \"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Prodotto inserito correttamente.\"}";
				}
			}else {
				status = "{\"AGGIUNTO\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Inserire i dati nei relativi campi.\"}";
			}
			pr.write(status);
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
}
