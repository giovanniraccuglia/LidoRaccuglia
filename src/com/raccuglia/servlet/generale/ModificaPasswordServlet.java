package com.raccuglia.servlet.generale;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
 * Servlet implementation class ModificaPasswordServlet
 */
@WebServlet(name = "ModificaPasswordServlet", urlPatterns = {"/modificaPassword"})
public class ModificaPasswordServlet extends HttpServlet {
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
			modificaPassword(request, response);
		}
	}
	
	private void modificaPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String vecchiaPassword = request.getParameter("vecchiaPassword");
			String nuovaPassword = request.getParameter("nuovaPassword");
			String confermaPassword = request.getParameter("confermaPassword");
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			if(LidoUtil.checkInput(vecchiaPassword) && LidoUtil.checkInput(nuovaPassword) && LidoUtil.checkInput(confermaPassword)) {
				if(nuovaPassword.equals(confermaPassword)) {
					Utente utente = (Utente) request.getSession().getAttribute("utente");
					if(DBMS.verificaPassword(utente.getIdUtente(), vecchiaPassword)) {
						DBMS.updatePassword(utente.getIdUtente(), nuovaPassword);
						String email = ((Utente) request.getSession().getAttribute("utente")).getEmail();
						String oggetto = "MODIFICA PASSWORD MARRAKECH BEACH";
						String messaggio = "Gentile Cliente, La informiamo che la modifica della sua password è avvenuta con successo.";
						Thread th = new Thread(() -> {
							try {
								InvioEmail.sendEmail(email, messaggio, oggetto);
							}catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							} 
						});
						th.start();
						status = "{\"TYPE\" : \"Successo!\", \"NOTIFICATION\" : \"Password aggiornata correttamente.\"}";
					}else {
						status = "{\"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Password errata.\"}";
					}
				}else {
					status = "{\"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Inserire la medesima password nei relativi campi.\"}";
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
