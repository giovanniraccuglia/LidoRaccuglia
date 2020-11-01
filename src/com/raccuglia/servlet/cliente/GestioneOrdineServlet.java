package com.raccuglia.servlet.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raccuglia.DB.DBMS;
import com.raccuglia.model.Prodotto;
import com.raccuglia.model.Utente;
import com.raccuglia.utils.InvioEmail;

/**
 * Servlet implementation class GestioneOrdineServlet
 */
@WebServlet(name = "GestioneOrdineServlet", urlPatterns = {"/gestioneOrdine"})
public class GestioneOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente == null) {
			response.sendRedirect(request.getContextPath());
		}else if(utente.getRuolo().equals("Cliente")) {
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
		}else if(utente.getRuolo().equals("Cliente")) {
			ordina(request, response);
		}else {
			response.sendError(401);
		}
	}
	
	private void ordina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String prodotti = request.getParameter("prodotti");
			String quantita = request.getParameter("quantita");
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			if(prodotti != null && prodotti.length() > 0 && quantita != null && quantita.length() > 0) {
				String[] idProdotti = prodotti.split(",");
				String[] quantitaProdotti = quantita.split(",");
				if(idProdotti.length == quantitaProdotti.length) {
					List<Prodotto> prodottiSelezionati = new ArrayList<>();
					List<Integer> quantitaProdottiSelezionati = new ArrayList<>();
					for(int i = 0; i < idProdotti.length; i++) {
						prodottiSelezionati.add(DBMS.getProdottoFromId(Integer.parseInt(idProdotti[i])));
						quantitaProdottiSelezionati.add(Integer.parseInt(quantitaProdotti[i]));
					}
					int idUtente = ((Utente) request.getSession().getAttribute("utente")).getIdUtente();
					String email = ((Utente) request.getSession().getAttribute("utente")).getEmail();
					DBMS.inserisciOrdine(getTotale(prodottiSelezionati, quantitaProdottiSelezionati), idUtente, prodottiSelezionati, quantitaProdottiSelezionati);
					String oggetto = "MARRAKECH BEACH";
					String messaggio = "Gentile Cliente, La informiamo che il suo ordine è andato a buon fine. Le ricordiamo che nell'Area Clienti potrà visionare lo status del suo ordine.";
					InvioEmail.sendEmail(email, messaggio, oggetto);
					status = "{\"ORDINATO\" : \"true\", \"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Ordine effettuato correttamente.\"}";
				}else {
					status = "{\"ORDINATO\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Selezionare i prodotti correttamente.\"}";
				}
			}else {
				status = "{\"ORDINATO\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Selezionare uno o pi&ugrave; prodotti.\"}";
			}
			pr.write(status);
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
	
	private double getTotale(List<Prodotto> l, List<Integer> q) {
		double totale = 0;
		for(int i = 0; i < l.size(); i++) {
			totale += (l.get(i).getPrezzo() * q.get(i));
		}
		return totale;
	}
}
