package com.raccuglia.servlet.generale;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccuglia.DB.DBMS;
import com.raccuglia.model.Ordine;
import com.raccuglia.model.Postazione;
import com.raccuglia.model.Prenotazione;
import com.raccuglia.model.Prodotto;
import com.raccuglia.model.Utente;

/**
 * Servlet implementation class AreaUtenteServlet
 */
@WebServlet(name = "AreaUtenteServlet", urlPatterns = {"/areaUtente"})
public class AreaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente != null) {
			RequestDispatcher dispatcher;
			if(utente.getRuolo().equals("Admin")) {
				dispatcher = request.getRequestDispatcher("/WEB-INF/Amministratore/areaAdmin.jsp");
				dispatcher.forward(request, response);
			}else if(utente.getRuolo().equals("Bagnino")) {
				dispatcher = request.getRequestDispatcher("/WEB-INF/Bagnino/areaBagnino.jsp");
				dispatcher.forward(request, response);
			}else if(utente.getRuolo().equals("Cliente")) {
				dispatcher = request.getRequestDispatcher("/WEB-INF/Cliente/areaCliente.jsp");
				dispatcher.forward(request, response);
			}else if(utente.getRuolo().equals("Ristorazione")) {
				dispatcher = request.getRequestDispatcher("/WEB-INF/Ristorazione/areaRistorazione.jsp");
				dispatcher.forward(request, response);
			}else {
				response.sendError(400);
			}
		}else {
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente != null) {
			if(utente.getRuolo().equals("Admin")) {
				visualizzaDipendentiProdotti(request, response);
			}else if(utente.getRuolo().equals("Bagnino")) {
				visualizzaSpiaggia(request, response);
			}else if(utente.getRuolo().equals("Cliente")) {
				visualizzaPrenotazioniOrdini(request, response);
			}else if(utente.getRuolo().equals("Ristorazione")) {
				visualizzaOrdini(request, response);
			}else {
				response.sendError(400);
			}
		}else {
			response.sendRedirect(request.getContextPath());
		}
	}
	
	private void visualizzaDipendentiProdotti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Utente> listaDipendenti = DBMS.getDipendenti();
			List<Prodotto> listaProdotti = DBMS.getProdotti();
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			pr.write("[" + mapper.writeValueAsString(listaDipendenti) + "," + mapper.writeValueAsString(listaProdotti) + "]");
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
	
	private void visualizzaPrenotazioniOrdini(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idUtente = ((Utente) request.getSession().getAttribute("utente")).getIdUtente();
			List<Prenotazione> listaPrenotazioni = DBMS.getPrenotazioni(idUtente);
			List<Ordine> listaOrdini = DBMS.getOrdiniFromId(idUtente);
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			pr.write("[" + mapper.writeValueAsString(listaPrenotazioni) + "," + mapper.writeValueAsString(listaOrdini) + "]");
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
	
	private void visualizzaOrdini(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Ordine> listaOrdini = DBMS.getOrdini();
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			pr.write(mapper.writeValueAsString(listaOrdini));
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
	
	private void visualizzaSpiaggia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Date dataPrenotazione = Date.valueOf(request.getParameter("dataPrenotazione"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date(System.currentTimeMillis());
			if(dataPrenotazione != null && (dataPrenotazione.compareTo(Date.valueOf(sdf.format(today))) == 0 || dataPrenotazione.after(Date.valueOf(sdf.format(today))))) {
				List<Postazione> postazioniPrenotate = DBMS.getPostazioniPrenotate(dataPrenotazione);
				List<Postazione> postazioni = DBMS.getPostazioni();
				List<Utente> utentiPrenotati = new ArrayList<>();
				for(int i = 0; i < postazioniPrenotate.size(); i++) {
					utentiPrenotati.add(DBMS.getUtenteFromPostazione(dataPrenotazione, postazioniPrenotate.get(i).getIdPostazione()));
				}
				PrintWriter pr = response.getWriter();
				response.setContentType("application/json");
				ObjectMapper mapper = new ObjectMapper();
				pr.write("[" + mapper.writeValueAsString(postazioniPrenotate) + "," + mapper.writeValueAsString(utentiPrenotati) + "," + mapper.writeValueAsString(postazioni) + "]");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
}
