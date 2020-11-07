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
import com.raccuglia.utils.InvioEmail;
import com.raccuglia.utils.LidoUtil;

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
			if(LidoUtil.checkInput(nome) && LidoUtil.checkInput(cognome) && LidoUtil.checkInput(cellulare) && LidoUtil.checkInput(email) && LidoUtil.checkInput(password) && LidoUtil.checkInput(ripassword)) {
				if(password.equals(ripassword)) {
					if(DBMS.verificaUtente(email, cellulare)) {
						status = "{\"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Account gi&agrave; presente.\"}";
					}else {
						DBMS.registrazioneCliente(nome, cognome, cellulare, email.toLowerCase(), password);
						String oggetto = "BENVENUTO A MARRAKECH BEACH";
						String messaggio = "Gentile Cliente, Le comunichiamo che la registrazione del suo account è avvenuta con successo. Ti invitiamo a visitare il nostro sito per usufruire dei nostri servizi. Grazie, e a presto.";
						InvioEmail.sendEmail(email, messaggio, oggetto);
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
