package com.raccuglia.servlet.generale;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccuglia.DB.DBMS;
import com.raccuglia.model.Prodotto;

/**
 * Servlet implementation class MenùServlet
 */
@WebServlet(name = "MenuServlet", urlPatterns = {"/menu"})
public class MenuServlet extends HttpServlet {
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
		menuPage(request, response);
	}
	
	private void menuPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Prodotto> listaProdotti = DBMS.getProdotti();
			PrintWriter pr = response.getWriter();
			response.setContentType("application/json");
			if(!listaProdotti.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				pr.write(mapper.writeValueAsString(listaProdotti));
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
	}
}
