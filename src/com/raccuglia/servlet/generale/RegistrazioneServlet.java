package com.raccuglia.servlet.generale;

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
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet(name = "RegistrazioneServlet", urlPatterns = {"/registrazione"})
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente == null) {
			registraCliente(request, response);
		}else {
			response.sendRedirect(request.getContextPath());
		}
	}
	
	private void registraCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String cellulare = request.getParameter("cellulare");
			String email = request.getParameter("email").toLowerCase();
			String password = request.getParameter("password");
			String ripassword = request.getParameter("ripassword");
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			if(nome != null && cognome != null && cellulare != null && email != null && password != null && ripassword !=  null) {
				if(password.equals(ripassword)) {
					if(DBMS.verificaUtente(email.toLowerCase(), cellulare)) {
						status = "{\"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Account già presente.\"}";
					}else {
						DBMS.registrazioneCliente(nome, cognome, cellulare, email.toLowerCase(), password);
						status = "{\"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Account correttamente registrato.\"}";
					}
				}else {
					status = "{\"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Inserire la medesima Password nei relativi campi.\"}";
				}
			}else {
				status = "{\"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Inserire i dati nei relativi campi.\"}";
			}
			pr.write(status);
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
}
