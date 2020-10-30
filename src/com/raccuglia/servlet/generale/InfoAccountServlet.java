package com.raccuglia.servlet.generale;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccuglia.DB.DBMS;
import com.raccuglia.model.Utente;

/**
 * Servlet implementation class InfoAccountServlet
 */
@WebServlet(name = "InfoAccountServlet", urlPatterns = {"/infoAccount"})
public class InfoAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente == null) {
			response.sendRedirect(request.getContextPath());
		}else {
			response.sendRedirect(request.getContextPath() + "/areaUtente");
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
		}else {
			modificaUtente(request, response);
		}
	}
	
	private void modificaUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Utente currentUtente = (Utente) request.getSession().getAttribute("utente");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String cellulare = request.getParameter("cellulare");
			String email = request.getParameter("email").toLowerCase();
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			String status;
			if(nome != null && cognome != null && cellulare != null && email != null) {
				Utente newUtente = new Utente(currentUtente.getIdUtente(), nome, cognome, cellulare, email, currentUtente.getPassword(), currentUtente.getRuolo());
				if(equalsUtente(currentUtente, newUtente) != true) {
					if((currentUtente.getEmail().equals(email) != true) && (currentUtente.getCellulare().equals(cellulare) != true)) {
						if(DBMS.verificaUtente(email, cellulare)) {
							status = "{\"ACCOUNT\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"E-mail e Cellulare già presenti all'interno del sistema.\"}";
						}else {
							DBMS.updateUtente(newUtente.getIdUtente(), newUtente.getNome(), newUtente.getCognome(), newUtente.getCellulare(), newUtente.getEmail());
							request.getSession().setAttribute("utente", newUtente);
							status = mapper.writeValueAsString(newUtente);
						}
					}else if(currentUtente.getEmail().equals(email) != true) {
						if(DBMS.verificaUtente(email, "")) {
							status = "{\"ACCOUNT\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"E-mail già presente all'interno del sistema.\"}";
						}else {
							DBMS.updateUtente(newUtente.getIdUtente(), newUtente.getNome(), newUtente.getCognome(), newUtente.getCellulare(), newUtente.getEmail());
							request.getSession().setAttribute("utente", newUtente);
							status = mapper.writeValueAsString(newUtente);
						}
					}else if(currentUtente.getCellulare().equals(cellulare) != true) {
						if(DBMS.verificaUtente("", cellulare)) {
							status = "{\"ACCOUNT\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Cellulare già presente all'interno del sistema.\"}";
						}else {
							DBMS.updateUtente(newUtente.getIdUtente(), newUtente.getNome(), newUtente.getCognome(), newUtente.getCellulare(), newUtente.getEmail());
							request.getSession().setAttribute("utente", newUtente);
							status = mapper.writeValueAsString(newUtente);
						}
					}else {
						DBMS.updateUtente(newUtente.getIdUtente(), newUtente.getNome(), newUtente.getCognome(), newUtente.getCellulare(), newUtente.getEmail());
						request.getSession().setAttribute("utente", newUtente);
						status = mapper.writeValueAsString(newUtente);
					}
				}else {
					status = "{\"ACCOUNT\" : \"equal\"}";
				}
			}else {
				status = "{\"ACCOUNT\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Inserire i dati nei relativi campi.\"}";
			}
			pr.write(status);
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
	
	private boolean equalsUtente(Utente u1, Utente u2) {
		if(u1.getNome().equals(u2.getNome()) && u1.getCognome().equals(u2.getCognome()) && u1.getCellulare().equals(u2.getCellulare()) && u1.getEmail().equals(u2.getEmail()))
			return true;
		else
			return false;
	}
}
