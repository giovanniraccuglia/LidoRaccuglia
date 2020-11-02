package com.raccuglia.servlet.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raccuglia.DB.DBMS;
import com.raccuglia.model.Postazione;
import com.raccuglia.model.Prenotazione;
import com.raccuglia.model.Utente;
import com.raccuglia.utils.InvioEmail;

/**
 * Servlet implementation class GestionePrenotazioneServlet
 */
@WebServlet(name = "GestionePrenotazioneServlet", urlPatterns = {"/gestionePrenotazione"})
public class GestionePrenotazioneServlet extends HttpServlet {
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
			prenota(request, response);
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
		}else if(utente.getRuolo().equals("Cliente")) {
			cancellaPrenotazione(request, response);
		}else {
			response.sendError(401);
		}
	}
	
	private void cancellaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idUtente = ((Utente) request.getSession().getAttribute("utente")).getIdUtente();
			int idPrenotazione = Integer.parseInt(request.getParameter("id"));
			Prenotazione prenotazione = DBMS.getPrenotazione(idUtente, idPrenotazione);
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			Date dataPrenotazione = prenotazione.getDataPrenotazione();
			Date tomorrow = new Date(System.currentTimeMillis() + 86400000);
			if(prenotazione.isRimborsato()) {
				status = "{\"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Prenotazione gi&agrave; annullata.\"}";
			}else if(dataPrenotazione.before(tomorrow)) {
				status = "{\"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"La prenotazione non pu&ograve; pi&ugrave; essere annullata.\"}";
			}else {
				DBMS.deletePrenotazione(idUtente, idPrenotazione);
				status = "{\"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Prenotazione annullata correttamente.\"}";
			}
			pr.write(status);
		}catch(Exception e) {
			response.sendError(400);
		}
	}
	
	private void prenota(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Date dataPrenotazione = Date.valueOf(request.getParameter("dataPrenotazione"));
			String postazioni = request.getParameter("postazioni");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date(System.currentTimeMillis());
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			if(dataPrenotazione != null && postazioni != null && postazioni.length() > 0 && (dataPrenotazione.compareTo(Date.valueOf(sdf.format(today))) == 0 || dataPrenotazione.after(Date.valueOf(sdf.format(today))))) {
				String[] idPostazioni = postazioni.split(",");
				List<Postazione> postazioniSelezionate = new ArrayList<>();
				for(int i = 0; i < idPostazioni.length; i++) {
					postazioniSelezionate.add(DBMS.getPostazioneFromId(Integer.parseInt(idPostazioni[i])));
				}
				List<Postazione> postazioniNonPrenotabili = DBMS.getPostazioniPrenotate(dataPrenotazione);
				if(checkPostazioni(postazioniSelezionate, postazioniNonPrenotabili) == false) {
					int idUtente = ((Utente) request.getSession().getAttribute("utente")).getIdUtente();
					String email = ((Utente) request.getSession().getAttribute("utente")).getEmail();
					DBMS.inserisciPrenotazione(dataPrenotazione, getTotale(postazioniSelezionate), idUtente, postazioniSelezionate);
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					String oggetto = "MARRAKECH BEACH";
					String messaggio = "Gentile Cliente, La informiamo che la sua prenotazione per la data: " + sdf1.format(dataPrenotazione) + " è avvenuta con successo. Le auguriamo una buona permanenza in struttura.";
					InvioEmail.sendEmail(email, messaggio, oggetto);
					status = "{\"PRENOTATO\" : \"true\", \"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Pronotazione effettuata correttamente.\"}";
				}else {
					status = "{\"PRENOTATO\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Una o pi&ugrave; postazioni sono gi&agrave; prenotate.\"}";
				}
			}else {
				status = "{\"PRENOTATO\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Inserire una data valida e selezionare una o pi&ugrave; postazioni.\"}";
			}
			pr.write(status);
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
	
	private double getTotale(List<Postazione> l) {
		double totale = 0;
		for(int i = 0; i < l.size(); i++) {
			totale += l.get(i).getPrezzo();
		}
		return totale;
	}
	
	private boolean checkPostazioni(List<Postazione> l1, List<Postazione> l2) {
		for(int i = 0; i < l1.size(); i++) {
			if(l2.contains(l1.get(i)))
				return true;
		}
		return false;
	}
}
