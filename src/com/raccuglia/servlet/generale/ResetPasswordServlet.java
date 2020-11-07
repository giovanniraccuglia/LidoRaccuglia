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
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/resetPassword"})
public class ResetPasswordServlet extends HttpServlet {
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
			resetPassword(request, response);
		}else {
			response.sendRedirect(request.getContextPath());
		}
	}
	
	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("email").toLowerCase();
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			if(LidoUtil.checkInput(email)) {
				if(DBMS.verificaUtente(email, "")) {
					String password = LidoUtil.genPassword(5);
					String oggetto = "RESET PASSWORD";
					String messaggio = "Gentile Cliente, questa è la sua nuova password: '" + password + "'. Per ragioni di sicurezza è consigliabile cambiare la password dopo l'accesso.";
					DBMS.resetPassword(email, password);
					InvioEmail.sendEmail(email, messaggio, oggetto);
					status = "{\"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Password correttamente resettata.\"}";
				}else {
					status = "{\"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Account inesistente.\"}";
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
