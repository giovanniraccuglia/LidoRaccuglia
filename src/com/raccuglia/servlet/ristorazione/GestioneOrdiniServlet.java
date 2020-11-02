package com.raccuglia.servlet.ristorazione;

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
 * Servlet implementation class GestioneOrdiniServlet
 */
@WebServlet(name = "GestioneOrdiniServlet", urlPatterns = {"/gestioneOrdini"})
public class GestioneOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente == null) {
			response.sendRedirect(request.getContextPath());
		}else if(utente.getRuolo().equals("Ristorazione")) {
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
		}else if(utente.getRuolo().equals("Ristorazione")) {
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			int idOrdine = Integer.parseInt(request.getParameter("id"));
			Boolean pronto = Boolean.parseBoolean(request.getParameter("pronto"));
			Boolean ritirato = Boolean.parseBoolean(request.getParameter("ritirato"));
			if(pronto != null && pronto.equals(true) && ritirato != null && ritirato.equals(false)) {
				DBMS.updateOrdine(idOrdine, pronto, ritirato);
				status = "{\"CHECK\" : \"true\"}";
			}else if(pronto != null && pronto.equals(true) && ritirato != null && ritirato.equals(true)) {
				DBMS.updateOrdine(idOrdine, pronto, ritirato);
				status = "{\"CHECK\" : \"true\"}";
			}else {
				status = "{\"CHECK\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Impossibile effettuare l\'operazione.\"}";
			}
			pr.write(status);
		}else {
			response.sendError(401);
		}
	}
}