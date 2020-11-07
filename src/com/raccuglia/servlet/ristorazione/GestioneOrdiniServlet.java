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
import com.raccuglia.utils.LidoUtil;

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
			String id = request.getParameter("id");
			String p = request.getParameter("pronto");
			String r = request.getParameter("ritirato");
			if(LidoUtil.checkInput(id) && LidoUtil.checkInput(p) && LidoUtil.checkInput(r)) {
				int idOrdine = Integer.parseInt(id);
				Boolean pronto = Boolean.parseBoolean(p);
				Boolean ritirato = Boolean.parseBoolean(r);
				if(pronto.equals(true) && ritirato.equals(false)) {
					DBMS.updateOrdine(idOrdine, pronto, ritirato);
					status = "{\"CHECK\" : \"true\"}";
				}else if(pronto.equals(true) && ritirato.equals(true)) {
					DBMS.updateOrdine(idOrdine, pronto, ritirato);
					status = "{\"CHECK\" : \"true\"}";
				}else {
					status = "{\"CHECK\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Impossibile effettuare l\'operazione.\"}";
				}
			}else {
				status = "{\"CHECK\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Impossibile effettuare l\'operazione.\"}";
			}
			pr.write(status);
		}else {
			response.sendError(401);
		}
	}
}