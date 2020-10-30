package com.raccuglia.servlet.generale;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccuglia.DB.DBMS;
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
			}else if(utente.getRuolo().equals("Ristorazione")) {
				dispatcher = request.getRequestDispatcher("/WEB-INF/Ristorazione/areaRistorazione.jsp");
				dispatcher.forward(request, response);
			}else if(utente.getRuolo().equals("Cliente")) {
				dispatcher = request.getRequestDispatcher("/WEB-INF/Cliente/areaCliente.jsp");
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
				visualizzaProdDip(request, response);
			}else if(utente.getRuolo().equals("Bagnino")) {
				visualizzaSpiaggia(request, response);
			}else if(utente.getRuolo().equals("Cliente")) {
				visualizzaPrenotazioni(request, response);
			}
		}else {
			response.sendRedirect(request.getContextPath());
		}
	}
	
	private void visualizzaProdDip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Utente> listaDipendenti = DBMS.getDipendenti();
			List<Prodotto> listaProdotti = DBMS.getListaProdotti();
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			pr.write("[" + mapper.writeValueAsString(listaDipendenti) + "," + mapper.writeValueAsString(listaProdotti) + "]");
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
	
	private void visualizzaPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idUtente = ((Utente) request.getSession().getAttribute("utente")).getIdUtente();
			List<Prenotazione> listaPrenotazioni = DBMS.getPrenotazioni(idUtente);
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			pr.write(mapper.writeValueAsString(listaPrenotazioni));
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
				List<Postazione> postazioniDisabilitate = DBMS.getPostazioniDisabilitate();
				List<Postazione> postazioniPrenotate = DBMS.getPostazioniPrenotate(dataPrenotazione);
				PrintWriter pr = response.getWriter();
				response.setContentType("application/json");
				ObjectMapper mapper = new ObjectMapper();
				pr.write("[" + mapper.writeValueAsString(postazioniDisabilitate) + "," + mapper.writeValueAsString(postazioniPrenotate) + "]");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
}
