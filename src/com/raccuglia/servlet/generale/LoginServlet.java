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
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
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
			autenticazione(request, response);
		}else {
			response.sendRedirect(request.getContextPath());
		}
	}
	
	private void autenticazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("email").toLowerCase();
			String password = request.getParameter("password");
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			String status;
			if(email != null && password != null) {
				Utente utente = DBMS.login(email, password);
				if(utente != null) {
					request.getSession().setAttribute("utente", utente);
					status = "{\"LOGIN\" : \"true\"}";
				}else {
					status = "{\"LOGIN\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"E-mail o Password errate.\"}";
				}
			}else {
				status = "{\"LOGIN\" : \"false\", \"TYPE\" : \"Errore!\", \"NOTIFICATION\" : \"Inserire i dati nei relativi campi.\"}";
				
			}
			pr.write(status);
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
}
